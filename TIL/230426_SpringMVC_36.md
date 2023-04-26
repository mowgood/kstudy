## [day36] 2023.04.26 수 학습 내용 정리
1. Spring MVC
---
day34 실습 리뷰
Lotto
- @SessionAttributes()
---
## 1. Spring MVC
### @PathVariable  
url의 특정 부분을 변수화하는 어노테이션  
@RequestMapping 에서 변수를 {}를 감싸고, 메서드의 파라미터에 @PathVariable을 지정하여 메서드에서 파라미터로 활용한다.  
    ```java
    @RestController
    public class HomeController { 
    @RequestMapping("/{name}")
    public String home(@PathVariable String name) {
    return "Hello, " + name;
    }
    }


    /* /board/list_controller/1/test/듀크 */
    @RequestMapping(value="/board/list_controller/{currentPage}/test/{name}")
    public String getAllBoards(@PathVariable(value="currentPage") int currentPage, 
    @PathVariable(value="name") String name, Model model){
    :
    return "view페이지";
    }

    ```

### @RequestBody, @ResponseBody  
- @RequestBody  
HTTP request body를 전달 형식 그대로 또는 자바 객체로 변환하여 전달받는데 사용된다. 

- @ResponseBody  
자바 객체를 HTTP response body로 전송할 수 있다. 이 때 view를 거치지 않고 컨트롤러가 직접 응답하므로 응답 형식을 설정해야 한다.  

### 로그
- Log4J
- LogBack
- Log4J2

### MultipartFile (파일 업로드 구현)  
클라이언트에서 업로드 되는 파일은 여러 개의 파트로 구성되어 전달된다.  
`multipart/from-data`  -> **POST 방식 요청만 가능하다**
멀티 파트를 아규먼트로 받기 위해서는 컨트롤러 메서드의 매개변수 타입을 다음 세 가지 중 하나로 지정한다.
```
xxx(MultipartFile mfile)
xxx(MultipartFile 타입을 멤버변수로 정의한 VO클래스 vo)
xxx(MultipartFile[] 타입을 멤버변수로 정의한 VO클래스 vo) → 다중 파일일 때
xxx(MultipartRequest mreq) → 다중 파일일 때
```

- 주요 메서드

    |메서드명|설명|
    |---|---|
    |String getName()|파라미터의 이름을 리턴한다|
    |String getOriginalFilename()|업로드 한 파일의 **실제** 이름을 리턴한다|
    |boolean isEmpty()|업로드 한 파일이 존재하지 않으면 true를 리턴한다|
    |long getSize()|업로드 한 파일의 크기를 리턴한다|
    |byte[] getBytes() throws IOException|업로드 한 파일의 데이터를 byte 배열로 리턴한다|
    |InputStream getInputStream()|InputStrem 객체을 리턴한다|
    |void transferTo(File dest)|업로드 한 파일 데이터를 지정한 파일에 저장한다|

---
### TIP
- "data-속성"
    ```html
    <!-- result.html -->
    <button id="rb2" data-pname="둘리">데이터 전송(JSON문자열추출)</button>
    <button id="rb3" data-pname="또치">데이터 전송(VO객체추출)</button>
    <button id="rb4" data-pname="도우너">데이터 전송(Map객체추출)</button>
    ```
    표준이 아닌 새로운 속성을 추가하고 싶을 때는 속성명에 data-를 붙여서 임의로 속성을 추가한다.
