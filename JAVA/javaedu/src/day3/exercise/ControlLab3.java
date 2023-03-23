package day3.exercise;

public class ControlLab3 {

	public static void main(String[] args) {
		
		StringBuilder sb = new StringBuilder();
//		String str = "";
		int num;
		
		while(true) {
			num = (int)(Math.random()*120)+1;
			
			if(num < 50) {
				sb.append(num + " : 50 미만\n");
				if(num%10==3) {
					sb.append(num + " : *듀크팀*\n");
				}
			} else if(num >= 50 && num <= 80) {
				sb.append(num + " : 50 이상 80 이하\n");
				if(num >= 70 && num <= 79) {
					sb.append(num + " : *턱시팀*\n");
				}
			} else if(num >= 81 && num <= 100) {
				continue;
			} else {
				break;
			}
		}
		
		System.out.printf("%s", sb);

	}

}
