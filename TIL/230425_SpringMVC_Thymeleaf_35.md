## [day35] 2023.04.25 화 학습 내용 정리
1. Thymeleaf, Spring MVC
---
## 1. Thymeleaf, Spring MVC
- [Thymeleaf] default prefix  
src/main/resources/templates
- [Thymeleaf] suffix  
.html  
```<HTML xmlns:th="http://www.Thymeleaf.org">```

- 문법  
SpringEL  
Spring Expression Language, 런타임 시 메서드 호출 및 기본 문자열 템플릿 등의 기능을 제공한다.  
1. #{...} 표현식 - 변수 표현식  
컨트롤러에서 전달받은 변수에 접근할 수 있으며 `th:속성명` 에서만 사용 가능하다.

2. @{...} 표현식 : URL 표현식  
서버의 contextPath를 추가한 URI로 변경된다.  
    - @{/} -> "/contextPath" (contextPath를 붙여서 추가된다.)
    - @{/images/1.png} -> "contextPath/images/1.png"
    - @{/vdelete(id=${vo.id})} -> "/contextPath/vdelete?id=2"

3. 문자 합치기  
합치고 싶은 문자열을 "|"로 감싸거나 + 연산자를 이용해 문자를 합칠 수 있다.  
    ```html
    <!-- 파이프 기호로 엮어서 사용 -->
    <div th:text="|My name is ${info.name} !!|"></div>
    <hr>
    <div th:text="My name is ' + ${info.name} + ' !!'"></div>
    ```

4. 비교 연산자
    ```html
    <!-- 이항 연산자 -->
    <div th:text="${info.name != 'kim'}"></div>
    <div th:text="${info.age >= 30}"></div>

    <!-- 삼항 연산자 -->
    <div th:text="${info.name == 'kim' ? 'ok' : 'no'}"></div>
    ```

5. HTML 태그의 컨텐트 설정 - th:text  
    ```html
    <div th:text="${info.name}">유니코</div>
    ```
    **div에 바로 출력시에는 [[ ]] 대괄호 두개를 사용한다.**  

6. HTML 태그의 value 속성의 값 설정 - th:value  
    ```html
    <input type='text' th:value="${info.name}" value="둘리">
    ```

7. th:if, th:unless  
    if~else와 비슷하다. 조건이 참이면 <th:if> 거짓이면 <th:unless>를 표현
    ```html
    <p th:if="${info.age > 18}">입장 가능</p>
    <p th:unless="${info.age <= 18}">입장 가능</p> <!-- 거짓이어야 p 태그를 출력 -->
    <th:block th:if="${info.age > 18}"><hr><p>입장 가능</p><hr></th:block>
    <th:block th:unless="${info.age <= 18}"><hr><p>입장 가능</p><hr></th:block>
    ```

8. th:switch, th:case  
th:case 속성에 지정된 값과 동일한 서브 태그를 표현한다.  
    ```html
    <!-- block : 여러 태그를 묶어 렌더링 여부를 결정한다.(Thymeleaf 문법) -->
    <th:block th:switch="${info.name}">
    <div th:case="올라프">겨울왕국</div>
    <div th:case="또치">아기공룡둘리</div>
    </th:block>
    ```

9. th:each  
for 반복문과 비슷하다. 
    ```html
    <th:block th:each="data:${datas}">
    <h1 th:text="${data}"></h1>
    </th:block>
    ```
    변수명 앞에 status 변수를 추가해 row에 대한 추가정보를 얻을 수 있다.
    ```html
    <th:block th:each="data,status:${datas}">
    <h1 th:text="|${status.count} ${data}|"></h1>
    </th:block> 
    ```
    - status 속성
        - index : 0부터 시작
        - count : 1부터 시작
        - size : 총 개수
        - current : 현재 index의 변수
        - event/odd : 짝수/홀수 여부
        - first/last : 처음/마지막 여부

10. 링크될 대상 URL : th:href="@{}"  
    ```html
    <!-- contextPath를 자동으로 붙여주고, 쿼리 문자열 사용이 가능하다 -->
    <a th:href="@{/vdelete(id=${vo.id})}">
    ```
 
