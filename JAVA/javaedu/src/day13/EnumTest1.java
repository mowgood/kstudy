package day13;

enum Season {
	SPRING, SUMMER, FALL, WINTER // SPRING, SUMMER, ...은 상수명이면서 값이다 
}

public class EnumTest1 {
	public static void main(String args[]) {
		System.out.println(Season.SPRING);
		System.out.println(Season.SUMMER);
		System.out.println(Season.FALL);
		System.out.println(Season.WINTER);
		Season data1 = Season.FALL;
		if (data1 == Season.FALL)
			System.out.println("당신이 좋아하는 계절은 가을!!");

		switch (data1) {
		case SPRING:
			System.out.println("대저토마토");
			break;
		case SUMMER:
			System.out.println("복숭아");
			break;
		case FALL:
			System.out.println("홍로");
			break;
		case WINTER:
			System.out.println("레드향");
			break;
		}

		for (Season v : Season.values()) // enum은 value() 메소드가 자동으로 만들어진다
			System.out.print(v + " ");
		System.out.println();
		
		Season data2 = Season.valueOf("SUMMER"); // SUMMER라는 값에 알맞는 enum상수 객체를 리턴, valueOf()는 자동으로 만들어지는 메서드
		System.out.println(data2);
	}
}
