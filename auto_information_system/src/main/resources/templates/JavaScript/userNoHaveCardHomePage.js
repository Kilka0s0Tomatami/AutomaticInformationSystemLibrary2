document.addEventListener('DOMContentLoaded', function () {
	// Обработчик отправки формы
	document
		.getElementById('ticketForm')
		.addEventListener('submit', function (event) {
			event.preventDefault() // Предотвращаем стандартное поведение формы

			const formData = {
				lib_card_first_name: document.getElementById('firstName').value,
				lib_card_second_name: document.getElementById('lastName').value,
				lib_card_father_name: document.getElementById('middleName').value,
				lib_card_mobilephone_number: document.getElementById('phoneNumber').value,
				lib_card_homephone_number: document.getElementById('homePhone').value,
			}

			// Проверка, что поля имя и фамилия не пустые
			if (!formData.lib_card_first_name || !formData.lib_card_second_name) {
				alert('Имя и фамилия не могут быть пустыми.')
				return
			}

			// Отправляем данные на сервер для изменения
			fetch('/user/registerUserLibCard', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(formData),
			})
				.then(response => {
					if (response.ok) {
						window.location.href = '/'
					} else {
						alert('Ошибка при обновлении данных.')
					}
				})
				.catch(error => console.error('Ошибка при обновлении данных:', error))
		})
    })
