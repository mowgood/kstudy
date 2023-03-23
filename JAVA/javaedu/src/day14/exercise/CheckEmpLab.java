package day14.exercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CheckEmpLab {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		String findName = "";
		String ctn = "";
		Scanner sc = new Scanner(System.in);
	
		while(true) {
			if(findName != "") {
				System.out.println("계속 하시겠습니까? y/n");
				ctn = sc.next();
				if(ctn.equals("n") || ctn.equals("N")) {
					break;
				}
			}
			System.out.println("조회할 직원의 이름을 입력하세요");
			findName = sc.next();

			String sql = "SELECT ename, DATE_FORMAT(hiredate, '%Y년 %m월') hiredate, deptno from emp where ename='" + findName + "'";
			try (Connection conn = DriverManager.getConnection(url, user, passwd);
					Statement stmt = conn.createStatement();			
					ResultSet rs = stmt.executeQuery(sql);) {			
				if(rs.next() ) {				
					do {
						System.out.print(rs.getString("ename")+" 직원은 근무중입니다.");
						System.out.print(rs.getString("hiredate")+"에 입사했고");
						System.out.print("현재 " + rs.getString("deptno")+"번 부서에서 근무하고 있습니다.\n");
					} while (rs.next());
				} else {
					System.out.printf("%s 직원은 근무하지 않습니다.\n", findName);
				}			
			} catch (Exception e) {
				System.err.println("오류 발생 : " + e);
			} 	
		}
		
		sc.close();
	}

}
