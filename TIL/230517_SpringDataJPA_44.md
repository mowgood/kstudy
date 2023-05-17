## [day44] 2023.05.17 수 학습 내용 정리
1. Spring Data JPA
---
## 1. Spring Data JPA  
- Spring Framework에서 JPA를 편리하게 사용할 수 있도록 지원하는 프로젝트로 CRUD 처리를 위한 공통 인터페이스르 제공한다.  
- Repository 개발 시 인터페이스만 작성해도 실행 시점에 구현 객체를 동적으로 생성하여 주입시킨다. (데이터 접근 계층 개발 시 구현 클래스가 없어도 된다)  
    - Spring Data JPA를 사용하기 위해 일반적으로 **JpaRepository<T, ID>** 인터페이스를 상속한 Repository 인터페이스를 정의한다.
    - Spring이 알아서 해주므로 정의한 인터페이스를 구현할 필요가 없다.
    - *엔티티클래스명Repository* 명으로 JpaRepository를 상속하여 개발
    - *JpaRepository<T : 엔티티클래스명 ID : 기본키 필드의 타입>*

### JpaRepository 주요 메서드
    - \<S extends T> S save(S)  
    새로운 엔티티는 저장하고, 이미 있는 엔티티는 병합
    - delete(T)  
    엔티티 하나를 삭제
    - Optional<T> findById(ID)  
    ID로 엔티티 하나를 조회
    - List<T> findAll(...)  
    모든 엔티티를 조회, 정렬이나 페이징 조건을 파라미터로 제공할 수 있다.  

### JpaRepository 상속 예시
```java
package springjpa.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import springjpa.exam.entity.Emp;


public interface EmpRepository2 extends JpaRepository<Emp, Integer>{
	
	public List<Emp> findByEname(String name);
	public List<Emp> findByEnameIgnoreCase(String name);
	public List<Emp> findByJob(String job);
	public List<Emp> findByJobOrDeptno(String job, int dno);
	public List<Emp> findByJobAndDeptno(String job, int dno);
	public List<Emp> findDistinctByJob(String job);
	public List<Emp> findByDeptno(int dno);
	public List<Emp> findBySalGreaterThan(int inputsal);
	public List<Emp> findBySalGreaterThanEqual(int inputsal);
	public List<Emp> findBySalLessThan(int inputsal);
	public List<Emp> findBySalLessThanEqual(int inputsal);
	public List<Emp> findBySalBetween(int minsal, int maxsal);
	public List<Emp> findByCommNull();
	public List<Emp> findByCommNotNull();

    //날짜
	public List<Emp> findByHiredateAfter(java.sql.Date d);
	public List<Emp> findByHiredateBefore(java.sql.Date d);

	public List<Emp> findByEnameStartsWith(String partname);
	public List<Emp> findByEnameContains(String partname);

	public List<Emp> findByDeptnoOrderBySalDesc(int dno);

    // 특정 부서에서 월급을 가장 많이 받는 직원 3명
	public List<Emp> findTop3ByDeptnoOrderBySalDesc(int dno);
}
```
데이터베이스에 알맞는 구문으로 반영해준다.

```java
package springjpa.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springjpa.exam.entity.Member;


public interface MemberTeamRepository extends JpaRepository<Member, Integer>{
	
	@Query("select m from Member m join m.team t where t.teamname = :tn")
	public List<Member> aaa(@Param("tn") String tname);
	@Query("select t.teamname from Member m join m.team t where m.username = :un")
	public String bbb(@Param("un") String uname);
	
	public List<Member> findByUsername(String username);
	public Long countByUsername(String username);
	public Long countBy();
}
```
Optional 객체로 묶어서 리턴

### 객체 주입 방식
```java
// 1. 생성자 주입
package springjpa.exam.controller;

@Controller
public class VisitorController {
	private VisitorRepository  repository;

	public VisitorController(VisitorRepository repository) {
		this.repository = repository;
	}

	// 생략
}
```
어노테이션을 따로 지정하지 않고 생성자를 통해 객체 생성

```java
// 2. 필드 주입
package springjpa.exam.controller;

@Controller
public class MeetingController  {
	@Autowired
	MeetingRepository repositoryM;
	@Autowired
	ReplyRepository repositoryR;
	
    // 생략
}
```

```java
// 3. final형 멤버변수
package springjpa.exam.controller;

@Controller
@RequiredArgsConstructor
public class EmpController {
    //@Autowired
	private final EmpRepository dao;
	
	// 생략

    @GetMapping("/part")
	public ModelAndView part(int page, int size) {
		ModelAndView mav = new ModelAndView();
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<Emp> pageObj = dao.findAll(pageRequest);
		List<Emp> list = pageObj.toList();
		mav.setViewName("empResult");
		mav.addObject("list", list);
		return mav;
	}
}
```
멤버변수가 final형이여야 자동으로 생성자가 만들어진다. (lombok)

### @Transactional
```java
@RequestMapping(value = "/vdelete")
@Transactional
public ModelAndView delete(int id) {
    ModelAndView mav = new ModelAndView();
    try {
        repository.deleteById(id);			
        mav.addObject("list", repository.findAll());
    } catch(Exception e) {			
        mav.addObject("msg", "글 삭제에 실패했습니다.");
    }
    mav.setViewName("visitorView");
    return mav;
}
```
DML(update, delete) 명령을 필요로 하는 경우, 트랜잭션에서 수행되도록 어노테이션을 설정하여 에러가 발생하면 롤백한다.

### Optional
```java
@RequestMapping(value = "/one", produces = "application/json; charset=utf-8")
@ResponseBody
public Visitor one(int id) {
    Optional<Visitor> result = repository.findById(id); // Optional 객체
    return result.get(); // visitor 객체를 꺼냄		
}
```
- 엔티티 객체를 그냥 리턴하지 않고, Optional 객체에 담아서 리턴 -> null인 경우 처리를 위해 사용  
- 실제 객체를 꺼내서 사용하려면 반드시 get() 메서드를 사용해야 한다.


### Spring Boot Application 어노테이션  
```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springedu2", "springjpa.exam"})
@EnableJpaRepositories(basePackages = {"springjpa.exam.repository"})
@EntityScan(basePackages = {"springjpa.exam.entity"})
public class Springedu2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springedu2Application.class, args);
	}

}
```
- @SpringBootApplication  
스프링 부트의 가장 기본적인 설정을 선언한다.  
내부적으로 @ComponentScan과 @EnableAutoConfiguration을 설정한다.
- @ComponentScan   
스프링 3.1부터 도입된 어노테이션이며 스캔 위치를 설정한다.
- @EnableJpaRepositories  
JPA Repository들을 활성화하기 위한 어노테이션이다.
- @EntityScan  
엔티티 클래스를 스캔할 곳을 지정하는데 사용한다.

### Spring Boot 테스트
- 스프링의 테스트 어노테이션  
    - @SpringBootTest : 전체적으로 테스트
    - @WebMvcTest : Web MVC 프로그램을 단위테스트
    - @DataJpaTest : JPA 부분만 단위테스트

```java
package com.example.springedu2;

// 내장 DB 설정 해제
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class JPA_EmpRepositoryTest {
    @Autowired
    private EmpRepository empR;

    @Test
    void list() {
    	List<Emp> list = empR.findAll();
    	list.stream().forEach(System.out::println);
    }
    @Test
    void list2() {
        List<Emp> list = empR.findAll(Sort.by("sal").descending());
        list.stream().forEach(System.out::println);
    }
}
```