package day3.exercise;

public class ForLab5 {

	public static void main(String[] args) {
		int num1 = (int)(Math.random()*8)+3;
		int num2 = (int)(Math.random()*3)+1;
		
		for(int i=0; i<num1; i++) {
			switch(num2) {
				case 1 : 
					System.out.print("*");
					break;
				case 2 :
					System.out.print("$");
					break;
				default : 
					System.out.print("#");
			}
		}
		
	}

}
