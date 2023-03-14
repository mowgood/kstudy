package day6;

import java.util.Scanner;

class CalculatorExpr {
	private int num1;
	private int num2;

	CalculatorExpr(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	int getAddition() {
		return num1 + num2;
	}

	int getSubtraction() {
		return num1 - num2;
	}

	int getMultiplication() {
		return num1 * num2;
	}

	double getDivision() {
		return (double)num1 / (double)num2;
	}

	void setNum1(int num1) {
		this.num1 = num1;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}
}

public class CalculatorTest {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 CalculatorExpr calEx;
		 String ask;
		 int num1 = sc.nextInt();
		 int num2 = sc.nextInt();
		 calEx = new CalculatorExpr(num1, num2);
	 	 System.out.printf("입력된 숫자 : %d, %d\n", num1, num2);
	 	 System.out.printf("덧셈 : %d\n", calEx.getAddition());
	 	 System.out.printf("뺄셈 : %d\n", calEx.getSubtraction());
	 	 System.out.printf("곱셈 : %d\n", calEx.getMultiplication());
	 	 System.out.printf("나눗셈 : %f\n", calEx.getDivision());
	 	
		 while(true) {
			 System.out.println("계속 진행하시겠습니까? Y/N");
		 	 ask = sc.next();
		 	 if(ask.equals("Y")) {
		 	 	calEx.setNum1(sc.nextInt());
		 	 	calEx.setNum2(sc.nextInt());
		 	 	System.out.printf("입력된 숫자 : %d, %d\n", num1, num2);
		 	 	System.out.printf("덧셈 : %d\n", calEx.getAddition());
		 	 	System.out.printf("뺄셈 : %d\n", calEx.getSubtraction());
		 	 	System.out.printf("곱셈 : %d\n", calEx.getMultiplication());
		 	 	System.out.printf("나눗셈 : %f\n", calEx.getDivision());
		 	 } else {
		 		sc.close();
		 		break;
		 	 }
		 }		 		 
	}

}
