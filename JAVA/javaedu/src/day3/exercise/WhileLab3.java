package day3.exercise;

public class WhileLab3 {

	public static void main(String[] args) {
		
		int cnt = 0;
		int num;
		
		while(true) {
			num = (int)(Math.random()*31)+0;
			if(num == 0 || (num>= 27 && num <= 30)) break;
			
//			System.out.printf("%d-%c, %d, 0x%x\n" , num, (char)(num+64), (int)(num+64), (int)(char)num+64);
			System.out.printf("%d-%c, %2$d, 0x%2$x\n" , num, num+64);
			cnt++;
			
		}
		System.out.println("출력횟수는 " + cnt + " 번입니다");
		
		

	}

}
