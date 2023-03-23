package day6.exercise;

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
