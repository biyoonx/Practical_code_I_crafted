package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Dog extends Animal {
	public static final String PLACE = "애견카페";
	@Getter
	@Setter
	private int weight;
	
	public Dog(String name, String type, int weight) {
		super(name, type);
		this.setWeight(weight);
	}

	@Override
	public void speak() {
		String content =  super.toString() + " : 위치 - " + PLACE + ", 몸무게 - " + weight;
		System.out.println(content);
	}
}
