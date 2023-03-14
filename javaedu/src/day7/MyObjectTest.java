package day7;

class MyObject { // extends Object
	void printName() {
		System.out.println("자바듀크");
	}
	public String toString() { // 조상이 가지고 있는 메서드를 자식에서 알맞게 재정의 (메서드 오버라이딩)
		/*
		 * 접근 제어자는 조상의 메서드와 동일하거나 넓게 줘야함 (적게 줄수 없음)
		 */
		return "Object의 자식 클래스인 MyObject 객체임";
	}
}

public class MyObjectTest {
	public static void main(String[] args) {
		MyObject my = new MyObject();
		System.out.println(my.toString());
		System.out.println(my);
		System.out.println("출력" + my);
		my.printName();

	}

}
