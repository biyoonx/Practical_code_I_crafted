package model;

public class GalaxyNote9 extends SmartPhone implements NotePen {
	public GalaxyNote9() {
		this.setMaker("삼성");
		this.setPhoneName("갤럭시 노트9");
	}
	
	@Override
	public String charge() {
		return "고속 충전, 고속 무선 충전";
	}

	@Override
	public String picture() {
		return "1200만 듀얼 카메라";
	}

	@Override
	public String touch() {
		return "정전식, 와콤펜 지원";
	}

	@Override
	public boolean bluetoothPen() {
		return true;
	}
}
