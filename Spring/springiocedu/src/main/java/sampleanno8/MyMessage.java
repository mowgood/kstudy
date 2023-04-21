package sampleanno8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component  
public class MyMessage {
	@Autowired
//	@Qualifier("message1")
	String message;
	
	public MyMessage(){
		System.out.println("MyMessage 객체 생성 - no args ");		
	}
	@Autowired
	public MyMessage(String m){ /*String 객체가 하나일 경우 자동으로 받아오지만 두개 이상이면 에러 발생*/
		System.out.println("MyMessage 객체 생성 - "+m);		
	}
	@Autowired
	//@Qualifier("message1")
	public void setMessage(String message) {
		System.out.println("MyMessage 객체의 setMessage() 호출 - "+message);
		this.message = message;
	}
	@Autowired
	//@Qualifier("message1")
	public void ccc(String message) {
		System.out.println("MyMessage 객체의 ccc() 호출 - "+message);
		this.message = message;
	}
	@Autowired
	//@Qualifier("message1")
	public void aaa(String message) {
		System.out.println("MyMessage 객체의 aaa() 호출 - "+message);
		this.message = message;
	}
	@Autowired
	//@Qualifier("message1")
	public void bbb(String message) {
		System.out.println("MyMessage 객체의 bbb() 호출 - "+message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}	
}
