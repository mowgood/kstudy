package day15.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/*
(3) 구현 클래스명 : SelectBookLab

- 수행을 시작하면 다음과 같은 메뉴를 출력한다.

	1. 모두 출력하기
	2. 가격이 높은 순으로 출력하기
	3. 20000 이상의 도서들만 출력하기 
	4. 웹 프로그래밍 도서들만 출력하기
	5. 데이터베이스와 인프라 도서들만 출력하기
         	6. 도서명에 '자바'를 포함하는 도서들만 출력하기
	7. 분류별 도서 가격의 평균을 출력하기 
             

	원하는 메뉴의 번호를 선택 : 

- 반복 처리하게 구현한다.(계속 처리하겠는지 프롬프트하고 계속하겠다고 하면 메뉴 출력부터 반복)
- 가격은 천단위 , 를 추가하고 '원'을 붙여서 출력한다.
- 1번과 2번은 4개의 컬럼을 모두 출력한다. (모든 행, 모든 열)
- 3번, 4번, 5번, 6번은 도서제목과 가격만 출력한다.
- 7번은 다음과 같은 형식으로 출력한다.

	프로그래밍 언어 도서들의 가격 평균은 x,xxx원입니다.
	웹 프로그래밍 도서들의 가격 평균은 x,xxx원입니다.
	빅데이터 도서들의 가격 평균은 x,xxx원입니다.
	데이터베이스 도서들의 가격 평균은 x,xxx원입니다.
	인프라 도서들의 가격 평균은 x,xxx원입니다.

  도서가 없는 분류의 정보는 제외한다.
 */

public class SelectBookLab {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		Scanner scan = new Scanner(System.in);
		String sql = "";
		String bookKind = "";

		mainLoop: while (true) {
			System.out.println("1. 모두 출력하기");
			System.out.println("2. 가격이 높은 순으로 출력하기");
			System.out.println("3. 20000 이상의 도서들만 출력하기");
			System.out.println("4. 웹 프로그래밍 도서들만 출력하기");
			System.out.println("5. 데이터베이스와 인프라 도서들만 출력하기");
			System.out.println("6. 도서명에 '자바'를 포함하는 도서들만 출력하기");
			System.out.println("7. 분류별 도서 가격의 평균을 출력하기");
			System.out.print("\n\n원하는 메뉴의 번호를 선택 : ");
			int num = Integer.parseInt(scan.nextLine());

			switch (num) {
			case 1: // 모든 행,열 출력
				sql = "SELECT id, title,  CONCAT(FORMAT(price, 0), '원') bookPrice, kind FROM book";
				break;
			case 2:
				sql = "SELECT id, title,  CONCAT(FORMAT(price, 0), '원') bookPrice, kind FROM book ORDER BY price DESC";
				break;
			case 3: // 도서제목과 가격만 출력
				sql = "SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where price >= 20000";
				break;
			case 4:
				sql = "SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where kind='b02'";
				break;
			case 5:
				sql = "SELECT title,  CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where kind='b04' OR kind ='b05'";
				break;
			case 6:
				sql = "SELECT title, CONCAT(FORMAT(price, 0), '원') bookPrice FROM book where title LIKE '%자바%'";
				break;
			case 7: // 분류별 도서 가격 평균 출력
				sql = "SELECT kind, CONCAT(FORMAT(AVG(price), 0), '원') priceAvg FROM book WHERE kind is not null GROUP BY kind";
			}

			while (true) {
				try (Connection conn = DriverManager.getConnection(url, user, passwd);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);) {
					if (rs.next()) {
						do {
							if (num == 1 || num == 2) {
								System.out.println("id : " + rs.getString("id"));
								System.out.println("title : " + rs.getString("title"));
								System.out.println("price : " + rs.getString("bookPrice"));
								System.out.println("kind : " + rs.getString("kind") + "\n");
							} else if (num == 3 || num == 4 || num == 5 || num == 6) {
								System.out.println("title : " + rs.getString("title"));
								System.out.println("price : " + rs.getString("bookPrice") + "\n");
							} else if (num == 7) {
								switch (rs.getString("kind")) {
								case "b01":
									bookKind = "프로그래밍 언어";
									break;
								case "b02":
									bookKind = "웹 프로그래밍";
									break;
								case "b03":
									bookKind = "빅데이터";
									break;
								case "b04":
									bookKind = "데이터베이스";
									break;
								case "b05":
									bookKind = "인프라";
								}
								System.out.println(bookKind + " 도서들의 가격 평균은 " + rs.getString("priceAvg") + "입니다.");
							}
						} while (rs.next());
					} else
						System.out.println("해당 도서는 존재하지 않습니다.");
					System.out.print("계속 입력하겠습니까?(y/n)");
					String aws = scan.nextLine();
					if (aws.equalsIgnoreCase("y")) {
						System.out.println();
						continue mainLoop;
					}
					break mainLoop;
				} catch (Exception e) {
					System.out.println("오류 발생 : " + e);
				}
			}

		}
		scan.close();
	}

}
