## [day34] 2023.04.24 월 학습 내용 정리
1. Spring MVC
2. Thymeleaf
---
## 1. Spring MVC
- 웹 애플리케이션  
클라이언트(사용자)와 서버 사이에 HTTP 프로토콜을 이용하여 데이터를 주고 받으면서 동작하는 소프트웨어 프로그램.

- 웹 애플리케이션 구조
    - 티어 : 어플리케이션의 구조를 물리적으로 나눈 것
    - 레이어 : 어플리케이션의 구조를 논리적으로 나눈 것

- 스프링 MVC 처리 흐름  
스프링 MVC는 프론트 컨트롤러 패턴을 적용한다.  
(프론트 컨트롤러 : 하나의 핸들러 객체를 통해서 요청을 할당하고, 일관된 처리를 작성할 수 있게 하는 개발 패턴)

    1. DispatcherServlet  
    가장 앞에서 요청을 받아들여 FrontController라고 불린다.  
    스프링 프레임워크의 중심이 되는 서블릿으로 클라이언트의 모든 요청을 받아 흐름을 제어하고 각 컨트롤러에 요청을 전달,  
    컨트롤러가 반환한 결과값을 View에 전달해 응답한다.  
    web.xml에 정의되어 있으며, 보통 servlet-context.xml 설정 파일을 읽어 컨테이너를 구동

    2. HandlerMapping  
    클라이언트의 요청 URL을 처리할 컨트롤러를 결정해 DispatcherServlet에 반환한다.  
    @Controller 어노테이션이 적용된 객체의 @RequestMapping 값을 이용해 요청을 처리할 컨트롤러를 탐색한다.  

    3. HandlerAdapter  
    DispatcherServlet의 처리 요청을 변환해서 컨트롤러에 전달한다.  
    컨트롤러의 응답 결과를 DispatcherServlet이 요구하는 형식으로 변환한다.  

    4. Controller  
    실제 클라이언트의 요청을 처리한 뒤, 처리 결과를 void, String, ModelAndView 형태로 반환한다.  
    GET, POST 방식 등 전송 방식에 대한 처리를 어노테이션으로 처리한다.  

    5. ViewResolver  
    컨트롤러의 처리 결과를 보여줄 뷰를 결정한다.  

- Spring MVC 구현 어노테이션
    - @Controller  
    Spring MVC의 Controller 클래스 선언을 단순화시켜준다. 스프링의 컨트롤러 클래스는
    서블릿을 상속할 필요가 없다. @Controller로 등록된 클래스 파일에 대한 Bean 객체를
    자동으로 생성한다. Controller로 사용하고자 하는 클래스에 @Controller 지정하면
    component-scan 으로 자동 등록된다.
        ```
        <context:component-scan base-package=“패키지명"/>
        ```
        
        - Spring Boot에서는 Application.java에 @ComponentScan으로 대신 처리한다
            ```
            @ComponentScan(basePackages = {"com.example.springedu","thymeleaf.exam"})
            ```

        - 파라미터 타입
        - 리턴 타입  
        @ResponseBody : view를 거치지 않고 컨트롤러 이용하여 전달 

- View
    - 클라이언트에서 직접 요청하는 HTML : static
    - 응답하는 용도 HTML : templates

## 2. Thymeleaf  
컨트롤러에서 전달받은 데이터를 추출해 동적인 페이지를 만드는 View Template Engine.  
html 파일 내에서 사용 가능하며 JSP와 같이 서버 사이드 렌더링 방식이다.  

- 장점  
Java, Spring 기반에서 개발하기 쉽다.  
순수 HTML 구조를 유지하여 서버상에서 동작시키지 않아도 되므로 웹 퍼블리셔와 협업이 용이하다.  
Thymeleaf가 HTML 태그의 속성(Attribute)로 작성되므로 기존의 HTML 구조를 건드리지 않기 때문에 static 파일을 사용하듯 해당 내용을 브라우저에서 바로 확인할 수 있다.  

