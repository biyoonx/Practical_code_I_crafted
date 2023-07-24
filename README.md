# myex
- 강의자료, 책 등에서의 실습예제 나만의 방식으로 해본 것들


# GradeManagementSystem(학점관리시스템)
- 책 <Do it! 자바 프로그래밍 입문>의 최종 프로젝트 실습
- 개발 환경 : Eclipse
- 데이터와 목표만 가지고 직접 설계 및 구현한 프로그램(책의 코드와 다름)
- Student 클래스 : 학생에 관한 클래스
  - 필드: 이름, 학번, 전공, 평균 점수, 최종 학점, 수강 과목(해시맵)
  - 생성자: 이름과 전공을 입력해야하고 학번은 자동으로 생성되며 수강과목 해시맵이 생성됨
  - 메서드: 평균 점수 집계, 최종 학접 집계, 수강과목 추가, 수강과목 점수 추가, 자동 학번 부여 등
- Major 클래스 : 전공에 관한 클래스
  - 필드: 전공명, 전공번호, 필수 수강과목
  - 생성자: 전공명과 필수 수강과목을 입력해야하고 전공번호는 자동 부여됨
  - 메서드: 전공명 getter, 전공번호 getter 필수 수강과목 getter 등
- Subject 클래스 : 강의 과목에 관한 클래스
  - 필드: 과목명, 과목번호, Pass/Fail 여부
  - 생성자: 과목명을 입력해야 하고 과목번호는 자동 생성됨
  - 메서드 : 과목명 getter, Pass/Fail 여부 setter 등
- Course 클래스 : 수강한 과목에 관한 속성 및 기능을 넣은 클래스
  - 필드: 과목, 점수, 학점, 학점 정책
  - 생성자: 과목을 입력해야 하며 점수는 -1(미입력)로 초기화됨
  - 메서드 : 학점 정책 setter(Pass/Fail여부-필수 수강과목 여부 확인 후 아니면 일반 학점 정책 적용), 점수 setter(점수가 입력되면 학점도 집계되도록 함) 등
- GradeSystem 인터페이스 : 학점을 산출하는 추상 메서드만 있는 학점 정책 인터페이스
  - General 구현 클래스 : 싱글톤 패턴의 일반 학점 정책
    - 오버라이딩한 String getGrade(int 점수)만 입력
  - ForRequiredSubject 구현 클래스 : 싱글톤 패턴의 필수과목 학점 정책
    - 오버라이딩한 String getGrade(int 점수)만 입력
  - PassFail 구현 클래스
    - 오버라이딩한 String getGrade(int 점수)만 입력
- Report sealed 추상 클래스 : 리포트에 관한 템플릿을 제공하는 추상 클래스. SubjectReport와 MajorReport 클래스만 상속을 허용함.
  - 필드: 자주 사용하는 기호들에 관한 것들 상수로 선언, Reportable 인터페이스, 학생 ArrayList, 최종 리포트, 헤더, 메인, 푸터
  - 생성자: Reportable을 상속받는 타입과 학생들(가변인자)을 입력해야하고 필요한 생성자와 템플릿 메서드 호출
  - 메서드: 헤더 생성, 메인 생성, 푸터 생성, 집계(총점, 최빈값, 최고점, 최저점, 평균, 인원) 메서드, 학생 목록 필터링(조건에 맞는 학생만), 최종 리포트를 만들기 위한 템플릿 메서드, 리포트 출력하는 메서드
  - Reportable 마커 인터페이스 : 리포트할 수 있는 것들을 제한하는 마커 인터페이스
  - MajorReport 구현 클래스 : 전공생에 대한 결과를 집계하는 리포트 클래스
    - 생성자와 리포트 결과 출력하는 메서드만 사용 가능
    - 생성자: 전공과 학생들(배열)을 입력하면 집계됨
    - 메서드: 학생 목록 필터링(해당 전공생만 ArrayList에 추가), 헤더 내용 생성, 메인 내용 생성, 푸터 내용 생성, sum(의미가 없는 것 같은데 오버라이딩해야해서 -1 반환하게 함), mode(학점 중 최빈값 반환 - 동일하면 앞에 있는 성적 출력), avg(학생 평균 점수 출력), max/min(학생 최고점/최저점 출력)
  - SubjectReport 구현 클래스 : 강의 수강생에 대한 결과를 집계하는 리포트 클래스
    - 생성자와 리포트 결과 출력하는 메서드만 사용 가능
    - 생성자: 과목과 학생들(가변인자)을 입력하면 집계됨
    - 메서드: 학생 목록 필터링(해당 과목을 듣는 학생만 ArrayList에 추가), 헤더 내용 생성, 메인 내용 생성, 푸터 내용 생성, sum(학생들의 점수 총합), mode(점수 시스템이 달라서 null 반환/사용하지 않음), avg(학생 점수 평균 산출), max/min(학생들 점수 중 최고점/최저점 산출)
- RunTest 실행 클래스
  - 강의 과목 생성
  - 전공 생성
  - 학생 생성
  - 수강과목 담기 및 점수 입력
  - 학생 목록 생성
  - 클래스 및 메서드 테스트용 출력들
  - 강의과목 리포트 생성
  - 전공 리포트 생성
- FinalStaticNumber 클래스 : 상수 영역(만들어놓고 사용할 일이 없어서 비워져있음)
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223117795740)


