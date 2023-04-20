package day16.exercise.mvclab.test;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.sql.Connection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import day16.mvc.MySQLConnect;

@DisplayName("첫 번째 JUnit 테스트 클래스")
public class MySQLConnectTest {
	
	@DisplayName("DB서버 접속 확인")
	@Test
	void test() {
		assumeTrue(MySQLConnect.connect() instanceof Connection);
	}

}
