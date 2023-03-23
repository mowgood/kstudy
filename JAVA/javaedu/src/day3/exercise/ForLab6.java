package day3.exercise;

public class ForLab6 {

	public static void main(String[] args) {
		 final char NUM = '&';
		 int num2 = (int)(Math.random()*6)+5;
		 
		 for(int i=1; i<=num2; i++) {
			 for(int j=0; j<i; j++) {
				 System.out.print(NUM);
			 }
			 System.out.println();
		 }
	}

}
