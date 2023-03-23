package day6.exercise;

import java.util.Scanner;

public class GradeTest {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 System.out.println("데이터 개수를 입력하세요.");
		 int num = sc.nextInt();
		 int[] arr = new int[num];
		 
		 System.out.println("점수를 입력하세요.");
		 for(int i=0; i<arr.length; i++) {
			 arr[i] = sc.nextInt();
		 }
		 
		 System.out.print("점수들 : ");
		 for(int i=0; i<arr.length-1; i++) {
			 System.out.print(arr[i] + ", ");
		 }
		 System.out.println(arr[arr.length-1]);
		 
		 GradeExpr gx = new GradeExpr(arr);
		 System.out.printf("총점 : %d\n", gx.getTotal());
		 System.out.printf("평균 : %f\n", gx.getAverage());
		 System.out.printf("최고 점수 : %d\n", gx.getGoodScore());
		 System.out.printf("최저 점수 : %d\n", gx.getBadScore());
		 sc.close();
	}

}