# ChatProgram(TCP 소켓 프로그래밍을 이용한 채팅 프로그램)
- 책 <이것이 자바다>의 Chapter 19 Section 7의 채팅 프로그램 실습
- 개발 환경 : Eclipse(+외부 라이브러리 : JSON)
- 설계의 틀은 책에서 가져왔으나 만들면서 내 방식대로 변형시켜본 것
- ChatServer 클래스 : 채팅프로그램의 유저(클라이언트)의 연결 요청을 승인하며 채팅방을 관리하는 클래스
  - 필드 : 서버 소켓, 스레드풀(SocketClient에서 사용), 유저(클라이언트)와 연결된 소켓을 관리하기 위한 동기화된 맵인 chatRoom(채팅방)
  - 메서드
    - start(int portNo) : 포트번호를 매개값으로 받아 이 포트로 서버 열기
    - run() : 유저와 연결을 승인(accept())하고 반환된 소켓을 SocketClient를 생성하여 매개값으로 넘김. SocketClient의 receive() 함수를 호출하여 유저의 요청에 따라 입장, 메세지 전송, 퇴장이 이루어질 수 있도록 함. 스레드를 별도로 생성하였기 때문에 서버 소켓이 닫히면 에러가 발생하므로 프로세스가 종료되도록 예외처리함. 데몬스레드로 설정하여 메인 스레드가 종료하면 해당 스레드도 종료되도록 함.
    - stop() : 서버 소켓을 닫고 스레드풀을 정리함. chatRoom에 있는 SocketClient의 소켓도 모두 닫도록 함.
    - addToChatRoom(SocketClient clientSoc) : 채팅방에 유저가 입장되도록 함(서버가 관리하는 목록에 추가)
    - removeFromChatRoom(SocketClient clientSoc) : 채팅방에 유저가 퇴장하도록 함(서버가 관리하는 목록에서 삭제)
    - private String notice(String status, String userInfo) : 유저가 채팅방에 입장 또는 퇴장시 알림으로 사용할 문자열 생성
    - sendToAll(SocketClient sender, String receivedMessage) : 유저가 요청 사항을 보내면 해당 내용이 발신자를 제외한 채팅방의 모든 유저에게 일괄 전송되도록 함(chatRoom으로 관리하는, 유저와 연결된 모든 소켓에서 데이터를 보내는 방식).
    - String mkMessage(String key, String receivedMessage) : 유저에게 메세지를 보낼때 형식을 JSON으로 바꿔주는 메서드
  - 스레드 : 메인 스레드와 채팅방에 딜레이 적게 유저를 받을 수 있도록 하기 위해 run() 부분 분리
  - 메인 메서드(실행)
    - 55555번 포트로 서버 오픈.
    - 유저의 연결 요청을 받는 스레드 따로 생성.
    - Scanner를 받아서 서버가 바로 종료되지 않도록 콘솔창에 stop을 입력받을 때까지 stop() 메서드 블로킹시킴
- SocketClient 클래스 : 서버가 채팅 유저와 연결된 소켓을 관리하기 위해 만든 클래스(서버에서 동기화된 맵으로 관리함), ChatServer의 내부 인스턴스 클래스로 만들어도 될 듯하나 너무 길어져서 분리한 듯 main() 메서드는 없는데 이는 채팅 서버의 run()에서 생성하고 모든 프로세스가 돌아가게 하는 receive()가 호출되었기 때문.
  - 필드 : 서버 소켓, 유저와 연결된 소켓, 유저의 key(채팅명@클라이언트 IP 또는 HostName), DataInputStream, DataOutputStream, 클라이언트의 주소 정보(IP 또는 HostName), 조용히 퇴장 여부
  - 생성자 : 서버 소켓과 유저와 연결된 매개값을 받아 내부 멤버변수에 넣어줌. 입출력스트림을 유저와 연결된 소켓과 연결.
  - 메서드
    - send() : 소켓에 연결된 유저에게 매개값으로 입력된 메세지 전송(출력 스트림 사용)
    - receive()
      - 입력 스트림으로 유저에게 요청 메세지를 받아 JSONObject로 메세지 파싱.
        - 메세지(msg) : 키 request의 값이 msg이면 키 data로 온 메세지 msg 내용을 전송
        - 입장(entry) : 키 request의 값이 entry이면 유저의 key(채팅명@IP 또는 HostName)를 설정하고 채팅방에 입장하도록 함. 입장한 유저를 제외한 모든 방에 입장했다는 메세지 전송.
        - 조용히 퇴장(quietExit) : 키 request의 값이 quietExit이면 조용히 퇴장 여부를 data값(true)으로 변경. (퇴장은 후에 유저가 소켓을 닫으면 예외로 넘어가서 처리됨)
        - 예외(퇴장) : 채팅방에서 유저를 제외하고 유저와 연결된 소켓을 닫음. 조용히 퇴장 여부가 false일 경우만 모든 방에 퇴장 메세지가 전송됨(true면 전송되지 않음)
    - close() : 서버의 chatRoom에서 해당 소켓을 제거(=채팅방 퇴장)하고 유저와 연결된 소켓을 닫음.
    - 기타 getter/setter : String getKey(), String getAddrInfo(), setKey(String chatName), setAddrInfo(String hostString), setQuietExit(boolean quietExit)
  - 스레드 : 유저의 요청을 처리하기 위해 스레드풀 사용. 유저들의 입장/퇴장과 메세지 전송에 따라 동시다발적으로 많은 요청이 있을 수 있기 때문에 스레드풀로 스레드 생성.
