function getReceivedBooks() {
    fetch('/user/getReceivedBooks', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            const resultsDiv = document.getElementById('searchResults')
            if (data.length > 0) {
				// Проходим по массиву книг и добавляем их в HTML
				data.forEach(book => {
                    const bookElement = document.createElement('div')
                    bookElement.classList.add('book-result')
                    bookElement.innerHTML = `
                        <p><strong>Автор:</strong> ${book.author}</p>
                        <p><strong>Название:</strong> ${book.title}</p>
                        <p><strong>Год издания:</strong> ${book.year_publication}</p>
                        <p><strong>Статус:</strong> ${book.status}</p>
                        <p><strong>Дата взятия/бронирования:</strong> ${book.issueDate}</p>
                        <p><strong>Дата возврата:</strong> ${book.returnDate}</p>
                        <hr>
                        `
                    resultsDiv.appendChild(bookElement)
                    })
                }else {
					// Если книги не найдены
					resultsDiv.innerHTML = '<p>У вас нет полученных или забронированных книг</p>'
				}
    })
        .catch(error => console.error('Ошибка при получении данных:', error))
}
window.onload = getReceivedBooks()