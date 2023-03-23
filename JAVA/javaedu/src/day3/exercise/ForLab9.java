package day3.exercise;

public class ForLab9 {

	public static void main(String[] args) {
		int num = (int)(Math.random()*2)+1;
		
	
		for(int i=num; i<=9; i+=2) {
			for(int j=1; j<=9; j++) {
				System.out.print(i + "X" + j + "=" + i*j);
				System.out.print("\t");
			}
			System.out.println();
		}
		
	}

}
