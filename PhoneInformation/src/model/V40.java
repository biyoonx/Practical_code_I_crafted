package model;

public class V40 extends SmartPhone implements NotePen {
	public V40() {
		this.setMaker("LG");
		this.setPhoneName("V40");
	}

	@Override
	public String charge() {
		return "고속 충전, 고속 무선 충전";
	}

	@Override
	public String picture() {
		return "1200만, 1600만 트리플 카메라";
	}

	@Override
	public String touch() {
		return "정전식";
	}

	@Override
	public boolean bluetoothPen() {
		return false;
	}
}