- ChatUser 클래스 : 유저용 소켓을 생성하고 서버 및 다른 사용자와 소통할 수 있는 클래스(클래스 이름을 책에 있는 ChatClient로 하면 헷갈려서 ChatUser로 변경함)
  - 필드 : 서버와 연결된 소켓, 데이터 입출력 스트림, 채팅명
  - 메서드
    - connect(String addr, int portNo) : 연결할 서버의 IP 주소와 포트번호를 매개값으로 받아 해당 서버로 연결함. 입출력 스트림을 서버와 연결된 소켓에 연결함.
    - unconnect() : 서버와 연결된 소켓을 닫음. (서버에서 퇴장 처리됨)
    - setChatName(String chatName) : 채팅명을 설정함.
    - send(String message) : 서버와 통신하기 위한 메서드로 서버에 요청내용을 매개변수로 받아 "전송"하는 역할.(전송만 함)
      - String mkRequest(String request, String data) : 서버에 요청할 메세지 내용을 JSON 형태로 생성(매개값으로 넘길 문자열)
      - enterChatRoom() : 서버에 채팅방에 입장하겠다는 요청 전송
      - exitChatRoom(boolean quietExit) : 매개값으로 조용히 퇴장 여부를 받아 true일 경우 서버에 조용히 퇴장 여부의 값을 전송함. (서버와 연결은 끊지 않고 이 메서드 호출 후 unconnect() 메서드로 연결 해제. 다만 채팅방에 퇴장 알림은 조용히 퇴장 여부에 따라서 띄움. 메서드명이 적절치 않은 것 같아 unconnect()를 호출할지 메서드명을 바꿀지 고민했다가 메서드명을 바꾸는게 낫다고 생각했으나 적절한 이름을 생각하지 못함.)
      - sendMessage(String message) : 채팅방에 메시지를 전송하는 메서드
    - receive() : 서버로부터 메세지를 받아서 파싱하고 유저에게 띄우는 메서드. 메세지 전송과 수신을 동시에 하기 위해 스레드를 생성. 메인 메서드가 종료되면 함께 종료되도록 데몬 스레드로 설정. 서버와 연결을 끊겨 예외가 발생했을 경우 프로세스를 종료시킴.
  - 스레드 : 서버로부터 데이터를 수신하는 메서드를 메세지 발신과 동시에 실행될 수 있도록 receive() 메서드에 스레드를 생성하여 분리하였음.
  - 메인 메서드(실행)
    - 포트번호 55555로 "localhost"에 열린 서버와 연결.
    - Scanner을 생성하여 유저가 대화명을 입력하도록 하여 chatName에 저장한 후 채팅방 입장.
    - receive()를 호출(스레드가 별도로 생성됨)
    - 유저로부터 (콘솔창에서) 메세지를 입력받아서 메세지 전송.
    - exit만 입력했을 경우 채팅모드를 종료하고 조용히 퇴장 여부 확인. y 입력시 조용히 퇴장, n 입력시 일반 퇴장, 기타 다른 것들을 입력했을 경우 퇴장을 취소하고 채팅모드로 돌아감.
    - 퇴장을 선택하였을 경우 Scanner를 닫고 서버와 연결을 끊음.
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223122712567)


# JS Validation API
- 과제로 받았던 것을 좀 더 발전시켜서 Web API를 적용해본 것
- 개발 환경 : VSCode
- HTML : 아이디, 비밀번호, 비밀번호 확인, 이름 칸과 회원가입 버튼으로 이루어진 구조
- CSS : 기능 구현 중심으로 하였기 때문에 스타일상의 특이사항은 없음. 다만 다른 블로그를 참고하여 :invalid를 추가하였는데 유효하지 않을 때 스타일이 바로 적용되는 것이 아니라 회원가입 버튼을 적어도 한번은 눌러야 스타일이 적용되도록 되어 있음.
- JS
  - 구조는 먼저 공통적으로 사용하는 값(유효성 상태, 유효성 검사 결과 메세지)를 모아두었고, 그 값들을 변경할 수 있는 메서드(원할 때마다 유효성 검사를 해서 결과값을 가져다 쓸수 있게 하기 위함)도 함께 만들었음.
  - 회원가입 버튼과 무관하게 실시간으로 동작하는 유효성 검사
    - 사용자가 값을 입력했을 때 아이디(임의의 값)의 중복을 체크하거나 비밀번호와 비밀번호 확인의 값들이 일치하는지 여부를 확인해서 밑에 문구가 추가되는 이벤트의 함수를 단계적으로 나누어 만들었음. 이는 사용자가 값을 입력할 때마다 실시간으로 확인됨.
    - 아이디와 비밀번호 확인칸에 조건에 따라 이벤트를 입력해주었고 비밀번호 확인칸의 이벤트는 너무 길어져서 함수로 만들어주었음.
  - 회원가입 버튼을 클릭한 후 유효성 검사
    - 회원가입 버튼을 누르고 나서는 일단 유효성 검사가 수행되었다는 마커 역할을 하는 클래스를 각 칸들에 넣어주었고 유효성 검사를 수행. 유효성 검사에 통과하지 못하면 검사를 멈추고 사용자에게 그 이유를 알려주는 알림을 띄움. Validation API를 활용해서 커스텀 메세지를 띄우는 방법을 사용하였으나 브라우저 툴팁을 중단시키고 alert를 이용하는 방법도 만들어두었음(주석처리 되어있음). 유효성 검사는 모두 동일한 함수로 동작하는데 체크하고자 하는 유효성 타입을 받아서 상태를 확인하고 만약 유효하지 않을 경우 알림을 띄우는 함수와 이 때 사용할 메세지를 불러오는 함수를 만들어 두어 유효성 검사가 되도록 일괄적으로, 동일한 형태로 동작하도록 만들어주었음.
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223140316672)


