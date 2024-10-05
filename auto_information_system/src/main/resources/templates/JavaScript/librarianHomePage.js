document
	.getElementById('searchForm')
	.addEventListener('submit', function (event) {
		event.preventDefault() // Предотвращаем стандартное поведение формы

		const author = document.getElementById('author').value
		const title = document.getElementById('title').value
		const year = document.getElementById('year').value
		const place = document.getElementById('place').value
		const udk = document.getElementById('udk').value
		const bbk = document.getElementById('bbk').value

		// Формируем параметры для GET-запроса
		const queryParams = new URLSearchParams({
			author: author,
			title: title,
			year: year,
			place: place,
			udk: udk,
			bbk: bbk,
		})

		// Отправляем запрос на сервер
		fetch(`/api/searchBookEdition?${queryParams.toString()}`, {
			method: 'GET',
		})
			.then(response => response.json()) // Ожидаем ответ в формате JSON
			.then(data => {
				// Очищаем блок для результатов перед отображением новых данных
				const resultsDiv = document.getElementById('searchResults')
				resultsDiv.innerHTML = '' // Очистка предыдущих результатов

				if (data.length > 0) {
					// Проходим по массиву книг и добавляем их в HTML
					data.forEach(book => {
						const bookElement = document.createElement('div')
						bookElement.classList.add('book-result')

						bookElement.innerHTML = `
                    <p><strong>Автор:</strong> ${book.book_edition_author}</p>
                    <p><strong>Название:</strong> ${book.book_edition_title}</p>
                    <p><strong>Год издания:</strong> ${book.book_edition_year_publication}</p>
                    <p><strong>Место издания:</strong> ${book.book_edition_place_publication}</p>
                    <p><strong>УДК:</strong> ${book.book_edition_udk_number}</p>
                    <p><strong>ББК:</strong> ${book.book_edition_bbk_number}</p>
                    <button onclick="issueBook(${book.book_edition_id})">Выдать книгу</button>
                    <hr>
                `
						resultsDiv.appendChild(bookElement)
					})
				} else {
					// Если книги не найдены
					resultsDiv.innerHTML = '<p>Книги не найдены.</p>'
				}
			})
			.catch(error => {
				console.error('Ошибка при выполнении поиска:', error)
				document.getElementById('searchResults').innerHTML =
					'<p>Ошибка при поиске книг.</p>'
			})
	})
	function issueBook(bookId) {
		// Отправляем запрос на сервер
		fetch(`/api/issueBook/${bookId}`, {
			method: 'POST',
		})
			.then(response => response.json()) // Ожидаем ответ в формате JSON
			.then(data => {
				// Очищаем блок для результатов перед отображением новых данных
				const resultsDiv = document.getElementById('searchResults')
				resultsDiv.innerHTML = '' // Очистка предыдущих результатов

				if (data.message === 'Book issued successfully!') {
					// Если книга выдана успешно
					resultsDiv.innerHTML = '<p>Книга выдана успешно!</p>'
				} else {
					// Если книга не выдана успешно
					resultsDiv.innerHTML = '<p>Книга не выдана успешно.</p>'
				}
			})
			.catch(error => {
				console.error('Ошибка при выполнении поиска:', error)
				document.getElementById('searchResults').innerHTML =
					'<p>Ошибка при поиске книг.</p>'
			})
	}
