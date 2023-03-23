package day7.exercise;

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

public class FriendTest {

	public static void main(String[] args) {
		 Friend[] f = new Friend[5];
		 
		 Friend f1 = new Friend("친구1", "00000000000", "abc@naver.com");
		 Friend f2 = new Friend("친구2", "00000000000", "dfd@naver.com");
		 Friend f3 = new Friend("친구3", "00000000000", "sdf@naver.com");
		 Friend f4 = new Friend("친구4", "00000000000", "asa@naver.com");
		 Friend f5 = new Friend("친구5", "00000000000", "fgh@naver.com");
		 
		 f[0] = f1;
		 f[1] = f2;
		 f[2] = f3;
		 f[3] = f4;
		 f[4] = f5;
		 
		 for(int i=0; i<f.length; i++) {
			 System.out.println(f[i].getInfo());
		 }
		 

	}

}
