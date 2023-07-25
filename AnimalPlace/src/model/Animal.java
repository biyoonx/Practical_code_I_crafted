package model;

import lombok.Data;

@Data
public abstract class Animal {
	private String name;
	private String type;
	
	protected Animal() {}
	protected Animal(String name, String type) {
		this.setName(name);
		this.setType(type);
	}
	
	public abstract void speak();
	
	public String toString() {
		return name + "(" + type + ")";
	}
}
