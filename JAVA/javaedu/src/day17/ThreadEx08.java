package day17;

public class ThreadEx08 {
	public static void main(String[] args) {
		Thread mainThread = Thread.currentThread();
		System.out.println("[ 프로그램 시작 스레드 이름 ] : " + mainThread.getName()); // 메인스레드
		System.out.println("-------------------------------");
		ThreadA threadA = new ThreadA();
		System.out.println("작업 스레드 이름: " + threadA.getName());

		ThreadB threadB = new ThreadB("ThreadB");
		System.out.println("작업 스레드 이름: " + threadB.getName());

		ThreadC threadC = new ThreadC();
		System.out.println("작업 스레드 이름: " + threadC.getName());
		threadA.start();
		threadB.start();
		threadC.start();

		for (int i = 0; i < 3; i++)
			System.out.println("프로그램 시작 스레드 이름: " + mainThread.getName());
	}
}

class ThreadA extends Thread {
	public ThreadA() {
		setName("ThreadA"); // 스레드 이름이 고정
	}

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(getName() + "가 출력한 내용");
		}
		System.out.println("-------------------------------");
	}
}

class ThreadB extends Thread {
	public ThreadB(String name) { // 객체 생성 시점에서 스레드 이름 설정 가능
		super(name); // 조상 생성자를 호출하여 이름 등록
	}

	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(getName() + "가 출력한 내용");
		}
		System.out.println("-------------------------------");
	}
}

class ThreadC extends Thread { // JVM이 자동으로 스레드에 이름을 부여해준다
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(getName() + "가 출력한 내용");
		}
		System.out.println("-------------------------------");
	}
}