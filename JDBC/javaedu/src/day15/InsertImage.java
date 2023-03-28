package day15;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertImage {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("해당 클래스를 찾을 수 없습니다." + cnfe.getMessage());
			return;
		} 
		String url = "jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8&serverTimezone=UTC";
		String user = "jdbctest";
		String passwd = "jdbctest";
		try (Connection conn = DriverManager.getConnection(url, user, passwd);
				Scanner scan = new Scanner(System.in); // scanner 객체 자동 close
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO imgtest (filename, imgcontent) VALUES (?, ?)")){
			System.out.print("저장할 이미지 파일명을 절대 패스로 입력하세요 : ");
			String name = scan.nextLine();
			File imgFile = new File(name);
			if (imgFile.exists()) { // 파일이 있는지 확인
				FileInputStream fin = new FileInputStream(imgFile); // 바이트 스트림 사용해서 파일 읽기 (바이너리 파일 읽기)
				pstmt.setString(1, imgFile.getName()); 
				pstmt.setBinaryStream(2, fin, (int)imgFile.length());
				pstmt.executeUpdate(); 
				System.out.println("이미지 삽입 성공");
			} else {
				System.out.println("[오류] 이미지가 존재하지 않음");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
