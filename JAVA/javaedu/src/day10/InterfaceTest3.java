package day10;

interface InterCalculator {

	int add(int x, int y);

	int sub(int x, int y);

	default int mul(int x, int y) { // default 메서드는 오버라이딩이 선택적
		return x * y;
	}
	
	static void print() {		 
        System.out.println("사칙 연산 처리");
    }
}

class Calculator implements InterCalculator {

	@Override
	public int add(int x, int y) {
		return x + y;
	}

	@Override
	public int sub(int x, int y) {
		return x - y;
	}
	
	/*@Override
    public int mul(int x, int y) {
        System.out.println(x + "와 " + y + "를 곱합니다.");
        return x * y;
    }*/
	
	public int div(int x, int y) {
		if (y != 0)
			return x/y;
		return 0;
	}
}

public interface InterfaceTest3 {
	public static void main(String[] args) {
		InterCalculator cal = new Calculator(); //다형성, 자손 객체를 조상 인터페이스에 담아 사용
		System.out.println("5 * 3 = " + cal.mul(5, 3));
		System.out.println("5 + 3 = " + cal.add(5, 3));
		System.out.println("5 - 3 = " + cal.sub(5, 3));
		System.out.println("5 - 3 = " + ((Calculator)cal).div(5, 3)); //자손에서 추가된 메서드
		InterCalculator.print();		
	}
}
