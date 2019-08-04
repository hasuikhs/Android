1. #### MySQL 구축

   - MySQL Database Setting(도서정보)

     - 제공된 Script file을 이용해서 Database 세팅

       - DataBase : 데이터의 집합

       - DBMS(DataBase ManagementSystem) : Database를 관리, 사용하기 위한 software의 집합

         - Oracle, DB2, informix, sybase, MySQL ...

         - 초창기(계층형 DB)

           ```
           => Oracle, DB2, informix, sybase, MySQL ...
              => 초창기(계층형 DB)
                 네트워크 데이터베이스 등장
                 (이론적으로는 좋았지만, 실제 사용되는 경우는 거의 없었음)
                 수학자가 논문을 하나 발표
                 관계형 데이터 => 데이터를 테이블 형태로 저장
                 => 관계해석, 관계대수
                 => IBM이 해당 논문을 근간으로 DBMS를 구축
                    => DB2의 시초가 되는 DBMS가 탄생
                    => 이것을 시작으로 모든 DBMS는 관계형DBMS로 전환
                    => 1990년대 후반까지 잘사용되다가 객체지향의 패러다임이 시작
                    => DB쪽에서는 프로그램 언어와는 다르게 객체지향의 중요한 특성만
                       받아들여서 객체-관계형 DB로 발전
                    => 현재는 빅데이터 시대로 접어들고 있음
                       비정형 데이터를저장, 관리 할때는 관계형 DB가효율적이지 못함.
                       그래서 No SQL 계열의 DB가 사용되기 시작(몽고DB)
           ```

       1. MySQL DBMS의 기동

          ```
           >> mysqld
          기본적으로 포트번호는 3306을 사용
          ```

       2. MySQL daemon process가 실행이 완료 후 MySQL에 접속 가능

          ```
          >> mysql -u root
          ```

       3. MySQL에 접속 후 사용자 생성부터 시작

          ```
          mysql> create user android identified by "android";
          mysql> create user android@localhost identified by "android";
          ```

       4. MySQL DBMS안에 여러개의 Database를 생성하고 관리 가능

          ```
          mysql> create database lib;
          ```

       5.  3번 단계에서 만든 새로운 사용자(android)에게 DB(lib) 사용 권한을 부여

          ```
          mysql> grant all privileges on lib.* to android;
          mysql> grant all privileges on lib.* to android@localhost;
          ```

       6. 권한 flush

          ```
          mysql> flush privileges;
          ```

       7. MySQL console 종료

          ```
           mysql> exit;
          ```

       8. 사용할 데이터를 DB에 구축하는 작업을 진행

          제공된 Script file을 이용해서 데이터 구축 진행

          ```
          >> mysql -u android -p lib < _BookTableDump.sql
          ```

   - ##### Transaction

     - 정의 : 작업(일)의 최소단위, 특정한 단위작업의 묶음을 Trasaction으로 설정 가능

     - 예)  은행의 이체업무는 Trasaction으로 설정 가능

               A라는 사람의 계좌에서 B라는 사람의 계좌로 2000원을 이체 

       ```
       1. A라는 사람의 계좌에 돈이 충분한지 (selection)
       2. A라는 사람의 계좌에서 2000원을 인출 (update)
       3. B라는 사람의 계좌의 잔액을 조회 (selection)
       4. 알아낸 잔액 + 2000원 한 금액을 저장 (update)
       ```

     - 그렇다면 왜 Trasaction을 설정할까?

       ```
       DBMS에서 ACID라고 불리는 기능을 제공받기 위해서 설정
       Atomicity(원자성) : Transaction으로 지정된 작업은 모두 성공하거나 
                          하나도 하지않은 상태로 관리
       Consistency(일치성) : Transaction이 종료된 후에 데이터의 일관성이 유지
       Isolation(독립성) : Transactoin이 걸려있는 resource에 대해서 
                          Trasaction이 종료 될때까지 데이터의 접근 제한
       Durability(영속성) : Transaction의 처리결과는 2차 저장소에 안전하게 저장되는 것을 보장
       ```

2. #### Java Servlet으로 Database Access Program 작성

      => 입력 : 책 제목의 keyword
      => 출력 : 책 제목 리스트(JSON)

   1. Eclipse의 설정

      ```
      Encoding 설정
        - Workspace에 대한 text file encoding => UTF-8
      ```

   2. Tomcat WAS를 Eclipse와 연동

      ```
      클라이언트(Web Browser)가 Tomcat을 통해서 서버프로그램을 호출할 때 데이터 전달 가능
      기본적으로 이 데이터 연결 통로가 iso9958-1이라는 영문 encoding으로 되어 있음
      한글이 전달 될 경우 문제가 발생 => UTF-8로 해당 데이터 연결통로의 Encoding을 변경 
      server.xml 파일 (65, 85번째 줄 Connector 속성에 URIEncoding="UTF8" 삽입)수정
      ```

   3. Dynamic Web Project를 생성

      ```
      Project Name : BookSearchForAndroid
      Context Root : bookSearch
        => 프로젝트가 생성
        => MVC pattern으로 파일을 생성
      	   클라이언트의 입력을 받고 출력을 내보내는 작업
      	   Servlet이 담당 => Controller
      	   buisness logic을 담당 => Service
      	   database 관련작업을 담당 => DAO(Data Access Object)
      	   데이터전달에 대한 객체 
      	   => DTO(Data Transfer Object), VO(Value Object), DO(Domain Object), Entity
      	 
      	   Servlet을 생성
      	   => url mapping : /searchTitle
      ```

3. #### Android에서 Java Network 기능 중 

   #### HTTP request호출 기능을 이용하여 Servlet 호출 후 JSON 받기
   ```
   api로 받을 경우 JSON 형태로 호출되므로 parsing하는 과정 필요
   ```

4. ####  JSON형태로 데이터를 받아온 후 Parsing해서 ListView 출력