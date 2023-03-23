### 2023.03.02 목

### 데이터베이스  
조직의 여러 응용 시스템들이 공용하기 위해 통합하고, 저장한 운영 데이터의 집합

### MySQL  
오픈 소스 RDBMS

### DBMS Client Tools
- MySQL : Workbench
- TOAD, ORANGE, SQL Gate for Oracle, SQL Developer

### 관계형 모델
- Attribute 값은 원자값이어야 함 
- Schema는 데이터베이스의 구조를 정의한다
- Null은 값이 지정되지 않았음을 의미한다
- 릴레이션에는 동일한 튜플이 존재할 수 없다
- 튜플을 구분하기 위한 키가 필요하다

### 기본키(PK, Primary Key)
- 릴레이션에서 튜플을 구분하기 위하여 사용하는 기본 키
- 하나의 애트리뷰트, 또는 애트리뷰트의 집합(복합키)으로 구성될 수 있다.
- 동일한 PK를 지닌 레코드는 존재할 수 없다

### 후보키(Candidate Key)
- 튜플을 식별할 수 있는 최소한의 애트리뷰트 집합
- 유일성과 최소성이 있으면 후보키가 될 수 있다

### 대체키  
- 후보키 중 기본키가 아닌 속성

### 복합키  
- 둘 이상의 애트리뷰트가 하나의 Key를 이루는 경우를 의미한다

### 외래키(FK, Foreign Key)
- 다른 릴레이션의 기본키를 참조하는 키
- 릴레이션 간의 관계를 나타내기 위하여 사용
- Null이 가능할 수도 있다

### MySQL 연산자
- 문자열 
    - Single Quotes(‘)을 사용한다
    - MySQL은 Double Quotes(“)를 사용할 수 있다
- 연산자
    - 동등비교   =
    - 부정비교   <> 를 일반적으로 사용, !=를 사용해도 됨
    - NOT       NOT 연산자 또는 !를 사용
    - AND, OR   && 또는 ||를 사용
    - 나누기     /
    - 나머지    MOD 나 %
    - REGEXP   패턴이 일치하는지를 확인하는 연산자

### commission_pct 컬럼이 널이 아닌 경우로 조회하기
```sql
select *
from employees
where commission_pct is not null
```
 
### SQL 작성 순서
>SELECT [ALL | DISTINCT] 열_리스트  
[FROM                 테이블_리스트]  
[WHERE                조건]  
[GROUP BY             열_리스트 [HAVING 조건]]  
[ORDER BY             열_리스트 [ASC | DESC]];  

### 연산자
- IN  
list 중의 하나  
- BETWEEN a AND b    
a와 b사이, a와 b를 포함한다  
- LIKE  
문자열 부분 검색  
- IS NULL, IS NOT NULL  
NULL인지 검색한다  
- AND  
둘 다 만족시켜야 하는 경우  
- OR  
둘 중 하나만 만족시키면 되는 경우  
- NOT  
만족하지 않음  
- ANY, ALL  
집합 중 어느 하나, 집합 중 모든 열  
- EXISTS  
존재 유무에 따라  
`한번이라도 부서나 직무를 변경한 적이 있는 직원을 조회
한번이라도 주문한 적이 있는, 한번이라도 휴학한 적이 있는 학생 조회`
- ORDER BY  
컬럼 리스트의 순서로 결과를 정렬한다  
    - ASC : 오름차순
    - DESC : 내림차순

