package day11.exercise;

import java.util.*;

/*
[ 실습 1 ] LinkedList 실습

day7.FriendTest.java 를 찾아서 day11.LinkedListLab1.java 로 
복사한다.
Friend 타입의 배열에 Friend 객체를 저장하는 부분을 
LinkedList 객체를 생성해서 저장하고 처리하는 소스로 변경하여 구현한다.
 */

class Person {
	private String name;
	Person(String name) {
		this.name = name;
	}
	public String getInfo() {
		return name;
	}
}

class Friend extends Person {
	private String phoneNum;
	private String email;
	
	Friend(String name, String phoneNum, String email) {
		super(name);
		this.phoneNum = phoneNum;
		this.email = email;
	}
	
	public String getInfo() {
		return super.getInfo() + " " + phoneNum + " " + email;
	}
}

public class LinkedListLab1 {

	public static void main(String[] args) {
		LinkedList<Friend> list = new LinkedList<>();
		list.add(new Friend("둘리", "01038473727", "abc@naver.com"));
		list.add(new Friend("또치", "01012345342", "ass@naver.com"));
		list.add(new Friend("도우너", "01034785342", "jsd@naver.com"));
		
		for(Friend f : list) {
			System.out.println(f.getInfo());
		}
	}

}
