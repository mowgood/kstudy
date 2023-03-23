package day2;

public class ConditionOperLab {

	public static void main(String[] args) {
		int num = (int)(Math.random() * 6);
		if(num == 1) 
			System.out.println("결과값 : " + (300+50));
		else if(num == 2)
			System.out.println("결과값 : " + (300-50));
		else if(num == 3)
			System.out.println("결과값 : " + (300*50));
		else if(num == 4)
			System.out.println("결과값 : " + (300/50));
		else 
			System.out.println("결과값 : " + (300%50));
		

	}

}
