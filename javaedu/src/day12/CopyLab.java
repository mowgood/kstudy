package day12;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

/*
[ 문제 3 ]

클래스명 : CopyLab

- 구현 기능
제공된 sample.txt 파일을 읽고 c:/iotest/sample_yyyy_mm_dd.txt  폴더에 저장
하는 프로그램을 구현한다.  파일을 append 모드로 오픈하여
여러번 테스트하면 sample.txt 파일의 내용이 
sample_yyyy_mm_dd.txt 파일에  계속 추가되게 한다.


정상적으로 수행되면 
화면에 “저장 완료되었습니다.”
FileNotFoundException 예외 발생시
"sample.txt 파일을 찾을 수 없습니다." 를 그리고
IOException 예외 발생시
"입출력을 처리하는 동안 오류가 발생했습니다."
를 화면에 출력한다.
오픈된 파일의 close() 처리는 try-catch with resource 구문을 이용한다.
 */

public class CopyLab {
	public static void main(String[] args) {
		char ch;
		LocalDate ld = LocalDate.now();
		int yearNum = ld.getYear();
		int monthNum = ld.getMonthValue();
		int dateNum = ld.getDayOfMonth();
		
		String title = "C:/Temp/sample_" + yearNum + "_" + monthNum + "_" + dateNum + ".txt";
		
		try (FileReader reader = new FileReader("c:/Temp/sample.txt");
			FileWriter writer = new FileWriter(title, true);){
			while (true) {
				int data = reader.read();
				if (data == -1)
					break;
				ch = (char) data;
//				System.out.print(ch);
				writer.write(ch);
			}
			System.out.println("저장이 완료되었습니다.");
		} catch (FileNotFoundException fnfe) {
			System.out.println("sample.txt 파일을 찾을 수 없습니다.");
		} catch (IOException ioe) {
			System.out.println("입출력을 처리하는 동안 오류가 발생했습니다.");
		} 
	}

}
