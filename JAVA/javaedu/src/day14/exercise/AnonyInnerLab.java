package day14.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import day6.exercise.Book;

/*
소스명 : AnonyInnerLab.java, Book.java

(1) day5 패키지 폴더나 day6 패키지 폴더에 가면 BookTest.java 가 있으며
     이중에서 Book 클래스를 public 클래스로 변경하고
     생성자와 getBookInfo() 도 public 으로 변경한다.
     또한 price 정보를 리턴하는 getter 메서드를 추가한다.

(2) AnonyInnerLab 클래스의 main() 메서드에 구현할 내용

    ArrayList 객체에 Book 객체를 5개 저장한다.(BookTest.java 를 활용해도 됨)
    Book 객체들의 정보를 행단위로 출력한다.
    Collections.sort(list, Comparator<Book>)
    을 호출하여 ArrayList 에 저장된 Book 객체들을 소팅한다.
    이 때 
    Comparator<Book> 객체를 구현하고 생성하여 전달해야 하는데 
    Comparator<Book> 객체 생성 부분에서 Anonymous Inner 클래스로 해결한다.
    compare() 메서드 오버라이딩시 책 가격을 가지고 오름차순이 되도록 구현한다.

(3) 실행 결과

제목 : 자바의 정석	 저자 : 남궁성	 가격 : 27000
제목 : 챗GPT	 저자 : 반병현	 가격 : 11700
제목 : 스타트 스프링 부트	 저자 : 남가람	 가격 : 27000
제목 : Doit! 자바프로그래밍	 저자 : 박은중	 가격 : 22500
제목 : 이것이 자바다	 저자 : 신용권,임경균	 가격 : 36000
[소팅 후 ]
제목 : 챗GPT	 저자 : 반병현	 가격 : 11700
제목 : Doit! 자바프로그래밍	 저자 : 박은중	 가격 : 22500
제목 : 자바의 정석	 저자 : 남궁성	 가격 : 27000
제목 : 스타트 스프링 부트	 저자 : 남가람	 가격 : 27000
제목 : 이것이 자바다	 저자 : 신용권,임경균	 가격 : 36000
 */

public class AnonyInnerLab {
	public static void main(String[] args) {		
		List<Book> list = new ArrayList<>();
		list.add(new Book("자바의 정석", "남궁성", 27000));
		list.add(new Book("챗GPT", "반병현", 11700));
		list.add(new Book("스타트 스프링 부트", "남가람", 27000));
		list.add(new Book("Doit! 자바프로그래밍", "박은중", 22500));
		list.add(new Book("이것이 자바다", "신용권,임경균", 36000));
		
		for(Book b : list) {
			System.out.println(b.getBookInfo());
		}
		
//		Comparator<Book> bookComp = new Comparator<>() {
//			@Override
//			public int compare(Book b1, Book b2) {
//				if(b1.getPrice() > b2.getPrice())
//					return 1;
//				else if (b1.getPrice() < b2.getPrice())
//					 return -1;
//				else 
//					return 0;
//			}
//		};
//		
//		Collections.sort(list, bookComp);
		
		Collections.sort(list, new Comparator<Book>() {
			@Override
			public int compare(Book b1, Book b2) {
				if(b1.getPrice() > b2.getPrice())
					return 1;
				else if (b1.getPrice() < b2.getPrice())
					 return -1;
				else 
					return 0;
			}
		});
		
		System.out.println("\n[소팅 후]");
		for(Book b : list) {
			System.out.println(b.getBookInfo());
		}	
	}

}
