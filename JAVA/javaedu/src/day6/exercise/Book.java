package day6.exercise;

public class Book {
	String title;
	String author;
	int price;
	
	public Book() {
		this("이것이 자바다", "신용권", 36000);
	}
	
	public Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	public String getBookInfo() {
		return String.format("제목 : %10s\t 저자 : %10s\t 가격 : %d", title, author, price);
	}

	public int getPrice() {
		return price;
	}
}
