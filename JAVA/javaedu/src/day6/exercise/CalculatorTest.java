package day6.exercise;

import java.util.Scanner;

public class CalculatorTest {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 CalculatorExpr calEx;
		 String ask;
		 
		 System.out.println("숫자 2개를 입력하세요.");
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
		 		System.out.println("숫자 2개를 입력하세요.");
		 	 	calEx.setNum1(sc.nextInt());
		 	 	calEx.setNum2(sc.nextInt());
		 	 	System.out.printf("입력된 숫자 : %d, %d\n", calEx.getNum1(), calEx.getNum2());
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