# SimpleBulletinBoard
- 과제로 받았던 것을 좀 더 발전시켜서 단순 구현이 아니라 진짜 간이 게시판으로 기능할 수 있도록 여러 기능을 추가해본 것
- 개발 환경 : VSCode
- HTML : 게시글 작성하는 부분, 작성한 글들이 표 형태의 목록으로 나오는 부분, 게시글 내용이 나오는 부분
    - 게시글 작성하는 부분
      - 폼이 있는 곳으로 제목, 작성자, 내용을 입력하고 파일을 첨부할 수 있는 형태로 되어있음.
      - 제목, 작성자, 내용은 필수항목, 첨부파일은 선택. png, jpg만 올릴 수 있고 또 여러 개를 올릴 수 있음.
      - 버튼은 게시글을 올리는 작성하기와 쓴 폼에 작성한 글들을 모두 비우는 삭제하기가 있음. 기본적으로 비활성화되어 있는데 값이 하나라도 입력되어 있어야 삭제하기가, 필수 항목이 모두 입력되어 있어야 작성하기가 활성화됨.
    - 게시된 글들 목록이 표로 나오는 부분
      - 게시글이 업로드된 순서인 번호와 게시글 제목, 작성자를 받아서 표로 만들고 작성일은 작성하기를 누른 시점으로 업로드됨. 조회수는 다양하게 해주고 싶어서 랜덤한 값이 들어가도록 하였음.
      - 게시글 제목 옆에는 첨부파일 버튼이 생성되는데 첨부파일이 있으면 활성화되고 없으면 비활성화된 채로 생성됨. 기능은 따로 넣지 않았음. 또한 게시글 제목을 누르면 해당 게시글을 확인할 수 있음.
      - 전체 게시글 수는 게시글 입력 후 작성하기를 누르거나 게시글 내용 확인 후 삭제하기를 누르면 현재 게시글 수에 맞추어 업데이트됨.
      - 게시글은 총 5개까지만 작성할 수 있음.
    - 게시글 내용이 나오는 부분(라이트 박스 형태)
      - 입력하고 전송한 제목과 작성자, 내용이 화면에 출력됨. 첨부파일은 그냥 파일이 없으면 '파일없음'이, 있으면 파일의 개수가 출력되도록 하였음.
      - 또한 뒤로가기와 삭제하기를 넣어 뒤로가기를 눌렀을 때 게시글 화면이 없어지고 삭제하기를 눌렀을 때는 화면이 없어지는 것과 더불어 게시글 표에서도 해당 내용이 없어짐.
- CSS : 기능 중심이라 기본적인 배치만 해주었음. 다만 게시글이 팝업이 아니라 라이트박스 형태이므로 기본 값으로 안보이도록 설정해주었음.
- JS
  - 처음 화면 설정은 작성하기와 삭제하기 버튼이 비활성화 되어 있고 전체 게시글 수가 집계된 후 뜸
  - 처음 화면이 로드되면 각 버튼은 비활성화된 상태로 뜨며 게시글 개수가 집계되어 뜸(0개)
  - 작성하기 버튼을 클릭했을 때 이미 작성된 게시글(삭제된 글 제외)이 5개면 알림만 뜨고 동작하지 않고 그렇지 않으면 다시 처음 화면 설정으로 뜨되 게시글 관련 행을 만들어 추가하고 게시글 개수가 다시 집계되어 뜸
  - 삭제하기 버튼을 클릭했을 때 값들을 비우고 값이 비어있는지 아닌지를 관리해주기 위한 배열도 비워버리고 처음 화면 설정으로 바꿔줌
  - 버튼의 활성화/비활성화를 위해 값이 있는 input 요소를 담아 관리할 notEmptyInput 배열을 만들어 둠
  - 각 칸들에 작성하기와 삭제하기 버튼을 활성화/비활성화시키기 위해 input과 click 이벤트를 추가함
  - 작성하기 버튼은 칸에 입력값이 있을 때 배열에 해당 input 요소가 없으면 추가해주고 또 필수항목이 모두 배열안에 있을 때 작성하기 버튼을 활성화 시켜줌. 만약 값이 없는데 배열 내부에 해당 요소가 있으면 배열 내부에서 삭제함.(즉 모든 필수 입력칸에 값이 있을 때만 버튼이 활성화되고 하나라도 값이 비면 비활성화됨)
  - 삭제하기 버튼은 값이 입력되면 활성화되고 값이 없는데 배열이 비어있을 경우에는 비활성화됨.
  - 작성하기 버튼 클릭후 게시하는 기능이 담긴 함수
    - 게시판 관리용 배열과 게시판 및 게시글 관리용 번호를 만들어줌
    - 관련된 데이터들의 수정이 쉽도록 사용자가 입력한 정보와 기타 게시판 표에 만들어줄 정보를 모아서 객체로 만들어두고 게시판 관리용 배열에 담아둠
    - 사용자가 입력한 값들로 표를 만들고 입력칸들을 모두 비워줌.
    - 표가 생성되었을 때 제목칸을 클릭했을 때 게시글이 화면에 라이트박스 형태로 뜨도록 내용을 띄워주는 이벤트 생성.
      - 게시글을 화면에 띄울 때 화면을 구성하는 요소들을 담은 객체 생성
      - 제목 옆의 (일련)번호와 각 행(게시글 하나를 담은 행)을 담은 배열 내부 객체의 일련번호가 일치할 때 객체와 배열 내부 인덱스 번호를 가져옴.
      - 배열에서 가져온 객체의 값들을 해당하는 위치에 뜨도록 함.
      - 게시판 제목을 클릭하면 게시글을 보여주고 뒤로가기 버튼을 클릭하면 게시판 내용을 다시 숨기는 이벤트를 넣어둠
      - 삭제하기 버튼을 누르면 게시판 내용이 안보이고 해당 게시글이 게시판 표에서 삭제됨(게시글을 관리하는 배열에서 지우고 테이블과 연결된 행의 노드도 삭제하고 전체 게시판 개수도 업데이트해줌.
    - 전체 게시글 수를 업데이트해주는 함수를 만듦(게시글을 관리하기 위해 각 행의 정보를 담아둔 배열 내부 요소의 개수를 출력하는 방식)
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223139630336)


