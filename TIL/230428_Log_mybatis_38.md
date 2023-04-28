## [day38] 2023.04.28 금 학습 내용 정리
1. 로그
2. Mybatis
---
## 1. 로그  
- SLF4J(Spimple Logging Facade for Java)  
여러 다양한 로그 라이브러리의 통합된 인터페이스를 제공,  
단순한 퍼사드 패턴을 수단으로 자바 로깅 API를 제공.  

스프링 부트에서는 스프링 부트 로길 라이브러리 spring-boot-starter-logging가 함께 포함되며  
이 안에 SLF4J와 Logback이 포함된다.

## 2. Mybatis
### 매퍼파일 작성시 #{xxx}와 ${xxx}
- #{xxx} : 'xxx'  
앞뒤에 ``(단일 인용부호)를 붙여서 출력된다.
- ${xxx} : xxx  
앞뒤에 ``(단일 인용부호)를 붙여주지 않고 그대로 출력한다.

### Mybatis 구현 방법
    - DAO를 인터페이스로 대신하는 방법 (자바코드 구현할 필요 X)
        - 애노테이션과 SQL이 정의된 인터페이스(매퍼 인터페이스)
        - 애노테이션이 정의된 인터페이스+SQL이 정의된 매퍼파일
    - 애노테이션이 정의된 인터페이스+SQL과 애노테이션이 정의된 매퍼 클래스

- 매퍼 인터페이스 방법 예시
    ```java
    @Mapper
    public interface MeetingMapperDAO {
        @Select("select id, name, title, date_format(meetingdate, '%Y/%m/%d %H:%i') meetingdate from Meeting")
        public List<MeetingDTO> listM();
        @Select("select id, name, title, date_format(meetingdate, '%Y/%m/%d %H:%i') meetingDate from meeting where title like  concat('%',#{key},'%')")
        public List<MeetingDTO> searchM1(String keyword);
        @Select("select id, name, title, date_format(meetingdate, '%Y/%m/%d %H:%i') meetingDate from meeting where name = #{name}")
        public List<MeetingDTO> searchM2(String name);
        @Insert("insert into meeting (name, title, meetingdate) values (#{name}, #{title}, date_format(#{meetingDate}, '%Y/%m/%d %H:%i'))")
        public boolean insertM(MeetingDTO vo);
        @Delete("delete from meeting where id = #{id}")
        public boolean deleteM(int id) ;
        @Update("update meeting set name = #{name}, title = #{title}, meetingdate = date_format(#{meetingDate}, '%Y/%m/%d %H:%i')  where id = #{id}")
        public boolean updateM(MeetingDTO vo);
        @Select("select id, name, content from reply where refid = #{refid}")
        public List<ReplyVO> listReply(int refid);
        @Insert("insert into reply (name, content, refid) values (#{name}, #{content}, #{refid})")
        public boolean insertReply(ReplyVO vo);
    }
    ```
