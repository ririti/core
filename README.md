# Spring Study
---

> 김영한님 인프런 강의 - 스프링 핵심원리 기본편
>
### 2021. 11. 24

## 스프링이란?
Spring이란 JAVA 기술들을 더 쉽게 사용할 수 있게 해주는 오픈소스 프레임 워크

###스프링 생태계
- 스프링 프레임워크핵심 기술: 스프링 DI 컨테이너, AOP, 이벤트, 기타 
- 웹 기술: 스프링 MVC, 스프링 WebFlux 
- 데이터 접근 기술: 트랜잭션, JDBC, ORM 지원, XML 지원 
- 기술 통합: 캐시, 이메일, 원격접근, 스케줄링 
- 테스트: 스프링 기반 테스트 지원 
- 최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편리하게 사용

###스프링 부트
- 스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용 
- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성 
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨 
- 손쉬운 빌드 구성을 위한 starter 종속성 제공 
- 라이브러리를 쓸때 하나만 땡기면 starter가 다른 라이브러리까지 땡겨줌 
- 스프링과 3rd parth(외부) 라이브러리 자동 구성 
- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공 • 관례에 의한 간결한 설정 
- 스프링 부트는 스프링이랑 따로 사용 불가 - 스프링프레임워크랑 같이 사용하여 도와준다.


##좋은 객체 지향 설계의 5가지 원칙(SOLID)

5가지 원칙

**SRP** : 단일 책임 원칙
- 한 클래스는 하나의 책임만 가져야 한다.
- Ex) UI변경, 객체의 생성과 사용을 분리

**OCP** : 개방-패쇄 원칙
- 확장에는 열려있으나 변경에는 닫혀 있어야 한다.
- 다형성을 활용
- 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현하면 기존의 코드를 변경할 필요가 없다.

문제점
- 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.

**LSP** : 리스코프 치환 원칙
- 인터페이스가 기능적으로 보장을 하면서 하위 타입의 인스턴스로 바꿀수 있어야한다.
- Ex)자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반, 느리 더라도 앞으로 가야함

**ISP** : 인터페이스 분리 원칙
- 하위 클래스의 인스턴스는 상위형 객체 참조 변수에 대입해 상위 클래스의 인스턴스 역할을 수행하는 데 문제가 없어야 한다.


**DIP** : 의존관계 역전 원칙
- 클라이언트 코드가 구현 클래스에 바라보지말고 인터페이스만 바라봐야한다.
- 클라이언트가 인터페이스에 의존해야지 유연하게 구현체를 변경 가능해진다.
- 구현체에 의존하게 된다면 변경이 어려워짐

정리
- 다형성 만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경됨
- 다형성 만으로는 OCP, DIP를 지킬 수 없다.

## DI와 기능의 확장

- DI는 특별한 기술이라기 보다는 일종의 디자인 패턴 또는 프로그래밍 모델이라는 관점에서 이해하는게 더 자연스럽다.

### DI를 의식하는 설계

- DI는 확장을 위해 필요한 것 (미래의 변화를 예상하고 고민)
- 유연한 확장과 재사용이 가능한 설계를 만드는데 많은 도움이 됨

### DI와 인터페이스

- DI를 적용할 때 가능한 인터페이스 사용이 좋음
- 다형성 (느슨한 연결을 위해)
- 인터페이스 분리 원칙을 통해 클라이언트와 의존 오브젝트 사이의 관계를 명확하게 해줄 수 있기 때문

## 스프링의 IoC

**스프링 빈**

- 스프링이 제어권을 가지고 직접 만들고 관계를 부여하는 오브젝트

**Bean Factory**

- 스프링 빈의 생성과 관계설정 같은 제어를 담당하는 IoC 오브젝트
- 빈 팩토리 보다는 조금 더 확장된 **ApplicationContext**를 주로 사용

### 추가된 어노테이션

- `@Configuration`: 해당 어노테이션을 클래스에 사용하면 해당 클래스에서 1개 이상의 빈을 생성하고 있음을 명시
- `@Bean`: IoC 컨테이너에 빈을 등록하도록 하는 어노테이션 (보통 개발자가 직접 제어가 불가능한 외부 라이브러리 등을 Bean으로 만들때 사용)

###2021. 11. 25

###싱글톤
스프링 없는 순수한 DI컨테이너인 AppConfig는 요청 할 때 마다 객체를 새로 생성
-> 메모리 낭비가 심하다.

싱글톤 패턴
- 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴

문제점
- 싱글톤 구현 코드자체가 많이 들어감
- 의존관계상 클라이언트가 구체 클래스에 의존 -> DIP위반

##싱글톤 컨테이너
###스프링컨테너
- 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다. 
- 지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.

### 싱글톤 방식의 주의점
- 여러 클라이언트가 하나의 같은 객체 인스턴스를 사용하기 때문에 무상태로 설계해야한다. (Stateless)
    - 특정 클라이언트에 의존 x
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 없어야한다
    
##정리
- `@Bean`이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다. -> 덕분에 싱글톤이 보장되는 것이다.

- `@Bean`만 사용해도 스프링 빈으로 등록되지만, 싱글톤을 보장하지 않는다. 
- memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지
않는다. 

- 크게 고민할 것이 없다. 스프링 설정 정보는 항상 `@Configuration` 을 사용하자.