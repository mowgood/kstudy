package day8;

public class RuntimeTest {
	public static void main(String[] args) throws Exception {
		//싱글톤 패턴이 적용된 Runtime클래스
		//싱글톤 패턴, 객체 하나를 공유 -> 생성자를 private로 감춰 놓음
		Runtime r = Runtime.getRuntime(); //팩토리 메서드, 생성자가 아닌 다른 방법으로 객체 생성 //Runtime : JVM에 대한 객체
		Runtime r1 = Runtime.getRuntime();
		Runtime r2 = Runtime.getRuntime();
		
		System.out.println(r);
		System.out.println(r1);
		System.out.println(r2);		
//		r.exec("c:/windows/notepad.exe");
	}

}
