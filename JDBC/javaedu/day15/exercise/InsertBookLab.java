package day15.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
(2) 구현 클래스명 : InsertBookLab

- DB 서버에 접속한다.
- 도서명과 가격을 표준 출력으로 입력받고 도서분류 정보를 선택하게 하여 입력 정보를 book 테이블에 저장한다.
- 한 번 입력을 받은 후에는 계속 입력을 윈하는지 문의하고 원할 때까지 반복 처리되도록 한다.
- 도서분류의 경우에는 입력된 숫자에 따라서 b01, b02, b03, b04, b05 중에 한 개를 입력한다.
- PreparedStatement 를 반드시 사용한다.

	도서명을 입력하세요 :
	가격을 입력하세요 : 
	도서분류에 대한 넘버를 입력하세요.
	    1. 프로그래밍 언어
	    2. 웹 프로그래밍
	    3. 빅데이터
	    4. 데이터베이스
	    5. 인프라
            선택(1~5) :
		 
	정보가 입력되었습니다.

	계속 입력하겠습니까?(y/n)

		:

- 종료할 때는 "X개의 데이터 입력 완료!" 메시지를 출력한다.
 */

public class InsertBookLab {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		int updateNum = 0;

		mainLoop: while (true) {
			try (Connection conn = DriverManager.getConnection(url, user, passwd);
					PreparedStatement pstmt = conn
							.prepareStatement("INSERT INTO book (title, price, kind) VALUES (?, ?, ?)");
					Scanner scan = new Scanner(System.in);) {
				while (true) {
					System.out.print("도서명을 입력하세요 : ");
					String title = scan.nextLine();

					System.out.print("가격을 입력하세요 : ");
					int price = Integer.parseInt(scan.nextLine());

					System.out.println("도서분류에 대한 넘버를 입력하세요.");
					System.out.println("1. 프로그래밍 언어");
					System.out.println("2. 웹 프로그래밍");
					System.out.println("3. 빅데이터");
					System.out.println("4. 데이터베이스");
					System.out.println("5. 인프라");
					System.out.print("선택(1~5) : ");
					int number = Integer.parseInt(scan.nextLine());
					// int number = scan.nextInt();
					// scan.nextLine(); // 입력 버퍼를 청소
					
					pstmt.setString(1, title);
					pstmt.setInt(2, price);
					pstmt.setString(3, "b0" + number);
					updateNum += pstmt.executeUpdate();

					System.out.println("정보가 입력되었습니다.");
					System.out.print("계속 입력하겠습니까?(y/n)");
					String aws = scan.nextLine();
					if (aws.equalsIgnoreCase("y")) {
						continue;
					}
					System.out.println(updateNum + "개의 데이터 입력 완료!");
					break mainLoop;
				}
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}

	}

}
