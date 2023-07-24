package model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
	private static int serialNo = 0;
	private int bookNo;
	private String title;
	private String author;
	private String publisher;
	private boolean isAbleToBorrowed;
	{
		this.setBookNo(++serialNo);
		this.setAbleToBorrowed(true);
	}
	public Book(String title, String author, String publisher) {
		this.setTitle(title);
		this.setAuthor(author);
		this.setPublisher(publisher);
	}
}
