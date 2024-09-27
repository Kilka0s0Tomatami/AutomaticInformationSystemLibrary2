document.addEventListener('DOMContentLoaded', function () {
	// Загружаем шапку

	// Получаем информацию о билете через AJAX-запрос
	fetch('/user/getUserLibCard', {
		method: 'GET',
	})
		.then(response => response.json())
		.then(data => {
			// Заполняем форму данными о билете
			document.getElementById('id').value = data.lib_card_id
			document.getElementById('firstName').value = data.lib_card_first_name
			document.getElementById('lastName').value = data.lib_card_second_name
			document.getElementById('middleName').value = data.lib_card_father_name
			document.getElementById('phoneNumber').value =
				data.lib_card_mobilephone_number
			document.getElementById('homePhone').value =
				data.lib_card_homephone_number
		})
		.catch(error => console.error('Ошибка при получении данных:', error))

	// Обработчик отправки формы
	document
		.getElementById('ticketForm')
		.addEventListener('submit', function (event) {
			event.preventDefault() // Предотвращаем стандартное поведение формы

			const formData = {
				firstName: document.getElementById('firstName').value,
				lastName: document.getElementById('lastName').value,
				middleName: document.getElementById('middleName').value,
				phoneNumber: document.getElementById('phoneNumber').value,
				homePhone: document.getElementById('homePhone').value,
			}

			// Проверка, что поля имя и фамилия не пустые
			if (!formData.firstName || !formData.lastName) {
				alert('Имя и фамилия не могут быть пустыми.')
				return
			}

			// Отправляем данные на сервер для изменения
			fetch('/api/ticket', {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			})
				.then(response => {
					if (response.ok) {
						alert('Данные успешно обновлены.')
					} else {
						alert('Ошибка при обновлении данных.')
					}
				})
				.catch(error => console.error('Ошибка при обновлении данных:', error))
		})
})
