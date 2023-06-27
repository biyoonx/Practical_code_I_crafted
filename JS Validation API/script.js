// 공통된 객체/함수
	// 유효하지 않은 값 입력시
	const invalidityMessage = {
		valueMissing: `칸은 필수로 입력해야 합니다.`,
		mkValueMissingMessage(input) {
			const labels = document.querySelectorAll('label');
			const label = Array.from(labels)
				.find(label => label.getAttribute('for') === input.getAttribute('id'))
			return `${label.textContent} ${invalidityMessage['valueMissing']}`;
		},

		duplicatedID: '이미 존재하는 아이디입니다.',
		mismatchedPW: '비밀번호가 일치하지 않습니다.',
	};
	// 유효한 값 입력시
	const validityMessage = {
		validID: '사용 가능한 아이디입니다.',
		matchedPW: '비밀번호가 일치합니다.',
		signUpSuccess: '회원가입을 성공하였습니다.'
	};
	// 유효성 상태
	const validityState = {
		// required 값 미입력 여부
		valueMissing: false,
		isValueMissing(input) {
			if (input.validity.valueMissing) {
				return validityState.valueMissing = true;
			}
			return validityState.valueMissing = false;
		},

		// 아이디 중복 여부
		duplicatedID: false,
		isDuplicatedID(userIDStr) {
			const existingUser = 'user01';
			if (userIDStr === existingUser) {
				return validityState.duplicatedID = true;
			}
			return validityState.duplicatedID = false;
		},

		// 비밀번호 불일치 여부
		mismatchedPW: false,
		isMismatchedPW(userPWStr, userPWCheckStr) {
			if (userPWStr === userPWCheckStr) {
				return validityState.mismatchedPW = false;
			}
			return validityState.mismatchedPW = true;
		},
	};


// 회원가입 버튼 클릭 전 유효성 체크 및 p 요소 만들기
	// 유효성 체크 후에 p 요소 만들기
	function mkValidityPElemAfterCheck(input, checkValidityFunc, validityTypeObj) {
		deleteExistingPElem(input);
		checkValidityFunc();
		mkPElem(
			input,
			{ invalid: validityTypeObj.invalid, valid: validityTypeObj.valid}
		);
	}
	// p 요소 있으면 삭제하기
	function deleteExistingPElem(standardNode) {
		if (standardNode.nextElementSibling) {
			standardNode.parentElement.removeChild(standardNode.nextElementSibling);
		}
	}
	// p 요소 만들어 지정한 위치에 붙이기
	function mkPElem(pElemPos, validResultType) {
		const resultPElem = document.createElement('p');

		const resultText = mkTextNode(
			resultPElem,
			validResultType.invalid,
			validResultType.valid
		);

		resultPElem.appendChild(resultText);
		pElemPos.parentElement.appendChild(resultPElem);
	}
	// p 태그 하위 TextNode 만들기
	function mkTextNode(resultPElem, invalid, valid) {
		const colorSet = {
			invalid: 'firebrick',
			valid: 'green',
		};
		const [result, message, color] = validityState[invalid] ? [invalid, invalidityMessage, colorSet.invalid] : [valid, validityMessage, colorSet.valid];
		return mkResultText(resultPElem, message[result], color);
	}
	// p 태그 하위 TextNode의 내용과 P 요소 스타일 만들기
	function mkResultText(elemNode, message, color) {
		elemNode.style.cssText = `
			color: ${color};
			font-size: 0.8em;
			margin-left: 0.5em;
			font-style: italic;
			font-weight: bold;
		`;
		return document.createTextNode(message);
	}

		// 아이디 중복/중복아닐 때 input 이벤트
		const userID = document.signUpForm.userID;
		userID.addEventListener(
			'input',
			() => {
				if (!userID.value) {
					deleteExistingPElem(userID);
					return;
				}

				mkValidityPElemAfterCheck(
					userID,
					() => validityState.isDuplicatedID(userID.value),
					{ invalid: 'duplicatedID', valid: 'validID' }
				);
			}
		);

		// 비밀번호 일치/불일치시 input 이벤트
		const userPW = document.signUpForm.userPW;
		const userPWCheck = document.signUpForm.userPWCheck;

		userPW.oninput = () => {
			eventPW(userPW, userPWCheck);
		};

		// 비밀번호 칸들이 비었을 때 아래 결과(p요소) 지움
		userPWCheck.oninput = () => {
			if (!(userPW.value && userPWCheck.value)) {
				deleteExistingPElem(userPWCheck);
			}
		};
		// 비밀번호 확인 칸도 입력을 추적하고 싶을 때
		userPWCheck.oninput = () => {
			eventPW(userPW, userPWCheck);
		};
		// 비밀번호 확인 칸이 blur되었을 때만 추적하려 할 때
		// userPWCheck.onchange = () => {
		// 	eventPW(userPW, userPWCheck);
		// };

			// 비밀번호 일치 여부 확인하는 함수 조건 넣어 실시(둘다 값이 입력된 경우만, 아니면 p 요소가 출력되지 않음)
			function eventPW(userPW, userPWCheck) {
				if (userPW.value && userPWCheck.value) {
					return mkValidityPElemAfterCheck(
						userPWCheck,
						() => validityState.isMismatchedPW(userPW.value, userPWCheck.value),
						{ invalid: 'mismatchedPW', valid: 'matchedPW' }
					);
				}
				deleteExistingPElem(userPWCheck);
			}


