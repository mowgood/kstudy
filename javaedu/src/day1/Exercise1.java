package day1;

/*
[ 실습 1 ]

작성 클래스명 : Exercise1
(1) int 타입의 변수 3개를 선언하고 각각 10, 25, 33 으로 초기화 한다. 
(2) 세 개 변수를 사용하여 합계와 평균을 구하고 다음에 제시된 출력 형식과 같이 
출력한다. (평균의 소수점 이하는 고려하지 않는다.)

- 출력 형식 

합계 : 68
평균 : 22
 */

public class Exercise1 {

	public static void main(String[] args) {
		int a, b, c;
		a = 10;
		b = 25;
		c = 33;
		
		int sum = a + b + c;
		int avg = sum / 3;
		
		System.out.println("합계 : " + sum);
		System.out.println("평균 : " + avg);

	}

}
