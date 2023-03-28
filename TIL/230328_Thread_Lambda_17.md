## [day17] 2023.03.27 월 학습 내용 정리
1. 멀티스레드 프로그래밍
2. 동기화
3. 람다
---
#### day16 실습 리뷰

### day17 실습1
- final형을 쓰면 속도 성능에 좋다

---
## 1. 멀티스레드 프로그래밍
- 프로세스  
실행 중인 프로그램, 자원과 쓰레드로 구성
- 쓰레드  
프로세스 내에서 실제 작업을 수행, 모든 프로세스는 하나 이상의 쓰레드를 가지고 있다.

`하나의 새로운 프로세스를 생성하는 것보다 하나의 새로운 쓰레드를 생성하는 것이 더 적은 비용이 든다`

### CGI / Servlet
- CGI (멀티프로세스)
    - http://.../xxx.cgi  
    - http://.../cgi-bin/xxx
    - 언어의 제한이 없다 (주로 C언어를 사용)
    - API가 없다
- Fast CGI (멀티스레드 기반의 시작)
    - C언어
- Servlet (멀테스레드)
    - Java 언어

> CGI / Servlet  
처리 방식 때문에 성능 차이가 나타남.  
웹 클라이언트에서 같은 프로그램을 요청하면 계속해서 메모리에 올렸다 내렸다를 반복해야 한다.  


### 스레드 구현 방식
1. Thread 클래스를 상속
```java
class MyThread extends Thread {
    public void run () { /* 작업내용 */ }  // Thread클래스의 run()을 오버라이딩
}
```

2. Runnable 인터페이스를 구현
```java
class MyThread implements Runnable {
    public void run() { /* 작업내용 */ } // Runnable 인터페이스의 추상메서드 run()을 구현
}
```

```java
// Thread 클래스를 상속
MyThread myt = new MyThread();
myt.start();

// Runnable 인터페이스를 구현
// MyThread 객체를 먼저 선언한 다음에 추가상속 해야한다.
MyThread myt = new MyThread();
Thread t = new Thread(myt);
myt.start();
```

메서드 오버라이딩해서 구현할 때는 예외처리가 제한적이다  
throws절 대신 `try~catch`를 사용해야 함  
오버라이딩 시 throws 절을 마음대로 사용하지 못함  
조상이 throws하는 예외보다 더 많은/큰 범위를 throws하지 못한다  

### 스레드 상태
- 실행 대기 상태 : 실행을 기다리고 있는 상태
- 실행 상태 : CPU 스케줄링에 따라 CPU를 점유하고 run() 메소드를 실행. 스케줄링에 의해 다시 실행 대기 상태로 돌아갔다가 다른 스레드가 실행 상태 반복  
- 종료 상태 : 실행 상태에서 run() 메소드가 종료되어 실행할 코드 없이 스레드의 실행을 멈춘 상태

- running
- runnable(ready) : 큐에 들어와 있는 상태 (언제든지 수행 가능)
- not runnable(not ready) : 큐에서 아예 빼버림 <br><br>

> start() : 스레드를 기동시키는 메서드  
stop() : 스레드 강제 종료 -> 사용해선 안됨  
-> 사용하던 자원을 반납하는 등 작업을 수행한 후 스레드 스스로 종료될 수 있게 구현해야 한다  


### 안전하게 스레드 종료하기
- 스레드를 안전하게 종료하려면 사용하던 리소스(파일, 네트워크 연결)를 정리하고 run() 메소드를 빨리 종료해야 한다
1. while 문으로 반복 실행 시 조건을 이용해 run() 메소드 종료를 유도
    ```java
    public class XXXThread extends Thread {
        private boolean stop;

        public void run() {
            while(!stop) { // stop이 true가 되면 while문을 빠져나감
                // 스레드가 반복 실행하는 코드;
            }
            // 스레드가 사용한 리소스 정리
        } // 스레드 종료
    }
    ```

2. interrupt() 메소드 이용  
스레드가 일시 정지 상태에 있을 때 InterruptedException 예외 발생  
예외 처리를 통해 run() 메소드를 정상 종료  
    ```java
    XThread thread = new XThread();
    thread.start();

    thread.interrupt();

    public viod run() {
        try {
            while(true) {
                Thread.sleep(1); // 일시 정지
            }
        } catch(InterruptedException e) { // 예외 발생 
        }
        // 스레드가 사용한 리소스 정리
    }
    ```

    Thread의 interrupted()와 isInterrupted() 메소드는 interrupt() 메소드 호출 여부를 리턴한다  
    ```java
    boolean status = Thread.interrupted();
    boolean status = objThread.isInterrupted();
    ```

### 데몬 스레드
- 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드
- `주 스레드가 종료되면 데몬 스레드도 따라서 자동 종료`
    - 주 스레드의 보조역할이므로 주 스레드가 종료되면 데몬 스레드는 존재 의미가 사라진다
- 데몬 스레드 적용 예
    - 워드프로세서의 자동 저장
    - 미디어플레이어의 동영상 및 음악 재생
    - 가비지 컬렉터
- 주 스레드가 데몬이 될 스레드의 setDaemon(true)를 호출

```java
Thread t = new Thread(new ThreadEx15());
t.setDaemon(true); // 데몬 설정 후 스레드 실행해야함
t.start();
```