# CalculatorAboutFigure1
- 과제로 설계도만 받은 것을 구현해본 것.
- 개발 환경 : IntelliJ(+외부 라이브러리 : Lombok)
- 원과 사각형의 길이 정보를 받아 둘레나 넓이 값을 계산해서 출력해줌.
- 입력한 메뉴 번호에 따라 메서드를 호출하는 방식으로 리플렉션을 활용함.
- model : Point(점 좌표에 대한 데이터 형식), Circle(원에 대한 데이터 형식), Rectangle(사각형에 대한 데이터 형식)
  - Point : 점에 대한 x, y 좌표값을 가지고 있음(Getter, Setter, toString, equals, hashCode, 생성자 등은 Lombok으로 생성함)
    - Circle : Point를 상속함. Circle은 원에 대한 데이터로 상속받은 것 이외에 반지름(radius) 데이터를 추가적으로 가지고 있음.(기타 필요한 것들은 위처럼 Lombok으로 생성함)
    - Rectangle : Point를 상속함. Rectangle은 사각형에 대한 데이터로 상속받은 것 이외에 너비(width)와 높이(height) 데이터를 추가적으로 가지고 있음(기타 필요한 것들은 위처럼 Lombok으로 생성함)
- controller : CircleController(원에 관한 연산), RectangleController(사각형에 관한 연산)
  - CircleController : 원에 관해 연산할 수 있는 메서드가 정의된 클래스. Circle을 생성하고 데이터를 입력하여 그 데이터를 기반으로 원주와 넓이를 계산함.
  - RectangleController : 사각형에 관해 연산할 수 있는 메서드가 정의된 클래스. Rectangle을 생성하고 데이터를 입력하여 그 데이터를 기반으로 둘레와 넓이를 계산함.
- view : PointView(사용자에게 메뉴를 보여주고 메뉴번호 또는 도형 데이터를 입력받아 적절한 연산 수행 및 결과 출력)
  - PointView : 필드로 사용자에게 입력을 받는 Scanner와 Circle, Rectangle 컨트롤러를 생성해두고 이를 사용. 메서드는 메뉴를 그리고 사용자의 입력값에 따라 결과를 출력하는 메서드와 반복되는 작업을 한번만 정의해두고 재사용하기 위해 분리한 메서드로 나뉘어짐
    - 사용자의 입력값에 따라 메뉴/연산결과 출력
      - 인자로 함수를 받아 리플렉션 방식으로 해당 메서드를 호출하는 메서드
        - private <T extends Point> Void controlMenu(String func, Point figure) : 유연하게 콜백함수를 쓸수는 없을까 고민하다가 만들어본 리플렉션 방식의 함수 호출 메서드. 메인 메뉴와 세부 메뉴에서 모두 사용할 수 있도록 하였으며 매개값으로 넣어야하는 객체가 다르기 때문에 필요한 부분만 다운캐스팅하여 사용함.
        - private void controlMenu(String func) : 메인메뉴는 도형이 필요없기 때문에 위의 함수를 오버로딩한 것. 도형 부분만 null 값으로 입력하고 매개값을 전달하여 위의 함수를 호출함.
      - 메뉴 구성하고 입력값을 받음
        - public void mainMenu() : 처음에 출력되는 메뉴. 메뉴 번호에 해당하는 값을 입력받아 해당 메뉴로 이동시킴. 메뉴를 호출할 때는 메뉴 번호와 메서드 이름으로 구성된 Map을 만들어 사용자의 입력값에 따라 해당하는 메서드 정보를 전달함.
        - public void circleMenu() : 메인 메뉴에서 원에 해당하는 메뉴를 입력했을 때 호출되는 메뉴. 메뉴 번호에 해당하는 값을 입력받아 해당 메뉴로 이동시킴. 메뉴를 호출할 때는 도형의 구성요소(반지름)와 메뉴 번호를 전달함.
        - pulbic void rectangleMenu() : 메인 메뉴에서 원에 해당하는 메뉴를 입력했을 때 호출되는 메뉴. 메뉴 번호에 해당하는 값을 입력받아 해당 메뉴로 이동시킴. 메뉴를 호출할 때는 도형의 구성요소(너비, 높이)와 메뉴 번호를 전달함.
        - private void inputCircleCalcOutline(String msg, String menuNo) : 좌표와 반지름 값을 입력받은 후 메서드 호출. 이때 사용자가 원하는 연산 정보(메서드)와 도형 정보(좌표, 반지름 값)를 매개값으로 전달함.
        - private void inputRectCalcOutline(String msg1, String msg2, String menuNo) : 좌표와 너비, 높이 값을 입력받은 후 메서드 호출. 이때 사용자가 원하는 연산 정보(메서드)와 도형 정보(좌표, 너비, 높이 값)를 매개값으로 전달함.
      - 사용자가 원하는 연산을 수행하기 위해 컨트롤러에서 관련된 메서드 호출
        - public void calcCircum(Circle c) : 컨트롤러(CircleController)의 원주를 계산하는 메서드 호출하여 도형 정보 출력
        - public void calcCircleArea(Circle c) : 컨트롤러(CircleController)의 원의 넓이를 계산하는 메서드를 호출하여 도형 정보 출력
        - public void calcPerimeter(Rectangle r) : 컨트롤러(RectangleController)의 사각형의 둘레를 계산하는 메서드를 호출하여 도형 정보 출력
        - public void calcRectArea(Rectangle r) : 컨트롤러(RectangleController)의 사각형의 면적을 계산하는 메서드를 호출하여 도형 정보 출력
      - public void exit() : 프로그램 종료
    - 편의성을 위해 반복작업을 분리한 메서드
      - private void showMenuOutline(String msg) : 메뉴의 틀을 만들어 콘솔창에 출력
      - private String figureInfoMenu(String type) : 도형의 메뉴 틀을 만들어 문자열로 반환
      - private Map<String, String> makeMenuMap(String...menu) : 메뉴 내용들을 매개값으로 받아 메뉴 번호와 메뉴 내용을 쌍으로 묶어 Map으로 만들어 반환
- run : Run(프로그램 진입점)
  - Run : PointView를 생성하고 mainMenu를 호출함. 프로그램 진입점 생성.
- 설계도대로 하고 보니 점과 도형의 관계가 잘못되어 있고 점의 정보를 사용하지 않아 점 관련 데이터가 무의미해지는 한계를 발견하여 Version2(CalculatorAboutFigure2)를 다시 만들었음.
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223162893588)


