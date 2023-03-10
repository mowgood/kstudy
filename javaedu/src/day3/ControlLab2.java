package day3;

public class ControlLab2 {

	public static void main(String[] args) {
		int num = 0;
		while(true) {
			num++;
			if(num == 11) 
				break;
			
			if(num % 3 == 0 || num % 4 == 0) {
				continue;			
			} else {
				System.out.println(num);
			}
		}

	}

}
