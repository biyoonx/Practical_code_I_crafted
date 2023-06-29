const form = document.noticePost;
const inputs = [form.title, form.author, form.content, form.uploadFile];

// 처음에 버튼들 비활성화해두고 이벤트 설정해두기
document.body.onload = resetState;

// 작성하기 버튼은 게시글 올리고 화면 리셋하고(새로고침은 아님) 전체 게시글 수를 업데이트함, 게시글 개수 제한도 함
form.submitBtn.addEventListener('click', () => {
	if (tbodyRows.length === 5) {
		alert('게시글은 5개를 초과할 수 없습니다.');
	} else {
		resetState();
		contentPost();
		showPostCount();
	}
});
// 삭제하기 버튼은 공지사항 작성 내용을 비움
form.resetBtn.addEventListener('click', () => {
	inputs.forEach(input => {
		input.value = '';
	})
	notEmptyInput = [];
	resetState();
});

// 화면이 새로고침하거나 삭제하기 버튼/작성하기 버튼을 클릭했을 때 화면 상태로 전체 게시글 수를 업데이트 하고 작성하기와 삭제하기 버튼을 비활성화함
function resetState() {
	form.submitBtn.disabled = true;
	form.resetBtn.disabled = true;
	showPostCount();
}

// 배열에 담아 각 input에 값이 입력되는 것에 따라 버튼 활성화/비활성화 관리하기
let notEmptyInput = [];
inputs.forEach(input => {
		input.addEventListener('input', inputEvent);
		input.addEventListener('click', inputEvent);
		function inputEvent() {
			enableResetBtn(this);
			enableSubmitBtn(this);
		};
	}
);

// 작성하기 버튼 활성화/비활성화
function enableSubmitBtn(input) {
	// 해당 칸에 값이 입력되어 있을 때
	if (input.value) {
		if(!(notEmptyInput.includes(input))) {
			notEmptyInput.push(input);
		}
		// 첨부파일 제외 3개 또는 첨부파일 포함 4개일 때
		let cond = ((notEmptyInput.length === 3) && !(notEmptyInput.includes(form.uploadFile))) || notEmptyInput.length === 4;
		if (cond) {
			form.submitBtn.disabled = false;
		}
	// 해당 칸에 값이 없을 때 배열 비우고 버튼 비활성화하기
	} else {
		if(notEmptyInput.includes(input)) {
			notEmptyInput.splice(notEmptyInput.indexOf(input), 1);
			form.submitBtn.disabled = true;
		}
	}
}
// 삭제하기 버튼 활성화/비활성화
function enableResetBtn(input) {
	if (input.value) {
		form.resetBtn.disabled = false;
	} else {
		if (notEmptyInput.length === 0) {
			form.resetBtn.disabled = true;
		}
	}
}

// 게시판 관리용 배열로 각 행의 정보가 담겨있음
let tbodyRows = [];
// 게시판 번호 및 게시글 관리용 번호
let tdNoSerial = 1;

function contentPost() {
	// 사용자가 입력한 정보를 모아 담은 객체
	const rowArr = {
		serialNo: tdNoSerial++,
		title: form.title.value,
		author: form.author.value,
		content: form.content.value,
		date: new Date().toLocaleDateString(),
		randomNo: (Math.random() * 100).toFixed(),
		hasFiles: (form.uploadFile.files.length > 0),
		fileName: (form.uploadFile.files.length > 0) ? `${form.uploadFile.files.length}개의 파일` : '파일없음',
	};
	// 게시글 관리용 배열에 행 정보를 담은 객체를 담음
	tbodyRows.push(rowArr);

	// 표의 내용 부분을 받아와서 행을 만듦
	const tbody = document.querySelector('#boardContent');
	const row = tbody.insertRow(0);

	// 여기서부터 셀 만드는 부분
	addPostCell(rowArr.serialNo);
	const noticeTitle = addPostCell(rowArr.title);
	noticeTitle.style.cursor = 'pointer';

	// 버튼 만들기(첨부 파일이 있으면 활성화, 없으면 비활성화로 생성)
	const colBtn = row.insertCell(-1)
	const btn = document.createElement('button');
	btn.disabled = !(rowArr.hasFiles);
	const btnTxt = document.createTextNode('첨부파일');
	btn.appendChild(btnTxt);
	colBtn.appendChild(btn);

	addPostCell(rowArr.author);
	addPostCell(rowArr.date);
	addPostCell(rowArr.randomNo);

	// 텍스트 담은 셀 만들어서 행에 붙이고 셀 반환
	function addPostCell(content) {
		const col = row.insertCell(-1);
		const colTxt = document.createTextNode(content);
		col.appendChild(colTxt);
		return col;
	}

	// 입력 값들 모두 비우기
	inputs.forEach(input => input.value = '');

	noticeTitle.onclick = () => {
		// 게시판 내용 띄울 때 내용을 구성하는 요소들 받아와서 객체로 구성
		const posts = {
			title: document.querySelector('#postTitle'),
			author: document.querySelector('#postAuthor'),
			content: document.querySelector('#postContent'),
			fileName: document.querySelector('#postFileName'),
			// button
			backBtn: document.querySelector('#postBackBtn'),
			deleteBtn: document.querySelector('#postDeleteBtn'),
		};
		
		// 게시글 관리용 배열에서 이 게시글에 해당하는 객체와 배열인덱스를 받아옴
		let postArr;
		let postArrIndex;
		for (let index = 0; index < tbodyRows.length; index++) {
			if (tbodyRows[index].serialNo === +noticeTitle.previousElementSibling.textContent) {
				postArr = tbodyRows[index];
				postArrIndex = index;
			}
		}
		
		// 입력한 내용을 게시판 내용 볼 때 띄우기
		posts.title.innerText = postArr.title;
		posts.author.innerText = postArr.author;
		posts.content.innerText = postArr.content;
		posts.fileName.innerText = postArr.fileName;
		
		// 게시판 표에서 제목 클릭하면 게시글 보여줌
		const postBackground = document.querySelector('#postBackground');
		postBackground.style.display = 'flex';

		// 뒤로가기 버튼을 누르면 게시판 내용이 다시 안보임
		posts.backBtn.onclick = () => {
			postBackground.style.display = 'none';
		};

		// 삭제하기 버튼 누르면 게시판 내용이 다시 안보이고 해당 게시글이 게시판 표에서 없어짐
		posts.deleteBtn.onclick = () => {
			postBackground.style.display = 'none';
			noticeTitle.parentNode.remove();
			tbodyRows.splice(postArrIndex, 1);
			showPostCount();
		};
	};	
}

// 전체 게시글 수 실시간 업데이트
function showPostCount() {
	const postCount = document.querySelector('#postCount');
	postCount.textContent = '';
	const postCountTxt = document.createTextNode(`${tbodyRows.length}개`);
	postCount.appendChild(postCountTxt);
}