11. th:with="${}"  
    바뀐 이름으로 간단하게 변수를 사용할 수 있다.
    ```html
    <div th:with=”userId=${number}” th:text=”${usesrId}”>
    ```
    변수형태의 값을 재정의하는 속성이다.  
    th:with를 이용하여 새로운 변수값을 생성할 수 있다.

- 모델을 만들어서 보관하는 메서드
    ```java
    @Controller
    public class TestModelController1 {
        @ModelAttribute("v1")
        public String createString() {
            System.out.println("객체 생성 자동호출1");
            return "테스트!!";
        }
        @ModelAttribute("v2")
        public int[] createArray() {
            System.out.println("객체 생성 자동호출2");
            return new int[]{10, 20, 30, 40, 50};
        }
        @ModelAttribute("v3")
        public MyVO createVO() {
            System.out.println("객체 생성 자동호출3");
            MyVO vo = new MyVO( 23, "yellow");
            return vo;
        }	
        @ModelAttribute("v4")
        public Date createDate() {
            System.out.println("객체 생성 자동호출4");		
            return new Date();
        }	
        @ModelAttribute("v5")
        public List<MyVO> createList() {
            System.out.println("객체 생성 자동호출5");
            List<MyVO> list = new ArrayList<MyVO>();
            MyVO vo = new MyVO(7, "red");
            list.add(vo);
            vo = new MyVO(11, "pink");
            list.add(vo);
            return list;
        }	
        @RequestMapping("/modeltest1")
        public String handle() {
            System.out.println("handle() 메서드 호출");		
            return "modelResult1";
        }
    }
    ```

- VO, DTO 구분하기  
    - 바뀔 수 있는 객체 -> DTO  
    - 바뀌지 않는 객체 -> VO로 생성  
    매개변수로 객체를 불러올 때는 setter가 없어도 최초 객체를 생성해 준다.  
    하지만 객체 생성 후 값을 바꿀 경우에는 DTO 사용한다.  

- StringBuffer(Thread safe), StringBuilder(Thread not safe)  
문자열을 편집, API 사용 방법 거의 동일
    ```java
    @Controller
    @SessionAttributes("data1")
    public class TestModelController3 {
        @ModelAttribute("data1")
        public StringBuffer createModel1() {	
            System.out.println("createModel1() 호출");
            return new StringBuffer();
        }
        @ModelAttribute("data2")
        public StringBuffer createModel2() {	
            System.out.println("createModel2() 호출");
            return new StringBuffer();
        }
        @RequestMapping(value="/modeltest3")
        public String handle(@ModelAttribute("data1") StringBuffer vo1, 
                @ModelAttribute("data2") StringBuffer vo2, String str) {
            vo1.append(str+"#");
            vo2.append(str+"@");
            System.out.println("handle 에서 출력 : "+vo1 + " - " + vo2);
            System.out.println("=============================");
            return "modelResult2";
        }	
    }
    ```
    @SessionAttributes("")  
    @SessionAttributes({"count1", "count2"})  
    class 에서만 적용할 수 있는 어노테이션    
    괄호안 객체를 세션에 저장해둔다.   
    따로 지정 안하면 requestScope

- 세션 예제  
CountController.java, CountDTO.java, count.html, countdel.html  
    ```java
    @Controller 
    @SessionAttributes({"count1", "count2"})
    public class CountController {
        @ModelAttribute("count1")
        public CountDTO countMethod1() {
            System.out.println("countMethod1 호출");
            return new CountDTO();
        }
        @ModelAttribute("count2")
        public CountDTO countMethod2() {
            System.out.println("countMethod2 호출");		
            return new CountDTO();
        }	
        @RequestMapping(value="/count") 
        public void handle(@ModelAttribute("count1") CountDTO vo1,
                        @ModelAttribute("count2") CountDTO vo2, int num1, int num2) {
            vo1.setCountNum(num1);
            vo2.setCountNum(num2);
            System.out.println("handle() : "+ 
                    vo1.getCountNum() + " : " + vo2.getCountNum());
            System.out.println("=============================");
        }
        @RequestMapping(value="/countdel")
        public void handle(SessionStatus s) {
            s.setComplete(); /* 세션 객체에 보관하고 있던 값이 사라진다 */
            System.out.println("Both count1 and count2 deleted!");	
            System.out.println("=============================");
        }	
    }
    ```
    ```html
    <h3>Objects stored in count1 names :
                <span th:text="${ session.count1 } ? '있음' : '없음'"></span></h3>
    <h3>Objects stored in count2 names :
                <span th:text="${ session.count2 } ? '있음' : '없음'"></span></h3>
    ```
    session.count1 : session 객체 중 count1으로 보관되어 있는 객체를 꺼낸다.  

