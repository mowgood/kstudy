## [day32] 2023.04.20 목 학습 내용 정리
1. Spring IoC
---
### [day32] 23.04.20 실습1 리뷰
- xml 
    - 태그 내용이 없을 경우 종료 태그를 생략한다.
    ```xml 형식
    <bean id="morningGreetingBean" class="exam1.MorningGreetingImpl" />
    ```
    - Spring IoC 컨테이너 꼭 종료하기
    더 이상 사용하지 않는 시점에는 close() 해주기
    ```java
     ((ClassPathXmlApplicationContext)factory).close();
    ```

### [day32] 23.04.20 실습2 리뷰
[]()
- bean 객체 생성 시 클래스의 생성자와 생성자의 메서드가 하나인 경우 name값은 생략 가능하다
    ```xml
    <bean id="h1" class="exam2.Homework">
        <constructor-arg name="homeworkName" value="국어" />
    </bean>
    ```
    ```xml
    <!-- name 생략 -->
    <bean id="h1" class="exam2.Homework">
        <constructor-arg value="국어" /> 
    </bean>
    ```
    ```java
    public class Homework {
        private String homeworkName;

        public Homework(String homeworkName) { // 생성자
            this.homeworkName = homeworkName;
        }

        public String getHomeworkName() {
            return homeworkName;
        }

        public void setHomeworkName(String homeworkName) {
            this.homeworkName = homeworkName;
        }
    }    
    ```

- 단축형으로 bean 객체 생성 시 변수명을 직접 넣어줘도 되고, 인덱스 값을 사용해도 된다.
    ```xml
    <!-- 변수명 직접 입력 -->
    <bean id="h1" class="exam2.Homework"
            c:homeworkName="국어" />
    ```
    ```xml
    <!-- 인덱스 사용 -->
    <bean id="h1" class="exam2.Homework"
            c:_0="국어" />
    ```

---
## 1. Spring IoC
- Spring IoC 컨테이너 초기화
    ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("빈 설정 파일");
    ```

- DL (Dependency Lookup)
    ```java
    타입명 bean = (타입명)context.getBean("빈이름");
    ```

- DI (Dependency Injection)
    1. Construction Injection : 생성자 통해 객체 바인딩(의존 관계를 연결) 
        - Annotaion
        - Bean
    2. Setter Injection : setter 메서드 이용해서 객체 바인딩(의존 관계를 연결) 
        - Annotaion
        - Bean
    3. method Injection : 어노테이션을 정의한 메서드를 이용해서 객체 바인딩(의존 관계를 연결) 
        - Annotaion
    4. field Injection : 어노테이션을 정의한 메서드를 이용해서 객체 바인딩(의존 관계를 연결) 
        - Annotaion

- IoC 설정
    - XML 설정 (선언적인 프로그래밍 : 정해진 규격의 문서를 작성하여 구현을 대신함)
        - 설정 정보 변경시 XML만 변경하면 된다.
        - 많은 프레임워크의 라이브러리가 XML 스키마 이용한 설정의 편리함을 지원한다.
        - 코드를 실행해야 설정 정보의 오류를 확인할 수 있다.
        - 프로젝트 규모에 따라 XML 문서 내용이 많아진다.

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

            <bean id="messageBean1" class="sample1.MessageBeanImpl" >
                <constructor-arg>
                    <value>strawberry</value>
                </constructor-arg>	
                <property name="cost"> <!-- setter -->
                    <value>3000</value>
                </property>
            </bean>	
            
            <bean id="messageBean2" class="sample1.MessageBeanImpl" >
                <constructor-arg>
                    <value>peach</value>
                </constructor-arg>	
                <property name="cost"  value="2000" />	
            </bean>	
        </beans>
        ```

        **\<bean> 태그 :**    
        Spring IoC 컨테이너가 관리할 Bean 객체(자바 클래스) 설정  
        id : 주입 받을 곳에서 호출할 이름  
        class : 주입할 객체의 클래스명  
        factory-method : 객체 생성시 사용될 factory 메서드  
        scope : Bean 객체의 유효 범위 설정(singleton, prototype 등) <br><br>

        **\<constructor-arg> 태그 :**     
        \<bean>의 하위태그로 다른 bean 객체 또는 값을 생성자를 통해 주입하도록 설정
    
        \<ref bean="bean name"/> => 객체를 주입 시  
        \<value>값\</value> => 문자(String), Primitive data 주입 시  
        type 속성 : 값의 타입을 명시해야 하는 경우  
        [ 속성 이용 ]  
        - ref="bean 이름" 
        - value="값"  <br><br>

        **\<property> 태그 :**     
        \<bean>의 하위태그로 다른 bean 객체 또는 값을 setter 메서드를 통해 주입하도록
        설정  
        - name 속성 : 객체 또는 값을 주입할 property 이름을 설정(setter의 이름)  
        \<ref bean="bean name"/> => 객체를 주입 시  
        \<value>값\</value> => 문자(String), Primitive data 주입 시  
        type 속성 : 값의 타입을 명시해야 하는 경우  
        [ 속성 이용 ]  
        - ref="bean 이름"  
        - value="값"  