```java
package day17;

class ThreadEx15 implements Runnable {
	static boolean autoSave = false;

	public static void main(String[] args) {
		Thread t = new Thread(new ThreadEx15());
        // 꼭 스레드 기동 전에 데몬 설정 해야한다
		t.setDaemon(true); // 이 부분이 없으면 종료되지 않는다.
		t.start();

		for (int i = 1; i <= 20; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			System.out.println(i);

			if (i == 5)
				autoSave = true;
		}

		System.out.println("프로그램을 종료합니다.");
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(3 * 1000); // 3초마다
			} catch (InterruptedException e) {
			}

			// autoSave의 값이 true이면 autoSave()를 호출한다.
			if (autoSave) {
				autoSave();
			}
		}
	}

	public void autoSave() {
		System.out.println("작업파일이 자동저장되었습니다.");
	}
}

```

## 2. 동기화  
- 동기화된 컬렉션
    - 동기화된 메소드로 구성된 Vector와 Hashtable는 멀티 스레드 환경에서 안전하게 요소를 처리한다.

- synchronized  
synchronized 키워드를 통해 스레드간 동기화를 시킨다. 
현재 데이터를 사용중인 스레드를 제외하고 나머지 스레드들은 데이터에 접근할 수 없도록 한다.

- synchronized 메서드  

- Colelctions의 synchroizedXXX() 메소드  
    ArrayList, HashSet, HashMap 등 비동기화된 메소드를 동기화된 메소드로 래핑
    ```java 
    synchronizedlist(List<T> list) // List를 동기화된 List로 리턴
    synchronizedMap(Map<K,V> m) // Map을 동기화된 Map으로 리턴
    synchronizedSet(Set<T> s) // Set을 동기화된 Set으로 리턴

    // 하나의 스레드가 접근 중이면 다른 스레드는 접근이 안됨
    List<T> list = Collections.synchronizedList(new ArrayList<T>());
    Set<E> set = Collections.synchronizedSet(new HashSet<E>());
    Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());
    ```

Vector, ArrayList
StringBuffer(동기화 필요할 때 사용) StringBuilder(동기화 필요없을 때 사용)

- CopyOnWriteArrayList<>()  

## 3. 람다  
- 람다식  
메서드 정의를 하나의 식으로 표현한 것.    
자바에서 함수적 프로그래밍 지원 기법으로서 익명 클래스 객체가 된다.

- 함수형 프로그래밍  
함수를 정의하고 이 함수를 데이터 처리부로 보내 데이터를 처리하는 기법  

- 람다식
    1. MyTest.pr((int n) -> { return n+100;});

    2. MyTest.pr((n) -> {return n+100;});

    3. MyTest.pr(n->{return n+100;});  
    매개변수가 하나일때만 괄호 생략 가능

    4. MyTest.pr(n->n+100);  
    수행문장이 하나일 경우, 리턴하는 값을 정의하는 식일 때
    블록해제, 리턴문 생략 가능


> 인터페이스가 단 하나의 추상 메소드를 가지는 것  
추상메소드가 하나여야 람다식을 만들 수 있다.

- 함수형 인터페이스
    - 인터페이스1

    ```java
    public interface Runnable {
        void run();
    }

    //람다식
    () -> {...}
    ```

    - 인터페이스2

    ```java
    // 어노테이션을 정의하여 인터페이스를 만들면 @FuntinalInterface의 자격을 갖추고 있는지 컴파일러에서 확인한다
    // 함수형 인터페이스(함수로 만들 수 있는) 자격을 갖추고 있는지 확인해준다
    @FunctionalInterface
    public interface Calculable {
        void calculate(int x, int y);
    }

    //람다식
    (x, y) -> {...}

    ```
    
- 매개변수가 없는 람다식  
함수형 인터페이스의 추상 메소드에 매개변수가 없는 경우.  
실행문이 두 개 이상일 경우에는 중괄호를 생략할 수 없고, 하나일 경우에만 생략할 수 있다.  
    ```java
    () -> {
        실행문;
        실행문;
    }

    () -> 실행문
    ```

- 매개변수가 있는 람다식  
함수형 인터페이스의 추상 메소드에 매개변수가 있는 경우.  
매개변수를 선언할 때 타입은 생략할 수 있다.  
    ```java
    (타입, 매개변수, ...) -> {
        실행문;
        실행문;
    }

    (타입 매개변수, ...) -> 실행문

    //
    (매개변수, ...) -> {
        실행문;
        실행문;
    }

    (매개변수, ...) -> 실행문

    // 매개변수가 하나일 경우 괄호 생략 가능
    매개변수 -> {
        실행문;
        실행문;
    }

    매개변수 -> 실행문
    ```

-리턴값이 있는 람다식  
함수형 인터페이스의 추상 메소드에 리턴 값이 있는 경우.  
return 문 하나만 있는 경우 중괄호와 함께 return 키워드를 생략할 수 있다.  
리턴값은 연산식 또는 리턴값이 있는 메소드의 호출식으로 대체 가능.  


람다식은 익명 클래스와 다르게 클래스가 만들어지지 않는다.

```java
package day17;

interface Calculation {
	public int add(int a, int b);
}

public class LambdaTest2 {
	public static void exec(Calculation com) {
		int k = 10;
		int m = 20;
		int value = com.add(k, m);
		System.out.println("덧셈 결과 : " + value);
	}

	public static void main(String[] args) {
		exec(new Calculation() { // 익명 클래스
			public int add(int a, int b) {
				return a + b;
			}
		});

		exec((int a, int b) -> {
			return a + b;
		});
		
		exec((a, b) -> a*a + b*b);
	}
}
```