### MySQL 내장 함수
- IFNULL  
컬럼이나 표현식이 널이면 다른 값으로 대체
- ISNULL  
컬럼이나 표현식이 널인지를 검색한다
- 현재 시각  
now(), sysdate()로 조회
- 날짜 연산  
DATE_ADD, DATE_SUB  
DATE_ADD(NOW(), INTERVAL 1 DAY)  
DATE_ADD(NOW(), INTERVAL 1 DAY)  
- 문자열 결합  
CONCAT  
GROUP_CONCAT
```sql
select GROUP_CONCAT(department_id) from departments;
```
- CAST  
값의 타입을 반환  
CAST(‘2022-10-22’ AS DATE)  
- HEX, UNHEX   
16진수 문자열로 변환, 16진수 문자열을 이진수로 변환  
- MD5, SHA, SHA2  
암호화 해시 함수  
- INET_ATON, INET_NTOA  
IP주소를 부호없는 정수타입으로 변환, 부호없는 정수를 IP주소 문자열로 변환
- JSON_OBJECT  
JSON형태로 리턴한다  

- 문자열을 다루는 함수  
CONCAT(s1, s2)
- 문자열 결합  
Ex 이메일 주소 중 kosa.go.kr을 넣어서 조회
- INITCAP(s)  
첫 글자만 대문자로 변경  
- LOWER(s)  
소문자로 변경  
- UPPER(s)  
대문자로 변경  
- REPLACE(s, p, r)  
문자열 치환  
- SUBSTR(s, m, n)  
부분 문자열  
Ex 이메일 주소 중 5자리만 조회
Ex2    
```sql
select *
from employees
where substr(last_name, 1, 1) = ‘K’; #K로 시작되는 이름 사원조회
```
- LENGTH(s)  
문자열 길이 반환

- 날짜 데이터 타입  
현재 날짜는 sysdate(), now()로 참조  
    - DATE : 년월일을 저장
    - TIME : 시분초를 저장
    - Timestamp : 년월일과 시분초를 저장
Ex
```sql
select now(), date_format(now(), ‘%m-%d-%Y’);
select last_name, hire_date, date_format(hire_date, ‘%m-%d-%Y’) 입사일자 from employees;
```

- 날짜와 시간(date, time, timestamp)을 다루는 함수  
DATE_ADD, DATE_SUB  
DATE_ADD(NOW(), INTERVAL 1 DAY)  

- 집계함수
AVG  
[자기 부서의 평균급여보다 많은 급여를 받는 직원조회]
```sql
select *
from employees e
where salary > (select avg(salary) from employees where department_id = e.department_id);
```

[자기 부서의 평균급여도 출력]
```sql
select last_name, salary, department_id,
round((select avg(salary) from employees where department_id = e.department_id)) 부서평균급여
from employees e;
```

- MAX  
[부서별로 최고급여를 받는 직원정보 조회]
```sql
select max(salary) from employees group by department_id;
```
   
### MySQL 변환 함수  
- DATE_FORMAT 사용  
select DATE_FORMAT(NOW(), ‘%Y%m%d’);
- STR_TO_DATE 사용  
select STR_TO_DATE(’28-11-2022’, /%d-%m-%Y’);

### NULL값
- null값은 값이 없음을 의미하나, 정해지지 않은 값이라는 형태로 인식
- null이 포함된 연산 결과는 null이기 때문

### 인덱스
- 테이블의 컬럼에 대응되는 별도의 객체로 독립적인 저장 공간을 보유한다
- Optimizer가 최적의 실행경로를 설정하는데 중요한 Factor가 된다
- PK가 지정되면 자동으로 인덱스가 만들어진다
- 인덱스의 성능 향상
- 인덱스를 이용하여 별도의 정렬 없이 결과 추출
- 물리적인 디스크 I/O 감소
- 테이블에 저장된 데이터가 차지하는 공간 이외에 저장 공간이 더 필요하다
- 테이블에 저장된 데이터가 자주 변경될 경우 관련 Index도 함께 변경해야 하는 오버헤드로 인하여 성능이 나빠질 수도 있다

### CASE  
- select 절에서의 CASE 활용
```sql
select last_name, salary,
         case when salary < 5000 then ‘D’
              when salary < 7000 then ‘C’
         when salary < 10000 then ‘B’
         when salary < 15000 then ‘A’
         else ‘S’
          end sal_grade
      from employees;
```

