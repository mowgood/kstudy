package day6;

class Member {
	String name;
	String account;
	String passwd;
	int birthyear;
}

public class MemberTest {

	public static void main(String[] args) {
		 Member member1 = new Member();
		 Member member2 = new Member();
		 Member member3 = new Member();
		 
		 member1.name = "회원1";
		 member1.account = "계정1";
		 member1.passwd = "1111";
		 member1.birthyear = 990101;
		 
		 member2.name = "회원2";
		 member2.account = "계정2";
		 member2.passwd = "2222";
		 member2.birthyear = 990102;
		 
		 member3.name = "회원3";
		 member3.account = "계정3";
		 member3.passwd = "3333";
		 member3.birthyear = 990103;
		 
		 System.out.printf("회원1 : %s(%s,%s,%d)\n", member1.name, member1.account, member1.passwd, member1.birthyear);
		 System.out.printf("회원2 : %s(%s,%s,%d)\n", member2.name, member2.account, member2.passwd, member2.birthyear);
		 System.out.printf("회원3 : %s(%s,%s,%d)\n", member3.name, member3.account, member3.passwd, member3.birthyear);
		  
	}

}