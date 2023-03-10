package day4;

/*
[ 실습 5 ] 
1. LottoMachine1 이라는 클래스를  생성한다.
2. 1부터 45 사이의 난수 6개를 추출하여 다음 형식으로 출력한다.
    단, 6개 숫자는 중복을 허용하지 않는다.

    [ 출력형식 ]

    오늘의 로또 번호 - x, x, x, x, x, x
 */

public class LottoMachine1 {

	public static void main(String[] args) {
		int num;
		int lotto[] = new int[6];
		int idx = 0;
		String lottoNumber = "";
		
//		for(int i=0; i<lotto.length; i++) {
//			lotto[i] = (int)(Math.random()*45)+1;
//			for(int j=0; j<i; j++) {
//				if(lotto[i] == lotto[j])
//					i--;
//			}
//		}
		
		while(true) {
			if(idx == 6)
				break;
			
			num = (int)(Math.random()*45)+1;
			
			if(lottoNumber.indexOf(String.valueOf(num)) == -1) { // 중복하지 않으면
				lotto[idx] = num;
				idx++;
				lottoNumber += num + " ";
			} else {
				continue;
			}
		}
		
		System.out.print("오늘의 로또 번호 - ");
		
		for(int i=0; i<lotto.length-1; i++) {
			System.out.print(lotto[i] + ",");
		}
		
		System.out.print(lotto[lotto.length-1]);
		
	

	}

}
