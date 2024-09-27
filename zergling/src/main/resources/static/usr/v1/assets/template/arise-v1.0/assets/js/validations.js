// // Example starter JavaScript for disabling form submissions if there are invalid fields
// (() => {
// 	"use strict";

// 	// Fetch all the forms we want to apply custom Bootstrap validation styles to
// 	const forms = document.querySelectorAll(".needs-validation");

// 	// Loop over them and prevent submission
// 	Array.from(forms).forEach((form) => {
// 		form.addEventListener(
// 			"submit",
// 			(event) => {
// 				if (!form.checkValidity()) {
// 					event.preventDefault();
// 					event.stopPropagation();
// 				} else {
// 					event.preventDefault(); // Prevent default submission

// 					// If form is valid, send data using fetch or any AJAX method
// 					const formData = new FormData(form);
// 					fetch('your-server-endpoint', {
// 						method: 'POST',
// 						body: formData
// 					})
// 					.then(response => response.json())
// 					.then(data => {
// 						console.log('Success:', data);
// 					})
// 					.catch((error) => {
// 						console.error('Error:', error);
// 					});
// 				}

// 				form.classList.add("was-validated");
// 			},
// 			false
// 		);
// 	});
// })();

(() => {
	"use strict";

	const forms = document.querySelectorAll('.needs-validation');

	Array.from(forms).forEach((form) => {
		form.addEventListener('submit', (event) => {
			// const validateElements = form.querySelectorAll('.validate-this');
			const validateElements = form.querySelectorAll('.form-control:required:not(:read-only), .form-select');

			let allValid = true;

			validateElements.forEach((element) => {
				// 유효성 검사를 제외할 클래스를 지정
                const excludeValidationClass = 'exclude-validation';	

                // 요소에 유효성 검사 제외 클래스가 있는지 확인
                if (element.classList.contains(excludeValidationClass)) {
                    return; // 유효성 검사를 수행하지 않음
                }

				
				if (!element.checkValidity()) {
					allValid = false;
					element.classList.add('is-invalid');
				} else {
					element.classList.remove('is-invalid');
					element.classList.add('is-valid');
				}
			});

			if (!allValid) {
				event.preventDefault();	// 페이지전환막기
				event.stopPropagation();
			} else {

				// 서버에 데이터 전송 (예: fetch API 사용)
				// const formData = new FormData(form);
				// fetch('your-server-endpoint', {
				// 	method: 'POST',
				// 	body: formData
				// })
				// .then(response => response.json())
				// .then(data => {
				// 	console.log('Success:', data);
				// })
				// .catch((error) => {
				// 	console.error('Error:', error);
				// });

				form.classList.add('was-validated');
			}
		}, false);
	});
})();

// Example starter JavaScript for disabling form submissions if there are invalid fields
// (() => {
// 	'use strict'
  
// 	// Fetch all the forms we want to apply custom Bootstrap validation styles to
// 	const forms = document.querySelectorAll('.needs-validation')
  
// 	// Loop over them and prevent submission
// 	Array.from(forms).forEach(form => {
// 	  form.addEventListener('submit', event => {
// 		if (!form.checkValidity()) {
// 		  event.preventDefault()
// 		  event.stopPropagation()
// 		}
  
// 		form.classList.add('was-validated')
// 	  }, false)
// 	})
//   })();