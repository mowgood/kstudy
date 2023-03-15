package day8;

public class PolyTest {

	public static void main(String[] args) {
		 printObjectInfo(new Object());
		 printObjectInfo("가나다");
		 printObjectInfo("ABC");
		 printObjectInfo(new java.util.Date());
		 printObjectInfo(new java.io.File("c:/Temp"));
		 printObjectInfo(new int[10]);
		 printObjectInfo(new double[5]);
		 printObjectInfo(100); // java5 - autoboxing java.lang.integer
		 printObjectInfo(3.14);

	}
	static void printObjectInfo(Object o) {
		if (o instanceof String) {
			System.out.println("문자열 객체가 전달됨 : " + o.toString() + "-" + ((String)o).length());
		} else {
			System.out.println("전달된 객체의 클래스명 : " + o.getClass().getName());
		}
		
	}

}
