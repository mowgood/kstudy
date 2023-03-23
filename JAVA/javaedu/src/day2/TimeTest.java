package day2;

public class TimeTest {

	public static void main(String[] args) {
		int time = 32150;
		int h = time / 60;
		int m = time % 60;
		int s = m % 60;
		
		System.out.print(h + "시간 " + m + "분 " + s + "초");

	}

}