# CalculatorAboutFigure2
- 과제로 설계도를 받은 것에 아래와 같은 문제점을 개선하여 다시 만든 것.
  - Version1은 원과 사각형이 도형이 아니라 원을 상속하고 있었음. 그러나 점은 각 도형들의 구성 요소(멤버변수)에 가깝지 상위 클래스라고 보기에는 부적절함.
  - 점을 클래스로도 만들고 입력값도 받으나 사실상 형식적이었음. 이를 활용해서 연산하는 것은 없었을 뿐더러 원이나 사각형과 관련된 연산을 하기 위해서는 점 좌표가 두쌍 이상은 필요하지만 사용자에게 입력받는 값은 한쌍 뿐임.
- 개발 환경 : Eclipse(+외부 라이브러리 : Lombok)
- model
  - Point : 좌표 x와 y로 구성된 점에 대한 클래스(Getter, Setter, toString, equals, hashCode, 생성자 등은 Lombok으로 생성함)
  - Figure : 도형을 구성하는 두 개의 점과 면적, 둘레를 멤버변수로 가진 도형에 관한 추상 클래스.
    - 생성자는 기본 생성자 이외에 Point 타입 변수 두 개를 매개값으로 받아 멤버변수를 초기화하는 생성자가 있음.
    - protected abstract void checkPoint(Point p1, Point p2) : Point 타입 변수가 도형을 구성하기에 적합한지 확인하는 추상 메서드
    - public void setPoint1(Point p1) : 멤버 변수 point2 값이 있다면 매개값으로 받은 p1이 도형을 그리기에 적절한지 체크하고 유효하면 멤버변수 point1에 값을 할당함(point2 값이 없으면 그냥 p1 값을 할당함)
    - public void setPoint2(Point p2) : 멤버 변수 point1 값이 있다면 매개값으로 받은 p2이 도형을 그리기에 적절한지 체크하고 유효하면 멤버변수 point2에 값을 할당함(point1 값이 없으면 그냥 p1 값을 할당함)
    - protected void checkLength(double length) : 길이가 유효한 값인지 확인하여 0 이하일 경우 예외 발생
    - public String toString() : Point 타입의 값들 뿐만 아니라 면적과 둘레도 출력할 수 있도록 재구성함
    - 이외의 Getter, Setter, equals 등은 Lombok으로 생성
    - Circle : Figure 클래스를 상속하는 원에 관한 클래스.
      - Circle 클래스 독자적으로 반지름에 해당하는 radius라는 멤버 변수를 가지고 있음
      - 생성자는 기본 생성자 이외에 Point 타입 변수 두 개를 받아 멤버 변수를 초기화하는 생성자와, 반지름만 받아 내부 반지름 값만 초기화하는 생성자가 있음
      - protected void checkPoint(Point p1, Point p2) : 오버라이딩한 메서드. Point 타입의 변수 두개를 매개값으로 받아 두 좌표가 동일한 위치에 있으면 예외를 발생시킴.
      - public void setRadius(Point p1, Point p2) : Point 타입 변수 두개를 매개값으로 받아 두 점이 유효한지 확인(checkPoint()). 그 후 각 점을 초기화하고 두 점의 좌표로 반지름을 구하여 radius 값도 초기화함
      - public void setRadius(double radius) : 반지름 값을 받아 길이가 유효한지 확인(checkLength())한 후 radius 값을 초기화함.
      - public String toString() : 오버라이딩한 메서드. 상위 클래스의 변수값들과 더불어 반지름과 도형 타입도 출력되도록 함.
      - 이외의 Getter, Setter, equals 등은 Lombok으로 생성
    - Rectangle : Figure 클래스를 상속하는 사각형에 관한 클래스
      - Rectangle 클래스 독자적으로 너비와 높이에 해당하는 width, height 멤버 변수를 가지고 있음
      - 생성자는 기본 생성자 이외에 Point 타입 변수 두 개를 받아 멤버 변수를 초기화하는 생성자와, 너비와 높이 값만 받아 필드 width, height만 초기화하는 생성자가 있음
      - protected void checkPoint(Point p1, Point p2) : 오버라이딩한 메서드. 두 점의 좌표 중 하나라도 동일하여 사각형을 그릴 수 없으면 예외 발생.
      - public void setWidth(double width) : 너비 값을 받아 길이가 유효한지 확인(checkLength())한 후 내부 필드 초기화.
      - public void setWidth(Point p1, Point p2) : Point 타입 두 점을 받아 도형을 그리기에 유효한 점인지 확인(checkPoint())한 후 유효하면 너비 값을 구하여 저장함.
      - public void setHeight(double height) : 높이 값을 받아 길이가 유효한지 확인(checkLength())한 후 내부 필드 초기화.
      - public void setHeight(Point p1, Point p2) : Point 타입 두 점을 받아 도형을 그리기에 유효한 점인지 확인(checkPoint())한 후 유효하면 높이 값을 구하여 저장함.
      - public String toString() : 오버라이딩한 메서드. 상위 클래스의 변수값들과 더불어 너비와 높이, 도형 타입도 출력되도록 함.
      - 이외의 Getter, Setter, equals 등은 Lombok으로 생성
