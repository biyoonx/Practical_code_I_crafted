package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.vo.AniBook;
import model.vo.Book;
import model.vo.CookBook;
import model.vo.Member;

public class LibraryController {
	private Map<Integer, Member> memList = null;
	private Map<Integer, Book> allBList = null;
	
	public void insertMember(Member mem) {
		memList = Optional.ofNullable(memList).orElseGet(() -> new HashMap<Integer, Member>());
		memList.put(mem.getMemNo(), mem);
	}
	public Member memInfo(int memNo) {
		return memList.get(memNo);
	}
	public void insertBook(Book book) {
		allBList = Optional.ofNullable(allBList).orElseGet(() -> new HashMap<Integer, Book>());
		allBList.put(book.getBookNo(), book);
	}
	
	public Map<Integer, Book> selectAll() {
		return allBList;
	}
	public Map<Integer, Book> searchBook(String keyword) {
		return allBList.entrySet().stream().filter(book -> 
			book.getValue().getTitle().contains(keyword)
			|| book.getValue().getAuthor().contains(keyword)
			|| book.getValue().getPublisher().contains(keyword)
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
	
	public List<Book> rentBook(Member mem, int...bookNoes) {
		List<Book> rentBList = new ArrayList<Book>();
		for (int no : bookNoes) {
			if (!(allBList.containsKey(no))) {
				return null;
			}
			Book book = allBList.get(no);
			if (!(book.isAbleToBorrow())) {
				return null;
			}
			if (!(checkAccessAge(mem, bookNoes))) {
				return null;
			}
			book.setAbleToBorrow(false);
			rentBList.add(book);
		}
		return rentBList;
	}
	
	public boolean isAbleToBorrow(Member mem, int numOfBooks) {
		if (mem.getBorrowedBList().size() + numOfBooks > 5) {
			return false;
		}
		return true;
	}
	
	public boolean borrowBooks(Member mem, int...bookNoes) {
//		if (!isAbleToBorrow(mem, books.size())) {
//			return false;
//		}
		List<Book> books = rentBook(mem, bookNoes);
		if (books == null) {
			return false;
		}
		mem.getBorrowedBList().addAll(books);
		mem.setCouponCount(mem.getCouponCount() + issueCoupon(bookNoes));
		return true;
	}
	public int issueCoupon(int...bookNoes) {
		int couponCount = 0;
		for (int bookNo : bookNoes) {
			Book book = allBList.get(bookNo);
			if (book instanceof CookBook cookBook) {
				couponCount = (cookBook.isCouponForAcademy()) ? ++couponCount : couponCount;
			}
		}
		return couponCount;
	}
	public boolean checkAccessAge(Member mem, int...bookNoes) {
		for (int bookNo : bookNoes) {
			Book book = allBList.get(bookNo);
			if (book instanceof AniBook aniBook) {
				if (mem.getAge() < aniBook.getAccessAge() ) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean returnBooks(Member mem, int...bookNoes) {
		try {
			for (int no : bookNoes) {
				if (mem.getBorrowedBList().stream().noneMatch(b -> b.getBookNo() == no)) {
					return false;
				}
				allBList.get(no).setAbleToBorrow(true);
				Book book = mem.getBorrowedBList().stream().filter(b -> b.getBookNo() == no).findFirst().get();
				mem.getBorrowedBList().remove(book);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
