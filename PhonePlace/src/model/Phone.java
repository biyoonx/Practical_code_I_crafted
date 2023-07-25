package model;

public interface Phone {
	char[] NUMBERPAD = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '*', '0', '#'};
	
	public default String makeCall() {
		return "번호를 누르고 통화버튼을 누름";
	}
	public default String takeCall() {
		return "수신 버튼을 누름";
	}
}
