package day4;

/*
[ 실습5 ]

1. TwoArrayLab5 라는 클래스를 만든다.

2. 5개의 행과 3개의 열을 갖는 int 타입의 2차원 배열을 생성하여 다음과 같이 출력한다.

	0	0	0	
	0	0	0	
	0	0	0	
	0	0	0	
	0	0	0	
 
3. 2번에서 만든 2차원 배열에 다음과 같이 구성되도록 데이터를 대입한다.
	
	1	0	0		
	2	6	0		
	3	7	9		
	4	8	0		
	5	0	0	

(*) 2번에서의 출력과 3번에서의 출력 사이에는 빈행을 출력한다.
     또한 규칙을 잘 찾아서 로직을 잘 구현해 본다.

- 수행 결과

0	0	0	
0	0	0	
0	0	0	
0	0	0	
0	0	0	

1	0	0	
2	6	0	
3	7	9	
4	8	0	
5	0	0
 */

public class TwoArrayLab5 {

	public static void main(String[] args) {
		 int[][] arr = new int[5][3];
		 
		 for(int i=0; i<arr.length; i++) {
			 for(int j=0; j<arr[i].length; j++) {
				 System.out.print(arr[i][j] + " ");
			 }
			 System.out.println();
		 }
		 
		 System.out.println();
		 
//		 0,0/1,0/2,0/3,0/4,0   1,1/2,1/3,1   2,2
		 
		 int num = 1;
		 for(int i=0; i<arr.length; i++) {
			 for(int j=0; j<arr[i].length; j++) {
				 arr[i][j] = num;
				 num++;
			 }			 
		 }
		 
//		 int y = 0;
//		 int num = 1;
//		 int next = 0;
//		 for(int i=5; i>=1; i-=2) {
//			 for(int j=0; j<i; j++) {
//				 arr[j+next][y] = num;
//				 num++;
//			 }
//			 next++;
//			 y++;
//		 }
		 
/*
		 int idx;
		 int num = 1;
		 
		 while(true) {
			 idx = 0;
			 
			 if(idx == arr.length)
				 break;

			 for(int j=idx; j<arr.length; j++) {
				 arr[j][idx] = num++;
			 }
			 
			 idx++;
			 
		 }
*/		 
		 for(int i=0; i<arr.length; i++) {
			 for(int j=0; j<arr[i].length; j++) {
				 System.out.print(arr[i][j] + " ");
			 }
			 System.out.println();
		 }

		 
		 
		 
		 
//		 for(int i=0; i<arr.length; i++) {
//			 for(int j=0; j<arr[i].length; j++) {
//				 System.out.print(arr[i][j] + " ");
//			 }
//			 System.out.println();
//		 }
		 
	}

}
