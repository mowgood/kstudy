## [day18] 2023.03.29 화 학습 내용 정리
1. 람다2
2. Stream (Java 8 Stream API)
---
#### day16 실습 리뷰

#### day17 실습2 리뷰
람다식 구현
- API document 확인하기  
https://docs.oracle.com/en/java/javase/11/docs/api/index.html

---

## 1. 람다 2
### 메소드 참조  
메소드를 참조해 매개변수의 정보 및 리턴 타입을 알아내 람다식에서 불필요한 매개변수를 제거한다
```java
(left, right) -> Math.max(left, right);
```  
- 정적 메소드와 인스턴스 메소드 참조  
람다식에서 정적 메소드를 참조할 경우 클래스 이름 뒤에 :: 기호를 붙이고 정적 메소드 이름을 기술한다
    ```
    클래스 :: 메소드
    ```
- 인스턴스 메소드일 경우에는 객체를 생성한 다음 참조 변수 뒤에 :: 기호를 붙이고 인스턴스 메소드 이름을 기술한다  
    ```
    참조변수 :: 메소드
    ```

### 매개변수의 메소드 참조
- 람다식에서 제공되는 a 매개변수의 메소드를 호출할 때 b 매개변수를 매개 값으로 사용한다
    ```
    (a, b) -> { a.instanceMethod(b); }
    ```
- a의 클래스 이름 뒤에 :: 기호를 붙이고 메소드 이름을 기술한다
    ```
    클래스 :: instanceMethod
    ```

### 생성자 참조
- 객체를 생성하는 것. 람다식이 단순히 객체를 생성하고 리턴하도록 구성되면 람다식을 생성자 참조로 대치 가능
    ```
    (a, b) -> { return new 클래스(a, b); }
    ```
- 클래스 이름 뒤에 :: 기호를 붙이고 new 연산자를 기술
    ```
    클래스 :: new
    ```
> 생성자가 오버로딩되어 여러 개가 있을 경우,  
컴파일러는 함수형 인터페으스의 추상 메소드와 동일한 매개변수 타입과 개수를 가지고 있는 생성자를 찾아 호출한다.  
(해당 생성자가 존재하지 않으면 컴파일 오류가 발생한다)

<br><br>

```java
package day18;

@FunctionalInterface
interface MyFunctionalInterface2 {
	public void method2(int x);
}

public class LambdaTest6 {
	public static void main(String[] args) {
		MyFunctionalInterface2 fi = (x) -> {
			System.out.println(x);
		};
		fi.method2(2);

		fi = x -> System.out.println(x);
		fi.method2(2);

		fi = System.out::println; // 메서드 호출 람다식
		fi.method2(2);
	}
}
```

```java
package day18;

import java.util.Date;

@FunctionalInterface
interface MyFunctionalInterface4 {
	public Date method4();
}

public class LambdaTest8 {
	public static void main(String[] args) {
		MyFunctionalInterface4 fi = () -> {
			return new Date();
		};
		System.out.println(fi.method4());

		fi = () -> {
			return new Date();
		};
		System.out.println(fi.method4());

		fi = () -> new Date();
		System.out.println(fi.method4());

        // 매개변수와 화살표 없이 람다식 사용
		fi =  Date::new; // Date의 생성자 중 method4() 와 같은 타입의 생성자가 호출된다
		System.out.println(fi.method4());		
	}
}

```

```java
// 함수형 인터페이스의 리턴값 유형이 배열인 경우
package day18;

@FunctionalInterface
interface MyFunctionalInterface5 {
	public int[] method5(int size);
}

public class LambdaTest9 {
	public static void main(String[] args) {
		MyFunctionalInterface5 fi = (int size) -> {
			return new int[size];
		};
		System.out.println(fi.method5(10).length);

		fi = size -> {
			return new int[size];
		};
		System.out.println(fi.method5(5).length);

		fi = size -> new int[size];
		System.out.println(fi.method5(8).length);

        // 배열 생성 식만으로도 람다식으로 쓸 수 있다
		fi =  int[]::new; 
		System.out.println(fi.method5(20).length);		
	}
}
```

```java
package day18;

@FunctionalInterface
interface MyFunctionalInterface6 {
	public int method6(String str);
}

public class LambdaTest10 {
	public static void main(String[] args) {
		MyFunctionalInterface6 fi = (String str) -> {
			return str.length();
		};
        // HTML의 length를 리턴
		System.out.println(fi.method6("HTML")); // 4 

		fi = str -> {
			return str.length();
		};
		System.out.println(fi.method6("JAVASCRIPT")); // 10

		fi = str -> str.length();
		System.out.println(fi.method6("CSS")); // 3

		fi =  String::length;
		System.out.println(fi.method6("AJAX")); // 4
	}
}
```

```java
// day18 [LamdaTest11.java]
// Comparator : 함수형 인터페이스
List<String> list = Arrays.asList("abc", "aaa", "bbb", "ccc");

Collections.sort(list); // 오름차순 정렬

Collections.sort(list, new Comparator<String>() { // 익명클래스
	public int compare(String s1, String s2) {
		return s2.compareTo(s1); // 내림차순
	}
});

Collections.sort(list, (String s1, String s2) -> { // 내림차순
	return s2.compareTo(s1);
});

Collections.sort(list, (String s1, String s2) -> {
	return s1.compareTo(s2);
});

Collections.sort(list, String::compareTo); // 오름차순 정렬
// 내림차순 구현 불가 -> 첫번째 아규먼트를 가지고 메서드 호출, 전달하는 데이터는 두번째 아규먼트

```

