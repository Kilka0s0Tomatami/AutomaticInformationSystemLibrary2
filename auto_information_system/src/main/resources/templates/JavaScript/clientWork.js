<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Работа с клиентом</title>
    <style>
        body { font-family: Arial, sans-serif; }
        .hidden { display: none; }
        .book-list, .new-book-search, .card-registration { margin-top: 20px; }
        .button { margin: 5px; padding: 5px 10px; cursor: pointer; }
    </style>
</head>
<body>

    <h1>Работа с клиентом (читателем)</h1>

    <div id="clientWork">
        <!-- Ввод ID читательской карты -->
        <label for="libCardId">ID читательской карты:</label>
        <input type="number" id="libCardId" required>
        <button onclick="getBooksOnHand()">Поиск книг на руках</button>
        
        <!-- Кнопка "Зарегистрировать читательскую карту" -->
        <button onclick="toggleCardRegistration()">Зарегистрировать читательскую карту</button>

        <!-- Форма регистрации читательской карты -->
        <div id="cardRegistrationForm" class="card-registration hidden">
            <h2>Регистрация читательской карты</h2>
            <label for="userName">Имя пользователя:</label>
            <input type="text" id="userName" required>
            <button onclick="registerLibraryCard()">Зарегистрировать</button>
        </div>

        <!-- Список книг на руках у читателя -->
        <div id="booksList" class="book-list hidden">
            <h2>Книги на руках</h2>
            <div id="booksContainer"></div>
            <button onclick="resetClientWork()">Назад</button>
            <button onclick="showBookSearchForm()">Подобрать читателю новую книгу</button>
        </div>

        <!-- Форма поиска новой книги -->
        <div id="bookSearchForm" class="new-book-search hidden">
            <h2>Поиск книги</h2>
            <label for="author">Автор:</label>
            <input type="text" id="author">
            <label for="title">Название:</label>
            <input type="text" id="title">
            <label for="year">Год:</label>
            <input type="number" id="year">
            <label for="place">Место:</label>
            <input type="text" id="place">
            <label for="udk">УДК:</label>
            <input type="text" id="udk">
            <label for="bbk">ББК:</label>
            <input type="text" id="bbk">
            <button onclick="searchBookEdition()">Найти книгу</button>
            <div id="searchResults"></div>
        </div>
    </div>

    <script>
        function getBooksOnHand() {
            const libCardId = document.getElementById('libCardId').value;
            fetch(`/librarian/getBooksOnHandsByLibCardId?libCardId=${libCardId}`)
                .then(response => response.json())
                .then(data => {
                    const booksContainer = document.getElementById('booksContainer');
                    booksContainer.innerHTML = '';
                    data.forEach(book => {
                        const bookDiv = document.createElement('div');
                        bookDiv.innerHTML = `
                            <p>${book.author} - ${book.title} (${book.year_publication})</p>
                            <p>Фондовый номер: ${book.fondNumber}, Статус: ${book.status}</p>
                            ${book.status === 'Забронирована' ? `<button onclick="issueBook(${book.fondNumber})">Выдать</button>` : ''}
                            ${book.status === 'Взята' ? `<button onclick="returnBook(${book.fondNumber})">Принять/Вернуть книгу</button>` : ''}
                        `;
                        booksContainer.appendChild(bookDiv);
                    });
                    document.getElementById('booksList').classList.remove('hidden');
                });
        }

        function showBookSearchForm() {
            document.getElementById('bookSearchForm').classList.remove('hidden');
        }

        function searchBookEdition() {
            const author = document.getElementById('author').value;
            const title = document.getElementById('title').value;
            const year = document.getElementById('year').value;
            const place = document.getElementById('place').value;
            const udk = document.getElementById('udk').value;
            const bbk = document.getElementById('bbk').value;

            const queryParams = new URLSearchParams({
                author, title, year, place, udk, bbk
            });

            fetch(`/api/searchBookEdition?${queryParams.toString()}`)
                .then(response => response.json())
                .then(data => {
                    const searchResults = document.getElementById('searchResults');
                    searchResults.innerHTML = '';
                    data.forEach(book => {
                        const bookDiv = document.createElement('div');
                        bookDiv.innerHTML = `
                            <p>${book.author} - ${book.title} (${book.year_publication})</p>
                            <button onclick="issueBook(${book.fondNumber})">Выдать</button>
                        `;
                        searchResults.appendChild(bookDiv);
                    });
                });
        }

        function issueBook(bookEditionId) {
            const libCardId = document.getElementById('libCardId').value;
            fetch('/librarian/issueBook', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ bookEditionId, libCardId })
            })
            .then(response => response.json())
            .then(data => {
                alert(`Книга выдана. Место хранения: ${data.cell_id}`);
            });
        }

        function returnBook(bookCopyFondNumber) {
            fetch('/librarian/returnBookCopies', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ bookCopyFondNumber })
            })
            .then(() => {
                alert("Книга принята/возвращена.");
            });
        }

        function toggleCardRegistration() {
            document.getElementById('cardRegistrationForm').classList.toggle('hidden');
        }

        function registerLibraryCard() {
            const userName = document.getElementById('userName').value;
            fetch('/librarian/registerUserLibCard', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ userName })
            })
            .then(() => {
                alert("Читательская карта зарегистрирована.");
            });
        }

        function resetClientWork() {
            document.getElementById('booksList').classList.add('hidden');
            document.getElementById('bookSearchForm').classList.add('hidden');
        }
    </script>
</body>
</html>
