package model.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
	private static int serialNo = 0;
	private int memNo;
	private String name;
	private int age;
	private char gender;
	private int couponCount = 0;
	private List<Book> borrowedBList = new ArrayList<Book>(5);
	{
		this.setMemNo(++serialNo);
	}
	
	public Member(String name, int age, char gender) {
		this.setName(name);
		this.setAge(age);
		this.setGender(gender);
	}
}
