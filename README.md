# Spring Study
---

> 김영한님 인프런 강의 - 스프링 핵심원리 기본편
>
### 2021. 11. 24

## 스프링이란?
Spring이란 JAVA 기술들을 더 쉽게 사용할 수 있게 해주는 오픈소스 프레임 워크

### 스프링 생태계
- 스프링 프레임워크핵심 기술: 스프링 DI 컨테이너, AOP, 이벤트, 기타 
- 웹 기술: 스프링 MVC, 스프링 WebFlux 
- 데이터 접근 기술: 트랜잭션, JDBC, ORM 지원, XML 지원 
- 기술 통합: 캐시, 이메일, 원격접근, 스케줄링 
- 테스트: 스프링 기반 테스트 지원 
- 최근에는 스프링 부트를 통해서 스프링 프레임워크의 기술들을 편리하게 사용

### 스프링 부트
- 스프링을 편리하게 사용할 수 있도록 지원, 최근에는 기본으로 사용 
- 단독으로 실행할 수 있는 스프링 애플리케이션을 쉽게 생성 
- Tomcat 같은 웹 서버를 내장해서 별도의 웹 서버를 설치하지 않아도 됨 
- 손쉬운 빌드 구성을 위한 starter 종속성 제공 
- 라이브러리를 쓸때 하나만 땡기면 starter가 다른 라이브러리까지 땡겨줌 
- 스프링과 3rd parth(외부) 라이브러리 자동 구성 
- 메트릭, 상태 확인, 외부 구성 같은 프로덕션 준비 기능 제공 • 관례에 의한 간결한 설정 
- 스프링 부트는 스프링이랑 따로 사용 불가 - 스프링프레임워크랑 같이 사용하여 도와준다.


## 좋은 객체 지향 설계의 5가지 원칙(SOLID)

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

**정리**
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

### 2021. 11. 25

### 싱글톤
스프링 없는 순수한 DI컨테이너인 AppConfig는 요청 할 때 마다 객체를 새로 생성
-> 메모리 낭비가 심하다.

싱글톤 패턴
- 클래스의 인스턴스가 1개만 생성되는 것을 보장하는 디자인 패턴

문제점
- 싱글톤 구현 코드자체가 많이 들어감
- 의존관계상 클라이언트가 구체 클래스에 의존 -> DIP위반

## 싱글톤 컨테이너
### 스프링컨테너
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

### 2021. 11. 26

## 컨포넌트 스캔

- @ComponentScan은 @Component 가 붙은 모든 클래스를 '스프링 빈'으로 등록한다.

- @Autowired : 의존관계 자동 주입 -
  - 생성자에 @Autowired 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입
  - 탐색 위치와 기본스캔대상 지정이 가능

### 컴포넌트 스캔 기본 대상

- @Component : 컴포넌트 스캔에서 사용 
- @Controlller : 스프링 MVC 컨트롤러에서 사용 
- @Service : 스프링 비즈니스 로직에서 사용
- @Repository : 스프링 데이터 접근 계층에서 사용 
- @Configuration : 스프링 설정 정보에서 사용

참고 : 애노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 아니고, 스프링이 지원하는 기능

### 필터

- includeFilters : 컴포넌트 스캔 대상을 추가로 지정한다.
- excludeFilters : 컴포넌트 스캔에서 제외할 대상을 지정한다.

### 중복 등록과 충돌
컴포넌트 스캔에서 같은 빈 이름을 등록하면 어떻게 될까? 다음 두가지 상황이 있다.
1. 자동빈등록 vs 자동빈등록
- 컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 스프링은 오류를 발생시킨다.
- ConflictingBeanDefinitionException 예외 발생

2. 수동빈등록 vs 자동빈등록
- 수동 빈이 등록이 우선
- 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 바꾸었디.

## 다양한 의존관계 주입 방법

1. 생성자 주입
2. 수정자 주입(setter주입)
3. 필드 주입
4. 일반 메서드 주입

### 생성자 주입
- 생성자를 통해서 의존 관계를 주입 받는 방법
- 특징
  - 생성자 호출시점에 딱 1번만 호출이 보장된다.
  - 불변 필수 의존관계에 사용

! 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다. 물론 스프링 빈에만 해당

**수정자 주입**
- Setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법
- 특징
- 선택 변경 가능성이 있는 의존관계에 사용
- 수정자 메서드 방식을 사용하는 방법

**필드 주입**
- 그대로 필드에 주입하는 방법
- 특징
  - 코드가 간결하지만 외부에서 변경이 불가능해서 테스트하기 힘든 단점이 있다
  - DI 프레임워크가 없으면 아무것도 못한다.
  - 안티 디자인패턴
  - 테스트케이스에서는 써도 괜찮다.
  - 사용하지 않는게 좋다.

**일반 메서드 주입**
- 일반 메서드를 통해서 주입 받을 수 있다.
- 특징
  - 한번에 여러 필드를 주입 받을 수 있다
  - 일반적으로 잘 사용하지 않음

### 2021. 11. 27

### 옵션 처리

1. `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
2. `@Nullable` : 자동 주입할 대상이 없으면 null이 입력된다.
3. `Optional<>` : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다. 

### 생성자 주입을 권장하는 이유

1. 불변
   - 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
2. 누락
3. final 키워드 사용가능
   - 생성자에서 혹시라도 값이 설정되지 않는 오류를 컴파일 시점에 막아준다. 

### 정리
1. 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법
2. 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용
3. 최대한 생성자 주입을 사용, 필드 주입은 사용하지 않는게 좋다.

### 롬복과 최신 트렌트

@RequiredArgsConstructor 기능을 사용하면 final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다. 
