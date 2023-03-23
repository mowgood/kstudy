package day12.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
[ 문제 4 ]

클래스명 : CountLab

- 구현 기능
제공된 yesterday.txt 파일을 읽고 이 파일 안에 yesterday 단어가 몇개 있는지 갯수를 
카운팅하여 다음과 같이 출력한다.

"yesterday 라는 단어는 X개 있습니다."

예외처리는 임의로 정하여 구현한다.
 */

public class CountLab {
	public static void main(String[] args) {
		File f = new File("c:/Temp/yesterday.txt");
		String word = "";
		int cnt = 0;
		try (Scanner scan = new Scanner(f);) {
			while(scan.hasNext()) {
				word = scan.next();
//				System.out.println(word);
				if(word.equals("yesterday") || word.equals("yesterday.")) 
					cnt++;
			}				
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다");
		}

		System.out.printf("yesterday 라는 단어는 %d개 있습니다.", cnt);

	}

}
