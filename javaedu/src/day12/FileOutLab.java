package day12;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

/*
[ 문제 2 ]

클래스명 : FileOutLab

- 구현 기능
다음과 같은 내용을 추출하여 c:/Temp/event.txt 라는 파일에 저장한다.
c:/Temp 디렉토리의 존재여부를 채크하고 존재하지 않으면 디렉토리를 
생성한다.  출력내용은 다음과 같다.

2023년 5월 5일은 X요일입니다.
2023년 6월 6일은 X요일입니다.


정상적으로 수행되면 
화면에 “저장이 완료되었습니다.”
예외 발생시
화면에 "파일에 저장하는 동안 오류가 발생했습니다."
를 출력하는 프로그램을 구현한다.

 */

public class FileOutLab {

	public static void main(String[] args) {
		String path = "C:/Temp";
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		try (FileWriter writer = new FileWriter("C:/Temp/event.txt", true);
				PrintWriter out = new PrintWriter(writer);) {
			LocalDate myld = LocalDate.of(2023, 5, 5);
			DayOfWeek myday = myld.getDayOfWeek();
			String mykorday = myday.getDisplayName(TextStyle.FULL, Locale.KOREAN);
			out.printf("2023년 5월 5일은 %s입니다.%n", mykorday);
			
			LocalDate myld2 = LocalDate.of(2023, 6, 6);
			DayOfWeek myday2 = myld2.getDayOfWeek();
			String mykorday2 = myday2.getDisplayName(TextStyle.FULL, Locale.KOREAN);
			out.printf("2023년 6월 6일은 %s입니다.%n", mykorday2);
			
			System.out.println("저장이 완료되었습니다.");

		} catch (Exception e) {
			System.out.println("파일에 저장하는 동안 오류가 발생했습니다.");
		}
	}

}
