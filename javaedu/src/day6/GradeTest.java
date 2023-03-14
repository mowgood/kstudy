package day6;

import java.util.Scanner;

class GradeExpr {
	private int[] jumsu;
	
	GradeExpr(int[] jumsu) {
		this.jumsu = jumsu;
	}
	
	double getAverage() {
		return getTotal()/(double)jumsu.length;
	}
	
	int getTotal() {
		int total = 0;
		for(int j : jumsu) {
			total += j;
		}
		return total;
	}
	
	int getGoodScore() {
		int max = jumsu[0];
		for(int i=1; i<jumsu.length; i++) {
			if(max < jumsu[i]) 
				max = jumsu[i];
		}
		return max;		
	}
	
	int getBadScore() {
		int min = jumsu[0];
		for(int i=1; i<jumsu.length; i++) {
			if(min > jumsu[i])
				min = jumsu[i];
		}
		return min;
		
	}
}

public class GradeTest {

	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 int num = sc.nextInt();
		 int[] arr = new int[num];
		 
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