- group by 절에서의 case 활용
```sql
select
       case when salary < 5000 then 'D'
               when salary < 7000 then 'C'
      when salary < 10000 then 'B'
      when salary < 15000 then 'A'
else 'S'
        end sal_grade,
    count(*) cnt
from employees
group by (case when salary < 5000 then 'D'
                      when salary < 7000 then 'C'
         when salary < 10000 then 'B'
         when salary < 15000 then 'A'
         else 'S'
           end), (case when salary < 5000 then 5
                      when salary < 7000 then 4
         when salary < 10000 then 3
         when salary < 15000 then 2
         else 1
           end)
order by (case when salary < 5000 then 5
                      when salary < 7000 then 4
         when salary < 10000 then 3
         when salary < 15000 then 2
         else 1
           end);
```

### Outer Join
- join 조건을 만족하지 않는 튜플(짝이 없는 튜플)도 null과 함께 조회된다
- 직원의 부서명 출력 시 아직 부서에 배치되지 않아서 부서명을 출력할 수 없는 경우
    - 아직 미배치 상태의 직원은 부서명없음이라고 출력
```sql
Select last_name, salary, ifnull(d.department_name, ‘부서명없음’)
From employees e left JOIN department d
    On e.department_id = d.department_id;
```

### 서브쿼리
- 쿼리 내부에 포함되어 있는 쿼리
```sql
# ex1
select * from employees
where department_id = (select department_id 
                        from employees
                        where last_name = ‘King’ and first_name = ‘Steven’);

# ex2                     
select * 
from employees
where department_id in (select department_id
                        from employees
                        where last_name = ‘King’);
```

### 빅데이터
- Big Data를 설명하는 4개의 V(Volume, Velocity, Variety, Veracity)에 더하여 다섯번 째 V로서 Value를 제시
- 대용량 데이터를 활용/분석하여 가치 있는 정보를 추출하고, 생성된 지식을 바탕으로 능동적으로 대응하거나 변화를 예측하기 위한 정보화 기술

### NoSQL
- 비 관계형 데이터베이스 (카산트라, 몽고DB, Redis 등)
- RDB 구조와는 다른 형태로 데이터를 저장하여 관리
- 스키마가 없다
- 클러스터에서 실행될 수 있다
- 일관성을 희생하여 다른 속성을 얻는다

### MongoDB
- Database 내부에 여러 Collection이 있으며 Collection에 Document가 있다
- Collecion은 RDB의 테이블, Document는 RDB의 row 역할
    - 관계DB와 Key-Value 시스템의 장점을 결합하여 설계되었다
- Key-Value는 단순하여 속도가 빠르고 확장이 용이
- 관계DB는 질의어가 강력하다
 
### MongoDB 쉘 기본
- show dbs  
시스템의 모든 데이터베이스를 출력
- show collections  
데이터베이스에서 정의된 컬렉션의 목록을 출력
- db.stats()  
하나의 데이터베이스에 대해 상세 정보를 출력
- db.numbers.stats()  
하나의 컬렉션에 대한 상세 정보를 출력
- use users  
user 콜렉션을 사용, 존재하지 않으면 생성한다
- db.users.insert({json 형태의 데이터})  
문서 생성하기  
- db.users.find()  
생성된 문서 조회하기

### MongoDB CRUD
- Create
    - insert()
    - insertOne()
    - insertMany()
- Update
    - update(query, update, option)
    - updateOne(filter, data, options)
    - updateMany(filter, data, options)
- Retrieve
    - find(filter, options)
    - findOne(filter, options)
- Delete
    - deleteOne(filter, options)
    - deleteMany(filter, options)

### MongoDB 실습
- emps Collections에 추가  
db.emps.insert({emp_id:1, last_name:’King’})

- 조회  
db.emps.find()  
db.emps.find({_id:1})  
db.emps.find({last_name:’King’});  

- 제거  
db.emps.remove({})
