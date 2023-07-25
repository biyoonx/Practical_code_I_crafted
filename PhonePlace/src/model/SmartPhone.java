package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class SmartPhone implements Cellphone, TouchDisplay {
	private String phoneName;
	private String maker;
	
	public String printInformation(boolean bluetoothPen) {
		String hasBluetoothPen = (bluetoothPen) ? "O" : "X";
		StringBuilder info = new StringBuilder();
		info.append(this.getPhoneName()).append("는/은 ").append(this.getMaker()).append("에서 만들어졌고 제원은 다음과 같다.").append("\n")
			.append(this.makeCall()).append("\n")
			.append(this.takeCall()).append("\n")
			.append(this.picture()).append("\n")
			.append(this.charge()).append("\n")
			.append(this.touch()).append("\n")
			.append("블루투스 펜 탑재 여부 : ").append(hasBluetoothPen);
		return info.toString();
	}
}
