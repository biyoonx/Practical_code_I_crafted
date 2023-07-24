package model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AniBook extends Book {
	private int accessAge;
	
	public AniBook(String title, String author, String publisher, int accessAge) {
		super(title, author, publisher);
		this.setAccessAge(accessAge);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", type=AniBook, accessAge=" + this.getAccessAge();
	}
}
