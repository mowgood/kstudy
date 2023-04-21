## [day33] 2023.04.21 금 학습 내용 정리
1. Spring IoC
2. Spring Boot
---
## 1. Spring IoC
- autowire 속성
    ```xml
    <bean id="myProcess0"  class="sample9.UserShow" /> <!-- argument를 갖지 않는 생성자 객체 생성 -->
    <bean id="myProcess1"  class="sample9.UserShow"  autowire="byName"/> <!-- 이름으로 Setter injection -->
    <bean id="myProcess2"  class="sample9.UserShow"  autowire="byType"/> <!-- 타입으로 Setter injection -->
    <bean id="myProcess3"  class="sample9.UserShow"  autowire="constructor"/>
    ```
    property 태그 대신 autowire 속성을 사용하여 Setter injection 할 수 있다.
    - byName : 프로퍼티 명과 똑같은 이름으로 만들어진 객체를 설정
    - byType : 매개변수 타입과 똑같은 타입의 객체를 찾아서 설정한다.
    - constructor : 생성자를 통해서 injection한다.

- ANNOTATION
    - @Component  
        - 클래스에 선언하며 해당 클래스를 bean 객체로 등록한다.  
        - 스프링에 의해서 관리되는 bean 객체를 정의한다.  
        - bean 이름은 해당 클래스명(첫 글자는 소문자로 변경)이 사용된다.  
        - 범위는 디폴트 singleton이고, @Scope를 사용하여 지정할 수 있다.  
        - 소스안의 어노테이션이 적용되려면 다음과 같은 태그들이 설정파일에 정의되어 있어야 한다.
            ```
            <context:annotation-config> <!-- - @Autowired 만 사용했을 때 -->
            <context:component-scan base-package="xxx" /> <!-- - 모든 어노테이션 -->
            <!-- 해당 패키지와 해당 패키지의 모든 자손패키지에서 찾는다 -->
            ```
            base-package의 자손패키지를 스캐닝한다.

    - @Scope  
        - 스프링은 기본적으로 빈의 범위를 "singleton"으로 설정한다.  
        - 다른 범위를 지정하고 싶다면 @Scope 어노테이션을 이용하여 범위를 지정한다.  
        - 설정 : prototype, singleton, request, session, globalSession  
            ```
            @Component
            @Scope(value="prototype")
            public class Worker {
                
            }
            ```

    - @Autowired  
        - Spring에서 의존관계를 자동으로 설정할 때 사용된다.  
        - **생성자, 필드, 메서드** 세 곳에 적용이 가능하며, **타입을 이용한 프로퍼티 자동 설정**기능을 제공한다.
        - 즉, 해당 타입의 빈 객체가 없으면 예외를 발생시킨다.  
        - 프로퍼티 설정 메서드(ex. setXXX()) 형식이 아닌 일반 메서드에도 적용 가능하다.  
        - 객체가 두개이상 만들어지면 멤버변수 이름이 같은 것을 우선으로 한다.  
        - 설정이 필수가 아닐 경우 @Autowired(required=false)로 선언한다.  

    - @Qualifier  
    같은 타입의 빈이 2개 이상 존재하게 되면 예외가 발생하는데, Autowired에서도 이러한 문제가 발생한다. 이 경우 @Qualifier를 사용하면 동일한 타입의 빈 중 특정 빈을 사용하도록 하여 문제를 해결할 수 있다.
        ```
        @Autowired
        @Qualifier("mytest")
        private Test test;

        @Autowired
        @Qualifier("mytest")
        private Test test;
        ```

    - @Resource  
        - @Autowired + @Qualifier 을 합친 기능을 제공한다.    
        - java에서 나온 어노테이션으로, 스프링에서는 주로 @Autowired를 사용한다.
    
## 2. Spring Boot
스프링 부트는 스프링으로 애플리케이션을 만들 때 필요한 **초기 설정을 간편하게 처리해주는 별도의 프레임워크**이다.  

- war : web archive
    - 하나의 웹 애플리케이션(웹프로젝트)을 배포할 때 생성하는 파일 포맷
    - WAS에 war 파일로 배치(배포)

```
<!-- 콘솔에서 java 클래스 파일 실행 (jar 이용) -->
java 클래스명
java -jar xxx.jar
```

- 스프링
    - 별도 외장 웹 서버 설치 필요
    - 프로젝트를 War 파일로 빌드하여 배포
        - 처리 속도가 느리며 번거롭다
- 스프링 부트
    - 자체 웹 서버 내장
        - 빠르고 간편한 배포 진행 가능
    - 독립적으로 실행 가능한 Jar 파일로 프로젝트 빌드
        - 도커와 같은 가상화 환경에 빠르게 배포할 수 있다.

### 스프링 부트의 특징
1. 의존성 관리  
'springboot-starter'을 통해 의존성을 제공해주고 서로 호환되는 버전의 모듈 조합을 제공한다.
2. 자동 설정  
애플리케이션에 추가된 라이브러리를 실행하는 데 필요한 환경설정을 자동으로 해준다.
3. 내장 WAS  
가장 기본이 되는 의존성 'spring-boot-starter-web'은 톰캣을 내장한다.  
자동 설정 기능을 통해 특정 설정 없이도 톰캣을 실행할 수 있으며 필요에 따라서는 다른 웹서버로도 대체 가능하다.
4. 모니터링  
스프링 부트 액추에이터라는 자체 모니터링 도구가 있어서 서비스 운영에서 필요한 요소들을 모니터링 할 수 있다.