- prototype 예시
    ```xml
    <bean id="foo0" class="sample2.Foo" scope="prototype"  /> <!-- prototyp/sigleton(default) scope="prototype" -->
    ```
    싱글톤이 아닌 객체는 미리 객체를 생성하지 않는다.

    ```java
    public class FooTestApp {
        public static void main(String[] args) {
            ApplicationContext factory
                = new ClassPathXmlApplicationContext("sample2.xml");
            System.out.println("************ IoC 컨테이너의 초기화 작업 끝 ************\n");
            
            /*요청시마다 필요한 객체 생성*/
            System.out.println("\nScope(singleton/prototype)");
            Foo ob1=(Foo)factory.getBean("foo0"); 
            System.out.println(ob1);
            Foo ob2=(Foo)factory.getBean("foo0");
            System.out.println(ob2);
            Foo ob3=(Foo)factory.getBean("foo0");
            System.out.println(ob3);
            
            ((ClassPathXmlApplicationContext)factory).close(); /* 스프링 IoC 컨테이너를 사용하지 않는 시점에는 반드시 종료해야 한다 */
        }
    }
    ```
    prototype 사용 시 객체 생성이 각각 일어남

- 단축형 bean 설정
    ```xml
    <!-- sample5.xml -->
    <beans xmlns:p="http://www.springframework.org/schema/p">

    <bean id="f2" class="sample5.DateVo" 
        p:name="Dooly"  p:birth="1983-04-22"/> <!--단축형--> <!-- setName, setBirth를 호출한다. -->
    </beans>
    ```
    p : setter 메서드 호출

    ```xml
    <!-- sample6.xml -->
    <bean id="messageBean" class="sample6.MessageBeanImpl"  
        c:name="Dooly"  
        p:phone="123-4567"
        p:outputter-ref="outputRef" /> <!--ref : 객체를 주입-->
    ```
    c : 생성자 메서드 호출  
    -ref : 다른 객체의 참조값을 전달

- DL(Dependency Lookup) 시 형변환을 생략하는 방법
    ```java
    /* sample3.UserServiceApp.java */

    /* 형변환 사용 */
    UserService u1=(UserService)factory.getBean("userService");

    /* 형변환 생략 */
    UserService u2=factory.getBean("userService", UserService.class); /*형변환 대신 두번째 아규먼트에 클래스 값을 준다*/
    ```
    bean 객체를 불러올 때 형변환을 사용하는 대신 두번째 아규먼트에 클래스 값을 부여하여 사용 가능하다.

- bean 객체의 참조값 전달 예시 **(ref)**
    ```xml
    <!-- sample8_1.xml -->
    <!-- 생성자를 통한 객체 생성 -->
    <bean id="developer" class="sample8.Developer">
        <constructor-arg name="emp"  ref="emp1" /> <!--ref : emp1로 만들어진 bean 객체의 참조값을 전달-->
        <constructor-arg name="dept"  value="Development 1 Team"/>
    </bean>

    <bean id="engineer" class="sample8.Engineer">
        <constructor-arg name="emp"  ref="emp2" /> <!--ref : emp1로 만들어진 bean 객체의 참조값을 전달-->
        <constructor-arg name="dept"  value="Technology 1 Team"/>
    </bean>

    <!-- emp1 bean 객체 -->
    <bean id="emp1" class="sample8.Emp"> 
        <constructor-arg name="name"  value="Dooly"/>
        <constructor-arg name="salary"  value="1500000"/>
    </bean>

    <!-- emp2 bean 객체 -->
    <bean id="emp2" class="sample8.Emp">
        <constructor-arg name="name"  value="Duke"/>
        <constructor-arg name="salary"  value="2500000"/>
    </bean>
    ```

    ```java
    /* sample8.Developer.java */
    public class Developer{
        private Emp emp; // Emp 객체
        private String dept;
        
        public Developer() {
            super();
        }
        public Developer(Emp emp, String dept) {
            super();
            this.emp = emp;
            this.dept = dept;
        }
        @Override
        public String toString() {
            return emp.toString() + " Department : " + dept;
        }
    }
    ```

- Annotation 설정  
소스안에 정해진 ANNOTATION 들을 사용한다.