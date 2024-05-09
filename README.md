## 견고한 결제 시스템 구축
- Payment System 설계
- Toss Payments 결제 연동
- Payment Service 데이터 모델링
- 가상의 Checkout 기능 구현
- 결제 승인 기능 구현
- 결제 승인 에러 핸들링 (feat: Retry, Timeout)
- 결제 복구 서비스 (feat: Bulk Head, Parallel Processing)
- Confluent Kafka 연동
- 결제 승인 메시지 발행 (feat: Transactional Outbox Pattern, Apache Kafka)
- 신뢰성 있게 카프카를 사용하는 방법
- Wallet Service 구축
- Wallet Service 동시성 제어 (feat: Optimistic Locking)
- Wallet Service 메시지 처리와 전달 보장 (feat: Kafka Transaction)
- Wallet Service 신뢰성 향상 (feat: Dead Letter Queue)
- Ledger Service 구축 (feat: Double-Entry Ledger, Trigger)
- 결제 완료 기능 구축

<br>

## 재고시스템으로 알아보는 동시성이슈 해결방법
- 재고 감소 로직 작성
- Synchronized 이용해보기
- Pessimistic Lock 활용해보기
- Optimistic Lock 활용해보기
- Named Lock 활용해보기
- Redis 라이브러리 알아보기
- Lettuce를 작성하여 재고 감소 로직 작성하기
- Redisson 을 활용하여 재고 로직 작성하기
- 라이브러리 장단점
- Mysql과 Redis 비교하기

<br>

## 실습으로 배우는 선착순 이벤트 시스템
- 쿠폰 발급 로직 작성
- Redis를 활용하여 문제 해결하기
- Kafka를 활용하여 문제 해결하기
- Producer 사용하기
- Consumer 모듈 추가하기
- Consumer 사용하기
- 발급 가능한 쿠폰 개수를 1인당 1개로 제한하기
- 쿠폰을 발급하다가 에러가 발생하면 어떻게 하나요?

<br>

## 현직 대기업 개발자 푸와 함께하는 진짜 백엔드 시스템 실무!
- JDK, IntelliJ, Visual Studio Code 설치
- Google Cloud Platform(GCP) 계정 생성, 인스턴스 만들기
- Docker Desktop 설치
- CPU를 극단적으로 사용하는 애플리케이션 만들기
- 스트레스 테스트 툴로 성능 측정하기
- Dockerized 애플리케이션 GCP에 배포하기
- jenkins를 이용해서 배포하기
- 무중단 배포를 위한 환경 이해하기
- nginx를 통한 로드밸런싱 구성
- 서버를 늘려서 성능 측정하기
- 쉬는 시간 - 후배들에게 받았던 질문 (1)
- Git, Sourcetree 설치
- Sourcetree로 쉽게 Git 배워보기
- GitHub Webhook과 jenkins로 배포 자동화하기
- 머지할 때 발생하는 충돌(conflict) 해결하기
- 실무에서 유용한 Git 꿀팁
- I/O bound 애플리케이션도 서버를 늘리면 성능을 올릴 수 있을까?
- DB를 이용한 한줄 게시판 만들기
- 페이징과 글번호/글내용으로 검색 기능 만들기
- 스트레스 테스트 툴로 성능 측정하기
- 서버가 죽는 이유와 Message Queue를 도입하여 얻을 수 있는 장점
- RabbitMQ 도입과 글 목록 캐싱
- 스트레스 테스트 - 글 작성 요청은 실패하지 않을까?
- 검색 요청은 여전히 느리다
- 쉬는 시간 - 후배들에게 받았던 질문 (2)
- ES 클러스터 구성
- ES에 데이터 적재 후 검색하기
- 검색 성능 테스트와 샤드, 레플리카
- I/O bound 애플리케이션에서의 기술적 선택 요소
- Kubernetes
- GCP 같은 클라우드 서비스에서 제공해주는 다양한 기능

<br>

## 스프링부트 JUnit 테스트 - 시큐리티를 활용한 Bank 애플리케이션
- Bank 프로젝트 생성
- yml 설정
- 화면설계
- 테이블설계
- User 엔티티 생성
- Account 엔티티 생성
- Transaction 엔티티 생성
- SecurityConfig 기본 설정
- SecurityConfig 직접 테스트
- SecurityConfig Junit 테스트
- 공통DTO 만들기
- 회원가입 서비스 만들기
- 회원가입 서비스 테스트
- 회원가입 서비스 코드 리팩토링
- 회원가입 컨트롤러 만들기
- 회원가입 컨트롤러 유효성검사 AOP 적용
- 회원가입 컨트롤러 정규표현식 실습1
- 회원가입 컨트롤러 정규표현식 실습2
- 회원가입 컨트롤러 정규표현식 DTO 적용
- 회원가입 컨트롤러 테스트
- Jwt 토큰 생성을 위한 세팅
- Jwt 토큰 필터 구현 완료
- Jwt 필터 등록하기
- Jwt 토큰 로그인 실패 로직 처리
- Jwt 인가필터 구현 및 등록완료
- 시큐리티 JWT 코드 리뷰 및 개념잡기
- JwtProcess 테스트
- successfulAuthentication 테스트
- unsuccessfulAuthentication 테스트
- authorization 테스트
- 회원가입 테스트 오류 잡기
- SecurityConfig에 ROLE_ prefix 관련 공식 문서 변경 확인하기
- 계좌등록 서비스 만들기
- 계좌등록 컨트롤러 만들기
- 계좌등록 서비스 테스트
- 계좌등록 컨트롤러 테스트
- 본인계좌목록보기 서비스 만들기
- 본인계좌목록보기 컨트롤러 만들기
- 계좌삭제 서비스 생성 및 테스트
- 계좌삭제 컨트롤러 생성 및 테스트
- @Sql teadown.sql 적용하기
- Jwt토큰 만료시간 버그 잡기
- 계좌입금 서비스 만들기
- 계좌입금 컨트롤러 만들기
- 계좌입금 서비스 테스트
- 서비스 테스트에 관하여 생각해보기
- 계좌입금 컨트롤러 테스트
- 계좌출금 서비스 만들기
- 계좌출금 서비스 테스트
- 계좌출금 컨트롤러 생성 및 테스트
- 계좌이체 서비스 생성
- 계좌이체 서비스 테스트
- 계좌이체 컨트롤러 생성 및 테스트
- Long 타입 테스트
- cors 테스트
- 입출금내역 동적 쿼리 작성
- outer join 하는 이유
- @DataJpaTest 더미데이터 만들기
- @DataJpaTest autoincrement 초기화
- @DataJpaTest 더티체킹
- 동적쿼리 테스트
- fetch join 테스트
- 입출금내역조회 서비스 만들기
- 입출금내역조회 컨트롤러 생성 및 테스트
- 계좌상세보기 서비스, 컨트롤러 생성 및 테스트
- 전체테스트 및 PostMan 확인
