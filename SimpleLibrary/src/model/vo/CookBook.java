package model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CookBook extends Book {
	private boolean couponForAcademy;
	
	public CookBook(String title, String author, String publisher, boolean coupon) {
		super(title, author, publisher);
		this.setCouponForAcademy(coupon);
	}
	
	@Override
	public String toString() {
		return super.toString() + ", type=CookBook, couponForAcademy=" + this.isAbleToBorrowed();
	}
}
