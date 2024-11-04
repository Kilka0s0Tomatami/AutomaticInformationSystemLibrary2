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
	// Функция для отображения модального окна и передачи ID книги
function issueBook(bookId) {
    const modal = document.getElementById("libCardModal");
    const bookIdInput = document.getElementById("bookId");

    // Открыть модальное окно
    modal.style.display = "block";

    // Установить ID книги в скрытое поле формы
    bookIdInput.value = bookId;
}

// Закрытие модального окна
document.querySelector(".close").onclick = function () {
    document.getElementById("libCardModal").style.display = "none";
};

// Закрытие модального окна при клике вне его
window.onclick = function (event) {
    const modal = document.getElementById("libCardModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
};

// Обработчик отправки формы для выдачи книги
document.getElementById("issueForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const libCardId = document.getElementById("libCardId").value;
    const bookId = document.getElementById("bookId").value;

	const queryParams = new URLSearchParams({
		bookEditionId: bookId,
		libCardId: libCardId,
		
	})
    // Отправляем POST-запрос на сервер
    fetch(`/librarian/issueBook?${queryParams.toString()}`, {
			method: 'POST',
		})
			.then(response => {
				const resultsDiv = document.getElementById('searchResults')
				resultsDiv.innerHTML = '' // Очистка предыдущих результатов

				if (response.ok) {
					resultsDiv.innerHTML = '<p>Книга выдана успешно!</p>'
				} else {
					resultsDiv.innerHTML = '<p>Ошибка при выдаче книги.</p>'
				}
				// Закрыть модальное окно после успешной выдачи
				document.getElementById('libCardModal').style.display = 'none'
			})
			.catch(error => {
				console.error('Ошибка при выдаче книги:', error)
				document.getElementById('searchResults').innerHTML =
					'<p>Ошибка при выдаче книги.</p>'
			})
});

