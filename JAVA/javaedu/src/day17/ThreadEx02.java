package day17;

class ThreadEx02 {
	public static void main(String args[]) throws Exception {
		ThreadEx2_1 t1 = new ThreadEx2_1();
		t1.start(); // 스레드마다 각각 콜스택을 만든다 (start 시 콜스택을 만든다)
		
		// 결과 : 자식 스레드의 콜스택 정보가 출력됨 
		// Main 메서드 호출에 대한 정보는 메인 스레드에 보관되어 있음
	}
}

class ThreadEx2_1 extends Thread {
	public void run() {
		throwException();
	}

	public void throwException() {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