- 지정된 세션 객체 지우기
    ```java
    // CountController2.java
    // 쿼리문에 who값으로 특정 세션 객체명을 넣어준다
    @RequestMapping(value="/countdel2")
        public void handle(HttpSession s, String who) {
            if(who != null) {
                s.removeAttribute(who);
                System.out.println(who + " deleted!");		
            }		
            System.out.println("=============================");
        }
    ```

- Model 객체
Spring에서는 Model객체를 재활용한다. 
    ```java
    /* UserController.java */
    @RequestMapping(value = "/userSave", method = RequestMethod.POST)
        public String userSave(UserVO userVo, Model model) {
            System.out.println("----- UserController.userSave() : POST -----");
            System.out.println("userInfo : " + userVo.toString());
            model.addAttribute("msg", "SUCCESS");
            return "userInfo";
        }
    ```

- each
    ```html
    Favorite Fruit : <span th:each="fruit:${userVO.favoriteFruit}" th:text="${fruit}+'&nbsp;'"></span>
    ```
    each 속성이 사용된 태그인 span 태그 자체가 반복되어 수행된다.

- 응답할 view에 대한 정보  
spring에서 정해 놓은 view 객체 : InternalResourceView 사용
    ```java
    @Controller
    public class StaticController {	
        public StaticController() {
            System.out.println("HelloController Create object");
        }
        @RequestMapping("/static")
        public ModelAndView xxx(){
            InternalResourceView view = new InternalResourceView("/staticview.html");
            ModelAndView viewModel = new ModelAndView(view);
            return viewModel; // staticview.html이 요청된다.
        }	
    }
    ```

- RequestMapping
    ```java
    @Controller
    public class ResponseBodyController {
        @RequestMapping(value = "/body/text/{id}", produces="text/plain; charset=utf-8")
        @ResponseBody
        public String getByIdInTEXT(@PathVariable String id) {
            return "<h1>컨트롤러에서 바로 문자열을 리턴해요 : "+id+"</h1>";
        }
        /* 결과 : <h1>컨트롤러에서 바로 문자열을 리턴해요 : HEY</h1> */
        @RequestMapping(value = "/body/htmltext/{id}", 	produces="text/html; charset=utf-8")
        @ResponseBody
        public String getByIdInHTMLTEXT(@PathVariable String id) {
            return "<h1>컨트롤러에서 바로 HTML 문자열을 리턴해요 : " + id +"</h1>";
        }
        /* 결과 : 컨트롤러에서 바로 HTML 문자열을 리턴해요 : HEY */
        @RequestMapping(value = "/body/json/{id}", produces = "application/json; charset=utf-8")  // text/json
        @ResponseBody
        public MyModel getByIdInJSON(@PathVariable String id) {
            MyModel my = new MyModel();
            my.setFlowerName("장미");
            my.setNum(5);
            my.setId(id);
            System.out.println(my);
            return my;
        }
        /* 결과 : {"flowerName":"장미","num":5,"id":"HEY"} */
    }
    ```
    ```java
    // MyModel.java
    @Getter
    @Setter
    @ToString
    public class MyModel {
        private String flowerName;
        private int num;
        private String id;	
    }
    ```
    - @PathVariable : 요청한 URL 문자열에서 특정 부분을 변수화한다.
    - @RequestMapping : 템플릿을 거치지 않고 바로 클라이언트에게 값을 준다.
    - produces 타입
        - text/plain
        - text/html
        - application/json

---
### TIP
```
public ModelAndView seachInternal(
@RequestParam("query") String query, 
@RequestParam("p") int pageNumber){ }
```
파라미터 이름이 같은 경우 @RequestParam()을 생략할 수 있다.