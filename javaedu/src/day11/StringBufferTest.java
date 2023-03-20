package day11;

public class StringBufferTest {

	public static void main(String[] args) {		
		StringBuffer buffer = new StringBuffer();
		String str = "자바프로그래밍";
     	buffer.append(str);

        System.out.printf("%s\n", buffer);
        buffer.reverse();
        System.out.printf("%s\n", buffer);
        System.out.printf("길이 : %d\n", buffer.length());
        buffer.append(str);
        buffer.append(str);
        buffer.append(str);
        System.out.printf("%s\n", buffer);
        
        StringBuffer bf1 = new StringBuffer("가나다");
        StringBuffer bf2 = new StringBuffer();
        bf2.append("가나다");
        System.out.printf("%b\n", bf1.equals(bf2)); // false -> 각각 참조값이 다름 (object에 있는 equals가 대신 호출되기 때문)
        System.out.printf("%b\n", bf1 == bf2); // false -> ==, equals 둘 다 false
        System.out.printf("%b\n", bf1.toString().equals(bf2.toString())); // true -> String형(문자열)으로 변환해서 비교
	}
}









