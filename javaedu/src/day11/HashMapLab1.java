package day11;

import java.util.*;

/*
[ 실습 3 ] HashMap 실습
표준입력으로 나라이름과 인구수를 입력받아
HashMap 객체에 5개까지 저장한 후에 출력하고 종료한다.
단, 입력되는 나라명은 모두 달라야 한다.
     
클래스명 : HashMapLab1

- 실행 결과

   나라이름을 입력하세요 : 
   인구 수를 입력하세요 : 
  *저장되었습니다.*
   나라이름을 입력하세요 : 
   인구 수를 입력하세요 : 
   *나라명 xxx는 이미 저장되었습니다.*
	:
   
  5개가 모두 입력되었습니다.
  입력된 데이터 : XX(nn), XX(nn)....
  */

public class HashMapLab1 {
	public static void main(String[] args) {
		 HashMap<String, Integer> map = new HashMap<>();
		 Scanner sc = new Scanner(System.in);
		 String country;
		 int people;
		 int cnt = 0;
		 
		 while(true) {
			 if(map.size() == 5) {
				 System.out.println("5개가 모두 입력되었습니다.");
				 break;
			 }
			 System.out.println("나라이름을 입력하세요 : ");
			 country = sc.next();
			 System.out.println("인구 수를 입력하세요 : ");
			 people = sc.nextInt();
			 if(map.containsKey(country)) {
				 System.out.printf("*나라명 %s는 이미 저장되었습니다.*\n", country); 
				 continue;
			 } else {
				 map.put(country, people);
				 System.out.println("*저장되었습니다.*");
			 }	 
		 }
		 
		 System.out.print("입력된 데이터 : ");
		 
		 for(String key : map.keySet()) {
			 System.out.printf("%s(%d)", key, map.get(key));
			 if(cnt < 4) 
				 System.out.print(", ");
			 cnt++;
		 }
		 
		 sc.close();

	}

}