// 회원가입 버튼 클릭 후 유효성 체크
	document.querySelector('#signUpBtn button').onclick = checkValidity;
	function checkValidity() {
		// 전체 input 검사
		const inputs = document.querySelectorAll('input');
		inputs.forEach(input => {
			// 회원가입 버튼 클릭 후 유효하지 않은 input 칸에 스타일을 적용하기 위한 코드
			input.addEventListener('invalid', () => {
				document.forms[0].classList.add('was-validated');
			});

			// 빈칸 확인
			if (input.validity.valueMissing) {
				return checkInvalidInput(() => validityState.isValueMissing(input), 'valueMissing', input);
			}

			switch (input.getAttribute('id')) {
				// 아이디 중복 확인
				case 'userID':
					return checkInvalidInput(() => validityState.isDuplicatedID(input.value), 'duplicatedID', input);
			
				// 비밀번호 일치 확인
				case 'userPWCheck':
					const userPW = document.querySelector('#userPW');
					return checkInvalidInput(() => validityState.isMismatchedPW(userPW.value, input.value), 'mismatchedPW', input);
			}
		});

		// 유효하지 않은 값 알려주는 커스텀 메세지 반환
		function getMessage(validityType, input) {
			if (validityType === 'valueMissing') {
				return invalidityMessage.mkValueMissingMessage(input);
			} else {
				return `${invalidityMessage[validityType]}`;
			}
		}

		// 입력값 유효성 검사
		function checkInvalidInput(checkInputFunc, validityType, input) {
			checkInputFunc();
			const customMessage = getMessage(validityType, input);
			if (validityState[validityType]) {
				// 1.웹 Validation API를 사용할 경우
				input.addEventListener('input', () => input.setCustomValidity(''));
				input.addEventListener('invalid', () => input.setCustomValidity(customMessage));
				input.value = '';

				// 2.alert를 사용할 경우
				// event.preventDefault();
				// alert(customMessage);
				// input.value = '';
				// input.focus();
				// input.addEventListener('invalid', input.preventDefault());
			}
		}

		// 모든 입력 값이 유효할 때 submit 전 성공 알림
		if (Array.from(inputs).every(input => input.validity.valid)) {
			alert(`${userName.value}님의 ${validityMessage.signUpSuccess}`);
		}
	}