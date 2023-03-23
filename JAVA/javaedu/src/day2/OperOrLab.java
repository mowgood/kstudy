package day2;

public class OperOrLab {

	public static void main(String[] args) {
		int grade = (int)(Math.random() * 7);
		if(grade == 1 || grade == 2 || grade == 3)
			System.out.print(grade + "학년은 저학년입니다.");
		else
			System.out.print(grade + "학년은 고학년입니다.");

	}

}
