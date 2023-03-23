package day3;

public class ForTest6 {      

	public static void main(String[] args) {
		final char DECO = '*';
//		final double PI = 3.14;
		int count = (int)(Math.random()*20);
		for(int n=0; n < count; n++)
			System.out.print(DECO);		
	}
}
