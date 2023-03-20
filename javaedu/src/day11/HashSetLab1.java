package day11;

import java.util.*;

/*
[ 실습 2 ] HashSet 실습

10 부터 30 까지의 숫자를 10개 추출하여 화면에 다음과 같이 출력하는 
HashSetLab1 이라는 클래스를 구현한다.
중복 숫자는 허용하지 않으며 추출되는 숫자를 HashSet 에 저장하여 처리한다.

오늘의 로또 번호 : 10, 11, 25, 22, 20, 21, 15, 27, 28, 13
 */

public class HashSetLab1 {

	public static void main(String[] args) {
		int cnt = 0;
		HashSet<Integer> lotto = new HashSet<>();
		
		while(true) {
			if(lotto.size() == 10) 
				break;
			lotto.add(day5.MethodLab5.getRandom(10, 30));
		}
		
		System.out.print("오늘의 로또 번호 : ");
		
		for(int i : lotto) {
			System.out.print(i);
			if(cnt < 9) 
				System.out.print(", ");
			cnt++;
		}
		 

	}

}
