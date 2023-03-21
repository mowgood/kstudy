package day12.collection;

import java.util.*;

/*
[ 문제 1 ]

1. Product.java 를 Product2.java 로 복사한다.
2. Product2 클래스는 productPrice 를 기준으로 객체의 대소 관계를 비교할 수 있는 객체가 되도록 구현한다.
   즉, Comparable 를 상속하여 구현하라는 것임
3. toString() 메서드를 오버라이딩하여 "제품ID   제품명   가격" 형식으로 리턴하는데
   가격은 첫단위마다 ,와 원을 붙인다.      
4. ProductTest2 클래스에서는 3월21일실습_1.pdf 의 내용대로 4개의 객체를 생성하여 LinkedList 객체에 담아
   Collection.sort() 를 호출한 후 출력했을 때 다음과 같은 결과가 출력되도록 구현한다.

제품 ID       제품명       	가격 
--------------------------------------------------------- 
p100 	     TV  		20,000원 
p200 	     Computer 	10,000원 
p300 	     Audio 	1,000원 
p100           MP3               700원
 */

public class ProductTest2 {
	public static void main(String[] args) {
		LinkedList<Product2> list = new LinkedList<>();
	
		list.add(new Product2("p100", "TV", "20000"));
		list.add(new Product2("p200", "Computer", "10000"));
		list.add(new Product2("p100", "MP3", "700"));
		list.add(new Product2("p300", "Audio", "1000"));
		
		Collections.sort(list, Collections.reverseOrder());
		
		System.out.printf("%s\t %s\t %s\n", "제품ID", "제품명", "가격");
		System.out.println("---------------------------------------");
		
		for(Product2 p2 : list) {
			System.out.println(p2);
		}

	}

}