- controller
  - FigureController : 도형을 컨트롤하기 위한 컨트롤러 역할을 하는 추상 클래스.
    - 필드로 Figure 타입의 상속 가능한 변수를 선언함
      - Getter는 Lombok으로 생성
      - Setter는 Figure 타입 매개변수를 받아 다운캐스팅(downCasting())하여 필드에 대입함. 또는 Point 타입 두 변수를 받아서 필드의 Point 타입 변수를 초기화함.
    - protected abstract Figure downCasting(Figure figure) : Figure 타입 매개값을 받아 다운캐스팅하기 위한 메서드. 하위 클래스에서 필요한 타입으로 다운캐스팅하기 위해 추상 메서드로 선언.
    - public String toString() : 오버라이딩한 메서드. 해당 클래스를 상속하는 하위 클래스에서 다운캐스팅한 형태로 출력되는 값을 정의함.
    - 면적을 계산하는 calcArea()와 둘레를 계산하는 calcPeri()는 추상 메서드로 선언하고 Point를 받아 계산할수도 있도록 하기 위해 같은 메서드를 오버로딩함.
    - 둘레와 면적을 모두 계산하는 메서드도 오버로딩하여 여러 개 생성함. 필요에 따라 내부 값을 초기화한 후 연산을 수행.
    - CircleController : FigureController를 상속하는 원을 컨트롤하기 위한 컨트롤러 역할을 하는 클래스.
      - 매개변수를 받지 않는 생성자에 figure 타입 필드를 Circle 타입으로 생성하여 초기화함
      - Circle 타입의 변수에만 있는 메서드를 사용하기 위해 필요한 부분마다 downCasting() 호출하여 다운캐스팅하여 사용
      - 상위 클래스에서 정의할 수 없는 원만 가지고 있는 반지름에 대한 부분을 정의하고 연산하기 위한 부분만 메서드를 오버라이딩함.
    - RectangleController : FigureController를 상속하는 사각형을 컨트롤하기 위한 컨트롤러 역할을 하는 클래스.
      - 매개변수를 받지 않는 생성자에 figure 타입 필드를 Rectangle 타입으로 생성하여 초기화함
      - Rectangle 타입의 변수에만 있는 메서드를 사용하기 위해 필요한 부분마다 downCasting() 호출하여 다운캐스팅하여 사용
      - 상위 클래스에서 정의할 수 없는 사각형만 가지고 있는 너비와 높이에 대한 부분을 정의하고 연산하기 위한 부분만 메서드를 오버라이딩함.
- view
  - FigureMenu : 메뉴를 출력하고 사용자에게 입력을 받아 해당하는 결과가 출력되도록 구성된 클래스
    - 필드는 사용자에게 계속 입력을 받을 Scanner 타입의 변수와 도형과 관련된 정보를 좀 더 쉽게 컨트롤하기 위해 내부 클래스를 선언하고 원과 사각형에 해당하는 변수를 선언, 또한 이를 담을 Map 타입 변수 선언. 콘솔창에서 사용자에게 입력받은 값들을 저장할 Map도 선언해둠.
    - 초기화 블록에 원과 사각형에 대한 값들을 초기화해두고 이를 다시 Map에 유사배열 형태로 초기화함. 도형 관련하여 입력받을 값들을 저장할 Map도 생성해둠.
    - 사용자에게 입력받은 값을 정수/실수 형태로 변환
      - private int inputCastInt() : int형으로 변환해서 반환
      - private double inputCaseDouble() : double형으로 변환해서 반환
    - 매개값에 따라서 메뉴 내용을 구성해서 문자열 형태로 반환
      - private String makeMainMenuContent(String type, String cont) : 도형 타입(원/사각형)과 메뉴 내용을 매개값으로 받아 메뉴 틀을 만들어서 문자열 형태로 반환
      - private String makeFigureMenuContent(String type) : 도형 타입(원/사각형)을 매개값으로 받아 도형 메뉴 틀을 만들어서 문자열 형태로 반환
    - 사용자에게 도형과 관련된 값을 요청하고 받아오는 메서드
      - private double inputNumber(String val) : 매개값으로 사용자에게 받으려는 값이 무엇인지를 문자열 형태로 받아 콘솔창에 출력하고 입력값을 받아옴. 받은 값은 double형으로 변환하여 반환함.
    - 메뉴를 출력하고 사용자가 원하는 메뉴 번호를 입력받아 해당하는 메뉴/입력창으로 이동해줌
      - public void mainMenu() : 처음 출력되는 기본메뉴. 메뉴를 출력하고 값을 입력받아 원하는 메뉴로 매핑시켜줌. 사용자가 종료를 원하여 3을 입력할 경우 프로그램이 종료됨.
      - private void figureMenu() : 도형과 관련된 세부 메뉴. 연산을 원하는 값을 선택하면 그에 따라 해당하는 메뉴로 매핑시켜줌. 사용자가 메인메뉴로 돌아가길 원하여 4를 입력할 경우 값들이 들어있는 Map을 비워주고 메인 메뉴로 이동시켜줌.
      - private void figureInfoMenu(int figureType, String calcType) : 사용자에게 점 좌표를 기준으로 계산할지, 도형의 길이를 기준으로 계산할지를 물어보는 메뉴를  출력하고 입력값에 따라 해당하는 연산이 수행되도록 함. 점 기준으로 계산하는 1을 누르면 점을 두개 만들고 점 기준 연산을 수행함. 길이를 기준으로 계산하는 2를 누르면 길이 값들을 받아 길이를 기준으로 연산을 수행함.
      - private void callCalc(int figureType, String calcType, String inputType) : 도형 정보와, 계산 기준과 어떤 결과를 출력하길 원하는지를 매개값으로 받아 해당하는 연산을 수행한 후 결과를 출력하도록 함. 연산이 끝나고 결과가 출력된 후에는 값들이 저장된 Map을 비우고 메인 메뉴로 이동함.
    - 연산을 수행하는 메서드
      - private FigureController calcByPoints(int figureType) : 점 좌표를 기준으로 연산을 수행하는 메서드. 매개값으로 받은 도형의 종류에 따라 연산을 수행한 후 해당하는 컨트롤러를 반환함.
      - private FigureController calcByLength(int figureType) : 길이 값을 기준으로 연산을 수행하는 메서드. 매개값으로 받은 도형의 종류에 따라 연산을 수행한 후 해당하는 컨트롤러를 반환함.
    - 사용자의 입력값에 따라 해당하는 점, 도형, 컨트롤러 매핑하여 생성
      - private void makePoint(int no) : 점(Point) 두개를 생성(매개값으로 받는 숫자는 몇 번째 점인지 표시해주기 위한 것)
      - private FigureController makeController(int figureType) : 입력받은 도형 종류(1-원, 2-사각형)에 따라 해당하는 컨트롤러를 생성하여 반환함.
      - private Figure makeFigure(int figureType) : 입력받은 도형의 종류(1-원, 2-사각형)에 따라 관련된 도형의 정보를 초기화하고 해당하는 컨트롤러를 생성하여 반환함.
