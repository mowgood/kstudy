package day15.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
[ JDBC 실습 3 ]

(1) 구현 클래스명 : CreateBookLab

- DB 서버에 접속한다.
- 다음 사양의 테이블을 생성하는 JDBC 프로그램을 구현한다.
   
        테이블명 : book
        컬럼 :  도서명, 가격, 도서분류
	     id - 식별값, Auto Increment 적용 - int
                 title - 한글기준 30자까지 저장할 수 있게 varchar 타입 사용 - varchar(90)
                 price - 숫자를 저장할 수 있는 숫자타입 - int
                 kind - 영문기준 3자까지 저장할 수 있게 char 타입 사용 - char(3)
                    	b01 - 프로그래밍 언어
		b02 - 웹 프로그래밍
		b03 - 빅데이터
		b04 - 데이터베이스
		b05 - 인프라

- 성공적으로 수행되면 "book 테이블 생성 완료!"메시지를 출력하고 종료한다.
- 오류 발생시에는 "오류발생"이라는 메시지를 출력하고 콜스택정보도 출력한다.
 */

public class CreateBookLab {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		try {
			String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "jdbctest";
			String passwd = "jdbctest";
			conn = DriverManager.getConnection(url, user, passwd);
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE book (id int primary key auto_increment, title varchar(90), price int, kind char(3))");
			System.out.println("book 테이블 생성 완료!");
		} catch (SQLException se1) {
			System.out.println("오류발생 " + se1);
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException se2) {
				System.out.println(se2.getMessage());
			}
		}
	}
}
