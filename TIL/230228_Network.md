### 2023.02.28 화
### 네트워크  
컴퓨터와 컴퓨터를 연결해주는 망

### OSI 7계층  
네트워킹을 위한 물리적 장비부터 실제 서비스를 제공하는 애플리케이션에 이르는 단계를 계층화하여 나타냄

### OSI 7계층 구조  
- Application Layer
    - 응용 프로세스가 OSI를 Access하는 수단을 제공
    - `파일전송, 가상 터미널, 전자메일`
- Presentation Layer
    - 응용계층 엔티티간의 데이터 교환을 위해 Syntax에 관한 사항을 관장
	- `Format, 구문변환, 코드변환, 압축, 보안 등`
- Session Layer
    - 대화세션 제공, 대화의 동기 제공
- Transport Layer
    - 응용간 또는 프로세스간 논리적 통로를 제공 (전송단위 : Message)
    - `TCP, UDP, SPX`
- Network Layer
    - 상위계층에 시스템을 연결하는데 필요한 데이터 전송과 교환기능을 제공 (전송단위 : Packet)
	- `IP, IPX.X.25`
- Data Link Layer
    - 물리적 통로를 통하여 인접장치 간 신뢰성 있는 정보를 교환 (전송단위 : Packet)
	- `HDLC, Ethernet, LLC, PPP`
- Physical Layer
    - 인접한 장치간에 비트전송을 위한 전송경로를 2계층에 제공 (전송단위 : Bit)
	- `RS-232C, V.35, ATM, Category5`

### TCP/IP 특징
- OSI 7계층을 단순화하여 4계층으로 정의한 것
    - 응용 계층 : WWW, FTP, Telnet, SMTP와 같은 네트워크 프로그램으로 구성된다
    - 전송 계층 : 각 시스템을 연결하고 TCP 프로토콜을 이용하여 데이터를 전송한다
    - 인터넷 계층 : IP 프로토콜을 이용하여 데이터를 정의하고 경로를 배정한다
    - 물리 계층 : 실제 네트워크에 접근할 수 있도록 하는 물리적인 부분
- 컴퓨터 간 통신할 수 있도록 만든 프로토콜의 종류 중 하나
- 개방형 구조로, 특정 운영체제나 하드웨어에 영향을 받지 않고 근거리와 원거리 모두 데이터 전송 가능
- TCP : 데이터 흐름 관리, 데이터 정확성 확인 등의 역할을 수행
- IP : 데이터(패킷)를 목적지까지 전송함

### IP Adress
- TCP/IP로 연결된 네트워크에서 각각의 컴퓨터를 구분하기 위해 사용하는 주소
- ‘123.123.123.123’과 같이 4개로 구분, 10진수 사용

### IPv6 VS IPv4
- 주소 체계 : IPv4(32bits), IPv6(128bits)
    - IPv6는 128비트 주소 체계를 사용하여 IPv4의 주소 부족 문제를 해결
- 패킷 헤더 : IPv4(가변), IPv6(고정)
    - 헤더를 고정 길이로 변경함
- 보안
    - IPv4는 IPSec 보안 프로토콜을 별도 설치, IPv6에서는 프로토콜 내에 보안 기능을 탑재

### Ping(Packet Internet Groper)
- 원격 호스트의 도달성, 패킷 분실, 응답 시간에 대한 정보 제공
- ping 목적지(ip주소)

### IP주소 확인
- Windows : ipconfig
- Linux : ifconfig

### 프로토콜
- 네트워크에 연결된 컴퓨터들 간의 통신 규약
- TCP/IP 4계층 중 응용 계층에 해당

### 포트  
하나의 컴퓨터에서 여러 개의 네트워크 서비스를 제공하는 경우 이들을 구분

### DNS(Domain Name System)  
도메인 이름을 IP주소로 변환

### HTTP 프로토콜
- 웹 서버가 정적인 웹 페이지에 대한 요청을 받으면 서버는 정적 파일을 찾아서 클라이언트로 전송
- 웹 서버가 동적인 웹 페이지에 대한 요청을 받으면 서버는 동적 페이지를 실행하여 HTML 문서를 생성하고 클라이언트로 응답 전송

### HTTP Request  
[HTTP Method] [URL] [Protocol/Version]
- `POST /servlet/SimpleServlet HTTP/1.1`

### HTTP Response  
[Protocol/Version] [상태코드] [설명]
- `HTTP/1.1 200 OK`  

### 주요 상태코드
- 200 : OK
- 404 : Not Found
- 500 : Internal Server Error

### HTML(HyperText Markup Language)
- 웹 문서의 구조를 정의하고 콘텐츠를 표현하는 기본 마크업 언어
- 웹 애플리케이션에 대한 사용자 인터페이스이다.
- https://google.github.io/styleguide/htmlcssguide.html

### JavaScript
- 웹 브라우저에서 구동되는 스크립트 언어
- HTML문서에 동적인 기능을 추가
- https://google.github.io/styleguide/jsguide.html

### Putty
- Putty를 사용하여 원격 리눅스 서버에 접속
- SSH를 이용하여 서버에 접속해 명령어를 전송하는 원격 서버 접속 프로그램

### Editor 종류
- `Sublime, Atom, Bracket, VSCode`  

### VSCode extension
- Colonize (세미콜론 자동 추가)
    - Shift + enter
- 세미콜론을 찍고, 바로 다음줄로 이동
    - alt + enter
- 세미콜론을 찍고, 다음줄로 이동
    - ctrl + alt + enter
- 세미콜론을 찍고, 커서를 가만히 유지
    - Eclipse Keymap
- 단축키를 이클립스처럼 변경
    - Open in browser
    - 브라우저 default로 설정

### Spring Framework
- Spring Legacy Project 생성 시 오류 해결 방법
    - JDK 중복 설치 확인
    - JDK 버전 확인 (OracleJDK 11 설치)
    - SpringToolSuite.ini 수정
        - vm 부분을 [jdk설치경로\javax.exe] 로 변경/추가
        - Dosgi.requiredJavaVersion=11로 수정

### Maven dependencies 경로 변경
- Eclipse - Window – preferences – Maven – User Setting –> [setting.xml 파일 경로 지정]

### Tomcat 서버 실행
- 시스템 환경 변수
    - CATALINA_HOME
    - JAVA_HOME 추가
- CMD
    - cd \kosa \apache-tomcat-9.0.72\bin

### log4j.xml 오류
- log4j.dtd 지정된 파일을 찾을 수 없습니다.
    - log4j.dtd의 경로를 직접 설정하여 해결