## [day39] 2023.05.10 수 학습 내용 정리
1. 스프링 부트 로깅
---
[Spring 2단계]  
로그  : Logback - 설정파일  
Scheduling  
필터 인터셉터  
에러처리  
--springedu  
AOP  
--- springiocedu  
JPA  
--- jpaedu (새로 생성)  
Sring Data JPA  
Spring Security  
Spring Rest  
(Spring Test)  
--- springedu2 (새로 생성)  

## 1. 스프링 부트 로깅
### LogBack  
Log4j의 후속 버전으로 더욱 성능을 좋게 만든 Java 오픈소스 Logging Framework  
스프링 부트에 기본으로 설정되어 있다.  
- 주요 설정요소
    - ERROR : 요청을 처리하는 중 오류가 발생한 경우 표시
    - WARN : 처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메시지
    - INFO : 상태변경과 같은 정보성 로그를 표시
    - DEBUG : 프로그램을 디버깅하기 위한 정보를 표시
    - TRACE : Debug보다 훨씬 상세한 정보를 표시

출력 레벨의 설정에 따라 설정 레벨 이상의 로그를 출력한다. 
- 루트 레벨(전체 레벨) 전체 로깅 레벨 지정 --> 디폴트  
logging.level.root=info

- 패키지별로 로깅 레벨 지정  
다음 방법으로 상위 패키지의 디폴트 레벨을 설정하고, 하위 패키지들에 대한 각각의 로깅 레벨을 별도로 설정할 수 있다.  
logging.level.org.springframework.web=info  
logging.level.com.example.springedu=debug  
logging.level.thymeleaf.exam=trace  

```xml
<!-- logback-spring.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <property name="LOGS_ABSOLUTE_PATH" value="./logs" />

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}][%thread] %-5level %logger{36} - %msg%n</pattern>
        </layout>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>${LOGS_ABSOLUTE_PATH}/mylogback.log</file>
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="ROLLINGFILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <encoder>
            <pattern>[%d{yyyy-MM-dd HH:mm:ss}:%-3relative][%thread] %-5level %logger{35} - %msg%n</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOGS_ABSOLUTE_PATH}/logback.%d{yyyy-MM-dd}.log</fileNamePattern>
        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>

    <logger name="com.example.springedu.controller.LogTestController1" level="DEBUG">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="ROLLINGFILE" />
    </logger>
    <logger name="com.example.springedu.controller.LogTestController2" level="TRACE" additivity="false">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
    </logger>
</configuration>
```
- 대소문자 구별하지 않는다.
- name attribute 를 반드시 지정해야 한다.
- logback-spring.xml 은 appender 와 logger 크게 두개로 구분된다.
- Dynamic Reloading 기능을 지원한다.
ex. 60 초 주기마다 로그파일(logback-spring.xml)이 바뀌었는지 검사하고 바뀌었으면 프로그램
 갱신한다.

 1. appender  
로그 메시지가 출력될 대상을 결정하는 요소 (콘솔에 출력할지, 파일로 출력할지 등)
2. root(디폴트 logger)와 logger  
package와 level을 설정하고 appender를 참조하게 정의한다.  
    - root
        - 전역 로거 설정
        - 항상 마지막에 수행되는 기본 로거
    - logger
        - 지역 로거 설정
        - additivity 속성으로 root 설정을 마저 수행할 것인지의 여부를 결정 가능(default = true)
3. property  
설정파일에서 사용될 변수값 선언
4. layout과 encoder
    - layout : 로그의 출력 포맷을 지정한다.
    - encoder : Appender 에 포함되며 출력될 로그메시지를 원하는 형식으로 변환하는 역할을 담당한다.   
    FileAppender 에서는 encoder 를 사용하여 layout 은 설정한다.
5. file  
기록할 파일명과 경로를 설정한다.  
6. pattern  
    - %logger{length} : Logger name 을 축약할 수 있다. {length}는 최대 자리 수
    - %-5level : 로그 레벨, -5 는 출력의 고정폭 값(5 글자)
    - %msg : - 로그 메시지 (=%message)
    - ${PID:-} : 프로세스 아이디
    - %d : 로그 기록시간
    - %p : 로깅 레벨
    - %F : 로깅이 발생한 프로그램 파일명
    - %M : 로깅일 발생한 메소드의 명
    - %l : 로깅이 발생한 호출지의 정보
    - %L : 로깅이 발생한 호출지의 라인 수
    - %thread : 현재 Thread 명
    - %t : 로깅이 발생한 Thread 명
    - %c : 로깅이 발생한 카테고리
    - %C : 로깅이 발생한 클래스 명
    - %m : 로그 메시지
    - %n : 줄바꿈(new line)
    - %% : %를 출력
    - %r : 애플리케이션 시작 이후부터 로깅이 발생한 시점까지의 시간(ms)

