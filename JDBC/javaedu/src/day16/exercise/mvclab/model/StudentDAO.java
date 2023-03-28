package day16.exercise.mvclab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import day16.mvc.MySQLConnect;

public class StudentDAO {
	public List<StudentDTO> getAllStudent() {
		Connection conn = MySQLConnect.connect();
		List<StudentDTO> slist = null;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select name, score from student");
			slist = new ArrayList<StudentDTO>();
			while (rs.next()) {
				StudentDTO vo = new StudentDTO();
				vo.setName(rs.getString("name"));
				vo.setScore(rs.getInt("score"));
				slist.add(vo);
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQLConnect.close(conn);
		return slist;
	}

	public int getScore(StudentDTO dto) {
		Connection conn = MySQLConnect.connect();
		int score = 0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("select score from student where name = '" + dto.getName() + "'");
			if (rs.next()) {
				score = rs.getInt("score");
			} else {
				return -1;
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQLConnect.close(conn);
		return score;
	}

	public boolean insertStudent(StudentDTO dto) {
		boolean result = false;
		Connection conn = MySQLConnect.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("insert into student (name, score) values (?, ?)")) {
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getScore());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQLConnect.close(conn);
		return result;
	}

	public boolean updateStudent(StudentDTO dto) {
		boolean result = false;
		Connection conn = MySQLConnect.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("update student set score = ? where name = ?")) {
			pstmt.setInt(1, dto.getScore());
			pstmt.setString(2, dto.getName());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQLConnect.close(conn);
		return result;
	}

	public boolean deleteStudent(StudentDTO dto) {
		boolean result = false;
		Connection conn = MySQLConnect.connect();
		try (PreparedStatement pstmt = conn.prepareStatement("delete from student where name = ?")) {
			pstmt.setString(1, dto.getName());
			if (pstmt.executeUpdate() > 0) {
				result = true;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		MySQLConnect.close(conn);
		return result;
	}

}
