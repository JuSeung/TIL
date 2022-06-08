## lombk
- Prefrences -> Annotaion Processors -> Enable processing 체크하기

## 라이브러리 의존관계 보기
- 해당 프로젝트 가서 
- ./gradlew dependencies —configuration compileClasspath


## 쿼리 파라미터 로그 남기기
- org.hibernate.type: trace
- 외부 라이브러리
  - https://github.com/gavlyukovskiy/spring-boot-data-source-decorator 

## 엔티티 설계 시 @Setter
- Setter가 모두 열려있다. 
- 변경 포인트가 너무 많아서, 유지보수가 어렵다. 
- 나중에 리펙토링으로 Setter 제거

## @ManyToMany 실무에서 사용하지 않아야 한다.
- 중간테이블에 실무에서는 등록자, 등록일이 들어가야하는데 이 어노테이션 사용시 외래키만 중간테이블에 외래키만 들어감
  실무에서는 중간 테이블에도 등록자나 등록일시 다른 칼럼이 필요 하기 때문이다.


## 모든 연관관계는 지연로딩으로 설정(외우는수준)
### 즉시로딩(EAGER)
- 예측이 어렵다.
- 어떤 SQL이 실행될지 추적하기 어렵다.
- JPQL을 실행할때 N+1 문제가 자주 발생한다.

### 지연로딩(LAZY)
- 실무에 모든 연관관계는 지연로딩으로 설정한다.
- 연관된 엔티티를 함께 DB조회해야 하면 fetch join 또는 엔티티 그래프 기능을 사용한다.
- OneToOne, ManyToOne은 LAZY로한다.


## 컬렉션은 필드에서 초기화 하자.
- 컬렉션은 필드에서 바로 초기화 하는 것이 안전하다.
- null 문제에 안전하다.

## 테이블, 컬럼명 생성 전략
스프링 부트에서 하이버네이트 기본 매핑 전략을 변경해서 실제 테이블 필드명은 다름

하이버네이트 기존 구현: 엔티티의 필드명을 그대로 테이블의 컬럼명으로 사용 ( SpringPhysicalNamingStrategy )
스프링 부트 신규 설정 (엔티티(필드) 테이블(컬럼))
1. 카멜 케이스 언더스코어(memberPoint member_point) 
2. .(점) _(언더스코어)
3. 대문자 소문자

4. 논리명 생성: 명시적으로 컬럼, 테이블명을 직접 적지 않으면 ImplicitNamingStrategy 사용
spring.jpa.hibernate.naming.implicit-strategy : 테이블이나, 컬럼명을 명시하지 않을 때 논리명
적용,
5. 물리명 적용:
spring.jpa.hibernate.naming.physical-strategy : 모든 논리명에 적용됨, 실제 테이블에 적용 (username usernm 등으로 회사 룰로 바꿀 수 있음)

## cascade = CascadeType.ALL 설정 시
- persist(orderItemA)
- persist(orderItemB)
- persist(orderItemC)    
- persist(order)
-->  저장이 전파 된다.
- persist(order)

## 도메인 모델 패턴(http://martinfowler.com/eaaCatalog/ domainModel.html)이라 한다. 
 - 주문 서비스의 주문과 주문 취소 메서드를 보면 비즈니스 로직 대부분이 엔티티에 있다.
 - 서비스 계층 은 단순히 엔티티에 필요한 요청을 위임하는 역할을 한다. 
 - 이처럼 엔티티가 비즈니스 로직을 가지고 객체 지 향의 특성을 적극 활용하는 것
 - 엔티티에 대한 단위테스트가 가능하다.
## 트랜잭션 스크립트 패턴(http://martinfowler.com/eaaCatalog/ transactionScript.html)이라 한다.
 - 엔티티에는 비즈니스 로직이 거의 없고 서비스 계층에서 대부분 의 비즈니스 로직을 처리하는 것

-> 어떤 것이 유지보수 할때 유지 할때 고민해봐야 한다.

## 테스트
- 좋은 테스트는 DB, 스프링 안엮고 테스트하는게 좋은 테스트 이다...

## API 개발 시 데이터 반환 할때는 엔티티를 반환 해서는 안된다.
- API 스펙이여서 불완전하게 스펙을 제공해서는 안된다.


## 폼 객체 vs 엔티티 직접 사용
> 요구사항이 정말 단순할 때는 폼 객체( MemberForm ) 없이 엔티티( Member )를 직접 등록과 수정 화면 에서 사용해도 된다.
> 하지만 화면 요구사항이 복잡해지기 시작하면, 엔티티에 화면을 처리하기 위한 기능이 점점 증가한다. 
> 결과적으로 엔티티는 점점 화면에 종속적으로 변하고, 이렇게 화면 기능 때문에 지저분해진 엔티티는 결국 유지보수하기 어려워진다.
> 실무에서 엔티티는 핵심 비즈니스 로직만 가지고 있고, 화면을 위한 로직은 없어야 한다. 화면이나 API에 맞 는 폼 객체나 DTO를 사용하자. 
> 그래서 화면이나 API 요구사항을 이것들로 처리하고, 엔티티는 최대한 순수 하게 유지하자.


# 변경 감지와 병합(merge)
## 준영속 엔티티?
- 영속성 컨텍스트가 더는 관리하지 않는 엔티티를 말한다.
- 이미 DB에 한번 저장된 식별자가 있는 엔티티
> 문제: JPA 관리 하는 엔티티가 아니기 때문에 데이터를 변경해도 변경이 일어나지 않는다.

## 변경 감지 기능
- 레퍼지토리 통해서 데이터를 불러와서 해당 엔티티를 변경 하면 변경감지 기능이 사용된다.

## 병합사용
- EntityManager.merge() 함수 사용

### 병합 주의
- 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 병합을 사용하면 모든 속성이 변경된다. 
- 병합시 값이 없으면 null로 업데이트 할 위험도 있다. (병합은 모든 필드를 교체한다.)

> 실무에서는 사용시 변경감지로 명확하게 변경된 부분만 업데이트 한다.
> set함수 보다는 의미있는 메서드 명으로 해서 처리한다.