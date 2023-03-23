package day14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectEmp {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		Connection conn = DriverManager.getConnection(url, user, passwd);
		System.out.println(conn.getClass().getName());
		Statement stmt = conn.createStatement();
		System.out.println(stmt.getClass().getName());
		ResultSet rs = stmt.executeQuery("select ename, job, format(sal, 0) sal, deptno  from emp"); // format() 사용시 문자열로 리턴
		System.out.println(rs.getClass().getName());
		System.out.printf("%10s%10s%10s%10s\n", "성명", "직무", "급여", "부서");
		System.out.println("  -----------------------------------------");
		while (rs.next()) { // rs.next()가 참일때까지 출력
				System.out.printf("%10s%10s%10s원%10d\n", 
						rs.getString("ename"), rs.getString("job"), 
						rs.getString("sal"), rs.getInt("deptno"));
	    // 				인덱스 값으로도 가능
		//				rs.getString(1), rs.getString(2), 
		//				rs.getString(3), rs.getInt(4));
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}
