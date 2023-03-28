package day17;

public class ThreadEx14 {
	public static void main(String[] args) {
		Thread thread = new PrintThread2();
		thread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		thread.interrupt();
	}
}

class PrintThread2 extends Thread {
	public void run() {
		while (true) {
			System.out.println("실행 중");
			if (Thread.interrupted()) { // interrupted 메소드를 호출하여 자식스레드를 종료
				// 자원 정리
				break;
			}
		}
		System.out.println("자원 정리");
		System.out.println("실행 종료");
	}
}
