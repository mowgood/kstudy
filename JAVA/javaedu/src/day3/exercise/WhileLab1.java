package day3.exercise;

public class WhileLab1 {

	public static void main(String[] args) {
		int num = (int)(Math.random()*6)+5;
		int idx = 1;
		
		System.out.println("[for 결과]");
		for(int i=1; i<=num; i++) {
			System.out.println(i + " -> " + i*i);
		}
		
		System.out.println("[while 결과]");
		while(true) {
			if(idx > num) break;
			System.out.println(idx + " -> " + idx*idx);
			idx++;
		}

	}

}
