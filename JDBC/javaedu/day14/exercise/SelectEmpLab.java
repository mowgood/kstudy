package day14.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class SelectEmpLab {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		
		boolean random = new Random().nextBoolean();
		
		System.out.println("random = " + random);
		
		if(random) { //true
			String sql = "SELECT ename, format(sal,0) sal from emp";
			try (Connection conn = DriverManager.getConnection(url, user, passwd);
					Statement stmt = conn.createStatement();			
					ResultSet rs = stmt.executeQuery(sql);) {			
				if(rs.next() ) {				
					do {
						System.out.print(rs.getString("ename")+" 직원의 월급은 ");
						System.out.print(rs.getString("sal")+"달러입니다.\n");
					} while (rs.next());
				} else {
					System.out.println("추출된 행이 없습니다.");
				}			
			} catch (Exception e) {
				System.err.println("오류 발생 : " + e);
			} 
		}
		else { //false
			String sql = "SELECT ename, DATE_FORMAT(hiredate, '%Y년 %m월 %d일') hiredate from emp order by hiredate";
			try (Connection conn = DriverManager.getConnection(url, user, passwd);
					Statement stmt = conn.createStatement();			
					ResultSet rs = stmt.executeQuery(sql);) {			
				if(rs.next() ) {				
					do {
						System.out.print(rs.getString("ename")+" 직원은 ");
						System.out.print(rs.getString("hiredate")+"에 입사하였습니다.\n");
					} while (rs.next());
				} else {
					System.out.println("추출된 행이 없습니다.");
				}			
			} catch (Exception e) {
				System.err.println("오류 발생 : " + e);
			} 
		}
	}
}
