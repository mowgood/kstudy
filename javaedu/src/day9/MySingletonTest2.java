package day9;

class MySingleton2 {
	private static MySingleton2 obj = null;
	private MySingleton2() { //private를 사용하여 생성자 호출을 막음		
	}
	static MySingleton2 getMy() { //생성자 대신 객체 생성
		if(obj==null) //생성이 안되어 있을 시에만 생성하도록
			obj = new MySingleton2();
		return obj;
	}
	void printMyName() {
		System.out.println("듀크");	
	}
	void printFavoriteFood() {
		System.out.println("갈비");	
	}
}
public class MySingletonTest2 {
	public static void main(String[] args) {
		//MySingleton2 my = new MySingleton2();
		MySingleton2 my = MySingleton2.getMy();
		my.printMyName();
		my.printFavoriteFood();
		System.out.println(my);
		System.out.println(MySingleton2.getMy());
		System.out.println(MySingleton2.getMy());
	}
}
