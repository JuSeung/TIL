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

## cascade = CascadeType.ALL
- persist(orderItemA)
- persist(orderItemB)
- persist(orderItemC)    
- persist(order)
-->  저장이 전파 된다.
- persist(order)