## 2. Stream (Java 8 Stream API)
### 자바의 I/O 스트림  
- 자바8 에서 추가된 스트림 (java.util.stream)   
컬렉션, 배열 등에 저장된 요소들을 하나씩 참조하면서 코드를 실행할 수 있는 기능  
반복문을 쓰지 않고 처리할 수 있다.  

readLine() : 개행문자가 없으면 계속 기다림

- 스트림의 특징
    - `자료의 대상과 관계없이 동일한 연산을 수행할 수 있다.`
    - Array, Collection에 동일한 연산이 수행되어 일관성 있는 처리 기능을 갖게 한다.
    - 스트림은 `1회성`이라 한번 사용한 스트림은 `재사용하지 못한다.`
    - 스트림의 연산은 기존의 자료를 변경하지 않고, 새로운 메모리 위에서 동작한다.
    - 중간 연산과 최종 연산이 구분되며, 최종 연산이 수행된 이후 모든 연산이 적용되는 `지연 연산`을 지원한다. (빅데이터 처리를 위해 만들어진 API들은 대부분 지연 연산을 도입하고 있다.)

- 중간 처리 메소드는 return 타입이 모두 Stream이다. [230329_스트림.pdf]()   
  중간처리 메소드는 체이닝 호출 방식으로 계속해서 호출이 가능하다 (최종 연산 후에는 불가)
    ```java
    // 체이닝 호출 방식 예시
    stream.map(...).filter(...).dinstinct(...).collect(...); // 지연 연산 : 최종 연산 후 전체적으로 해석 -> 효율적인 수행 방식을 내부적으로 찾음 -> 수행
    stream.map(...).filter(...).dinstinct(...).count() // count는 long형 타입 리턴

    ```
- Java 8 부터 컬렉션 및 배열의 요소를 반복 처리하기 위해 스트림을 사용한다.  
요소들이 하나씩 흘러가면서 처리된다는 의미이다. `스트림 -> 데이터 흐름`  

    ```java
    Stream<String> strema = list.stream(); // List 컬렉션의 stream() 메소드로 Stream 객체를 얻는다
    steram.forEach(item -> /* item 처리 */ ); // forEach() 메소드로 요소를 어떻게 처리할지 람다식으로 제공하여 처리
    ```
최종 처리 메서드 수행 후에는 더이상 스트림이 존재하지 않는다.

### 외부 반복자와 내부 반복자  
- 외부 반복자  
일반적으로 사용하는 루프처럼 요소를 사용하는 쪽에서 직접 컬렉션 요소를 하나씩 꺼내와서 반복 처리한다.
- 내부 반복자 (스트림)
처리할 행동(보통 콜백 함수)을 컬렉션 요소에 넘겨주어 반복 처리한다.  

- 스트림
    - 내부 반복자이므로 처리 속도가 빠르고 병렬 처리에 효율적
    - 람다식으로 다양한 요소 처리를 정의
    - 중간 처리와 최종 처리를 수행하도록 파이프 라인 형성
    - 내부 반복자의 이점은 어떻게 요소를 반복시킬 것인가는 컬렉션에 맡겨두고, 개발자는 요소 처리 코드에만 집중
    - 요소들의 반복 순서를 변경하거나, 멀티 코어 CPU를 최대한 활용하기 위해 요소들을 분배시켜 병렬 작업을 할 수 있게 도와준다

- 스트림 파이프라인  
컬렉션의 오리지널 스트림 뒤에 필터링 중간 스트림이 연결될 수 있고, 그 뒤에 매핑 중간 스트림이 연결될 수 있다.

- 스트림 인터페이스   
    - 파일로부터 스트림 얻기  
    Files.lines(Path, Charset) : return Stream<String>    
    텍스트 파일의 행 단위 스트림을 얻는다.

-스트림의 중간 처리 메서드와 최종 처리 메서드  
    - Stream은 요소에 대한 중간 처리와 최종 처리를 수행할 수 있다.
    - 중간 처리(매핑, 필터링, 정렬 등)는 여러 번 사용할 수 있다.
    - 최종 처리(반복, 집계처리 등)는 결과 처리이므로 한 번만 사용 가능.

- 매핑  
스트림의 요소를 다른 요소로 변환하는 중간 처리 기능  
- 매핑 메소드 : maXxx(), asDoubleStream, asLongStream, boxed(), flatMapXxx() 등
- 요소를 다른 요소로 변환
    - mapXxx() : 요소를 다른 요소로 변환한 새로운 스트림을 리턴
        - map() : Stream 객체 데이터가 여러개거나 1차원이 아닌 경우 구조를 그대로 유지
        - faltMap() : 데이터를 1차원 구조로 평탄화 시킨다

> 타입은 그대로, 내용만 변경 : map()  
타입까지 변경 : mapToInt(), mapToLong(), mapToDouble(), ...

- 집계 메서드
리턴 값이 Optional인 경우 getAs...() 메서드 사용해서 변환해줘야 한다.


- 파이프라인 예시
```cmd
ps -ef | more
ls | wc -l

명령1 | 명령2 | 명령3
```

### 소켓 프로그래밍
- 서버  
연결 요청을 먼저 기다리는 프로그램 : java.net.ServerSocket(서버주소, 포트), accept()
- 클라이언트

---
## MEMO
- printf( , ) 의 두번째 아규먼트 : Object...

