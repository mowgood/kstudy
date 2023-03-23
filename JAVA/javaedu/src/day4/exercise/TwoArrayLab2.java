package day4.exercise;

/*
[ 실습 2 ]
1. TwoArrayLab2 라는 클래스를 만든다.
2. 다음의 내용대로 char 타입의 2차원 배열을 생성한다.
 
         'B', 'C', 'A', 'A'
         'C', 'C', 'B', 'B'
         'D', 'A', 'A', 'D'

3. 4개의 원소를 갖는 int 타입의 배열을 생성한다.
    첫번째 원소에는 'A' 문자의 개수    
    두번째 원소에는 'B' 문자의 개수    
    세번째 원소에는 'C' 문자의 개수    
    네번째 원소에는 'D' 문자의 개수    
    를 저장한다.

4. 출력형식
    A 는 x개 입니다.
    B 는 x개 입니다.
    C 는 x개 입니다.
    D 는 x개 입니다.	
 */
public class TwoArrayLab2 {

	public static void main(String[] args) {
		char[][] arr = { {'B','C','A','A'}, 
		               {'C','C','B','B'}, 
		               {'D','A','A','D'}, };
		
		int[] arr2 = new int[4];
		char[] elem = {'A','B','C','D'};
				
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(arr[i][j] == 'A') 
					arr2[0]++;
				else if(arr[i][j] == 'B')
					arr2[1]++;
				else if(arr[i][j] == 'C')
					arr2[2]++;
				else
					arr2[3]++;
			}			
		}
		
		for(int i=0; i<arr2.length; i++) {
			System.out.println(elem[i] + " 는 " + arr2[i] + "개 입니다.");
		}

	}

}
