package day17;

class ThreadEx03 {
	public static void main(String args[]) throws Exception {
		ThreadEx3_1 t1 = new ThreadEx3_1();
		// 자식 스레드를 기동시키는 것은 start()
		t1.run(); // 자식 스레드를 기동시킨 것이 아니라 main 스레드 상에서 run()메서드를 호출
	}
}

class ThreadEx3_1 extends Thread {
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
