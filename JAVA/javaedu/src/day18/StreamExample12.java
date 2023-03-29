package day18;

import java.util.Arrays;

public class StreamExample12 {
	public static void main(String[] args) {
		// 정수 배열
		int[] arr = { 1, 2, 3, 4, 5 };

		// 카운팅
		long count = Arrays.stream(arr).filter(n -> n % 2 == 0).count();
		System.out.println("2의 배수 개수: " + count);

		// 총합
		long sum = Arrays.stream(arr).filter(n -> n % 2 == 0).sum();
		System.out.println("2의 배수의 합: " + sum);

		// 평균
		double avg = Arrays.stream(arr).filter(n -> n % 2 == 0).average().getAsDouble(); // 리턴값이 Optional이기 때문에 get메서드를 사용해야 한다
		System.out.println("2의 배수의 평균: " + avg);

		// 최대값
		int max = Arrays.stream(arr).filter(n -> n % 2 == 0).max().getAsInt(); // 리턴값이 Optional이기 때문에 get메서드를 사용해야 한다
		System.out.println("최대값: " + max);

		// 최소값
		int min = Arrays.stream(arr).filter(n -> n % 2 == 0).min().getAsInt(); // 리턴값이 Optional이기 때문에 get메서드를 사용해야 한다
		System.out.println("최소값: " + min);

		// 첫 번째 요소
		int first = Arrays.stream(arr).filter(n -> n % 3 == 0).findFirst().getAsInt(); // 리턴값이 Optional이기 때문에 get메서드를 사용해야 한다
		System.out.println("첫 번째 3의 배수: " + first);
	}
}