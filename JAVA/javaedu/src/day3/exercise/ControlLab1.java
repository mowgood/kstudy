package day3.exercise;

public class ControlLab1 {

	public static void main(String[] args) {
		
		int cnt = 0;
		int num, sum;
		
		while(true) {
			num = (int)(Math.random()*11)+10;
			sum = 0;
			
			
			if(num % 3 ==0 || num % 5 == 0) {
				for(int i=1; i<=num; i++) {
					sum += i;
				}
				System.out.println("합 : " + sum);
				cnt++;
			} else if(num == 11 || num == 17 || num == 19) {
				break;
			} else {
				System.out.println("재수행");
			}
		}
		
		System.out.println(cnt + "회 반복함");
		

	}

}
