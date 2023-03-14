package day6;

class Book {
	String title;
	String author;
	int price;
	
	Book() {
		this("이것이 자바다", "신용권", 36000);
	}
	
	Book(String title, String author, int price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}
	
	String getBookInfo() {
		return title + " " + author + " " + price;
	}
}

public class BookTest {

	public static void main(String[] args) {
		Book bookJava = new Book();
		Book book1 = new Book("책1", "저자1", 1000);
		Book book2 = new Book("책2", "저자2", 2000);
		Book book3 = new Book("책3", "저자3", 3000);
		Book book4 = new Book("책4", "저자4", 4000);

		System.out.println(bookJava.getBookInfo());		
		System.out.println(book1.getBookInfo());
		System.out.println(book2.getBookInfo());
		System.out.println(book3.getBookInfo());
		System.out.println(book4.getBookInfo());
	}

}