- run : Run(프로그램 진입점)
  - Run : FigureMenu를 생성하고 mainMenu를 호출함. 프로그램 진입점 생성.
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223162893588)


# SimpleLibrary
- 과제로 받은 것에서 기능을 좀더 개선하거나 추가하여 만들어본 것
- 개발 환경 : Eclipse(+외부 라이브러리 : Lombok)
- model
  - Member : 도서관 프로그램 사용자의 정보가 담기는 형태를 정의한 클래스
    - 멤버 번호(자동생성됨), 이름, 연령, 성별, 쿠폰개수, 빌린 책 목록이 정의되어 있음.
    - 기타 필요한 생성자, Getter, Setter, toString 등은 Lombok으로 정의함
  - Book : 도서관에 소장중인 책에 대한 정보가 저장되는 형태를 정의한 클래스
    - 책 번호(자동생성됨), 책 제목, 저자, 출판사, 대출가능여부(생성시 기본값 true)로 구성됨.
    - 기타 필요한 생성자, Getter, Setter, toString 등은 Lombok으로 정의함
  - AniBook : Book을 상속하는 만화책 클래스.
    - 추가로 제한연령에 관한 필드가 있음.
    - toString()에서 타입(AniBook)과 제한연령(AccessAge)에 관한 정보도 추가로 출력함.
    - 기타 필요한 생성자, Getter, Setter, toString 등은 Lombok으로 정의함
  - CookBook : Book을 상속하는 요리책 클래스.
    - 추가로 요리학원 쿠폰 발급 유무에 대한 정보가 있는 필드가 있음.
    - toString()에서 타입(CookBook)과 쿠폰발급유무에 대한 정보가 추가로 출력됨.
    - 기타 필요한 생성자, Getter, Setter, toString 등은 Lombok으로 정의함
- controller
  - LibraryController : 도서관 시스템의 기능에 관해 컨트롤하는 클래스
    - 도서관 회원을 담은 Map과 소장도서를 담은 Map을 필드로 가지고 있음
    - public void insertMember(Member mem) : 회원이 가입했을 때 멤버 목록에 추가하기 위한 메서드. 매개값으로 Member타입 변수를 받아 회원 Map에 멤버를 추가함. 가입된 회원이 없을 때는 일단 Map을 먼저 생성하고 추가함.
    - public Member memInfo(int memNo) : 멤버의 회원번호를 매개값으로 받아 해당하는 멤버를 반환해줌. 마이페이지에 정보를 출력하기 위한 메서드로 멤버를 Member 타입으로 반환해줌.
    - public void insertBook(Book book) : 소장 도서를 추가하기 위한 메서드. Book 타입의 변수를 매개값으로 받아 소장도서 Map에 도서를 추가함. 만약 소장도서가 없으면 먼저 Map을 생성한 후에 추가함.
    - public Map<Integer, Book> selectAll() : 소장도서 전체를 Map 타입으로 반환하는 메서드.
    - public Map<Integer, Book> searchBook(String keyword) : 검색하려는 키워드를 매개값으로 받아 소장도서에 키워드가 있는지 검색하는 메서드. 책 제목이나 저자나 출판사에 키워드가 있으면 값을 Map에 담아 반환함(List 형태로 반환해도 괜찮을 것 같음. stream을 사용해서 Map으로 만드는 방식을 저장해두려고 바꾸지 않음)
    - public List<Book> rentBook(Member mem, int...bookNoes) : 멤버 정보와 빌리려는 도서의 번호들을 입력하면 소장 도서에 있는 책인지, 대출 가능 개수를 초과하지는 않았는지, 연령제한에 문제가 되지는 않는지를 확인함. 만약 하나라도 문제가 되면 null을 반환하고 문제가 없으면 해당 도서 번호를 ArrayList에 추가함. 빌리려는 모든 책에 대한 대출 가능 여부 확인이 끝나고 문제 없으면 대출하려는 책을 담은 ArrayList를 반환함(+대출가능 여부를 확인하는 프로세스를 추가해야 함)
    - public boolean isAbleToBorrow(Member mem, int numOfBooks) : 멤버와 빌리려는 책의 개수를 매개값으로 받아 해당 회원이 대출중인 도서와 빌리려는 책의 개수의 합이 5권을 초과하는지 확인. 초과하면 true, 초과하지 않으면 false 반환.
    - public boolean borrowBooks(Member mem, int...bookNoes) : 멤버와 빌리려는 책들의 번호를 매개값으로 받아 rentBook() 메서드로 대출가능 여부를 확인하여 대출 가능한 책 목록을 전달받은 후 null값이면(대출 불가능한 사유가 있으면) 대출 실패라는 의미의 false를 반환. 대출 가능한 책 목록에 책이 있으면 멤버의 대출 목록에 해당 도서들을 추가하고 true를 반환함.
    - public int issueCoupon(int...bookNoes) : 책 번호 목록을 받아 쿠폰을 발큽해주는 책이 있으면 개수를 모두 세서 발급하려는 쿠폰 개수를 반환함.
    - public boolean checkAccessAge(Member mem, int...bookNoes) : 멤버와 책 번호 목록을 받아서 만약 해당 책이 만화책일 경우 나이연령을
    - public boolean returnBooks(Member mem, int...bookNoes)
- view
  - LibraryMenu : 
- run
  - Run : 
- 기타 과정에 대한 것들 기록(https://blog.naver.com/biyoonx/223162893588)
