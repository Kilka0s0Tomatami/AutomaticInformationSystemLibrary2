document
	.getElementById('register-form')
	.addEventListener('submit', function (event) {
        event.preventDefault()
        const username = document.getElementById('username').value
		const password = document.getElementById('password').value
		const confirmPassword = document.getElementById('confirm-password').value
		const passwordError = document.getElementById('password-error')

		// Сбрасываем предыдущие ошибки
		passwordError.textContent = ''

		// Проверяем, совпадают ли пароли
		if (password !== confirmPassword) {
			event.preventDefault() // Останавливаем отправку формы
			passwordError.textContent = 'Пароли не совпадают!'
            return
		}
        const userData = {
			username: username,
			user_password: password,
			}
        // Отправка POST-запроса на сервер
    fetch('/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'  // Указываем, что данные будут в формате JSON
        },
        body: JSON.stringify(userData)  // Преобразуем объект в строку JSON
    })
    .then(response => {
        if (response.ok) {
            // Если запрос успешен
            return response.json();  // Предположим, что сервер возвращает ответ в формате JSON
        } else {
            throw new Error('Ошибка при регистрации');
        }
    })
    .then(data => {
        // Обработка успешного ответа
        console.log('Пользователь успешно зарегистрирован!')
        window.location.href = '/login';  // Перенаправление на страницу входа
    })
    .catch(error => {
        // Обработка ошибок
        console.error('Ошибка:', error);
        passwordError.textContent = 'Ошибка при регистрации. Попробуйте позже.';
    });
})
