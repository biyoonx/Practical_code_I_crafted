package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Cat extends Animal {
	private String location;
	private String color;
	
	public Cat(String name, String type, String location, String color) {
		super(name, type);
		this.setLocation(location);
		this.setColor(color);
	}

	@Override
	public void speak() {
		String content = super.toString() + " : 위치 - " + location + ", 색깔 - " + color;
		System.out.println(content);
	}
}