- Natural Template(내추럴 템플릿)  
서버를 구동하지 않으면 순수 HTML로 구성되는 정적인 페이지를, 서버를 구동하면 동적으로 페이지가 생성된다. 이렇게 Thymeleaf는 순수 HTML을 유지하기 때문에 내추럴 템플릿으로도 불린다.  
    ```html
    <h2 th:text="${text}">서버없이 브라우저로 오픈하여 랜더링 하면 보임</h2>
    <h2 data-th-text="${title}">서버없이 브라우저로 오픈하여 랜더링 하면 보임</h2>
    ```

- 문법 정리
1. #{...} 표현식 - 변수 표현식  
컨트롤러에서 전달받은 변수에 접근할 수 있으며 `th:속성명` 에서만 사용 가능하다.

2. @{...} 표현식 : URL 표현식  
서버의 contextPath를 추가한 URI로 변경된다.  
    - @{/} -> "/contextPath"
    - @{/images/1.png} -> "contextPath/images/1.png"

3. 문자 합치기  
합치고 싶은 문자열을 "|"로 감싸거나 + 연산자를 이용해 문자를 합칠 수 있다.  
    ```
    <div th:text="|My name is ${info.name} !! |"></div>
    <div th:text="My name is ' + ${info.name} + ' !!'"></div>
    ```

4. 비교 연산자

5. HTML 태그의 컨텐트 설정 - th:text

- 예시
    ```java
    @Controller
    public class MultiController {	
        @RequestMapping(value="/select")
        public String select_proc() {
            System.out.println("select ............");
            return  "viewTest";
        }
        @RequestMapping(value="/search")
        public String search_proc() {
            System.out.println("search ............");
            return "viewTest";
        }
        @RequestMapping(value="/insert")
        public String insert_proc(int pageno) { /* pageno 쿼리 문자열을 int로 받음 */
            System.out.println("insert ............"+pageno);
            return  "viewTest";
        }
        @RequestMapping(value="/viewTest")
        public void void_proc(int pageno) {
            System.out.println("viewTest ............");
        }	
    }
    ```

    ```html
    <!-- viewTest.html -->
    <h2>요청 방식 : [[${ #request.method }]]</h2>
    <h2>요청 대상 : [[${ #request.requestURI }]]</h2>
    <hr>
    <!-- param : context에 보관된 객체이기 때문에 # 기호 생략 -->
    <h2>쿼리 문자열 : [[${ param.pageno }]]</h2> <!-- 쿼리 문자열 추출(추출된 값이 없으면 무시한다) -->
    <hr>
    <a th:href='${ #request.getHeader("referer") }'>다시 요청 해보자</a>
    ```
    param, session, application,... 은 # 기호를 생략하여 사용한다.

- 예시2
    ```java
        @Controller
        public class QueryStringController1 {
            @RequestMapping("/querystring1")
            public ModelAndView proc(String name) {
                ModelAndView mav = new ModelAndView();
                System.out.println("["+name+"]");
                mav.addObject("spring", name);
                mav.setViewName("queryView1");
                return mav;
            }	
            @RequestMapping("/querystring2")
            public ModelAndView proc(int number) {
                ModelAndView mav = new ModelAndView();
                mav.addObject("spring", number);
                mav.setViewName("queryView1");
                return mav;
            }	
            @RequestMapping("/querystring3")
            public ModelAndView proc(String name, 
                                    @RequestParam("num")int number) { /* 쿼리 문자열에서 num을 추출 */
                ModelAndView mav = new ModelAndView();
                mav.addObject("spring", name+":"+number);
                mav.setViewName("queryView1");
                return mav;
            }
            @RequestMapping("/querystring4")
            public ModelAndView proc(
            @RequestParam("myname1") String name1,
            @RequestParam(value="myname2", required=false) String name2, /* required = false */
            @RequestParam(defaultValue="10") int number1, /* defaultValue 설정 */
            @RequestParam(value="NUM2", defaultValue="100")int number2){
                ModelAndView mav = new ModelAndView();
                mav.addObject("spring", name1+":"+(number1 + number2) 
                        +":"+name2);
                mav.setViewName("queryView1");
                return mav;
            }
        }
    ```


