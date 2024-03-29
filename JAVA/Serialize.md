# Serialize(직렬화) / Deserialize(역직렬화)

# 직렬화(Serialize)
> 자바 시스템 내부에서 사용되는 Object 또는 Data를 외부의 자바 시스템에서도 사용할 수 있도록 Byte 형태로 변환 하는 기술
JVM(Java Virtual Machine 이하 JVM)의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술


# 역직렬화(Deserialize)
> byte로 변환된 Data를 원래대로 Object나 Data로 변환하는 기술을 역직렬화(Deserialize)라고 부릅니다.
직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태.

## 왜 사용 하는가?
- 자바 시스템간의 데이터 교환을 위해 존재 한다.
- 자바 시스템간 통신의 최적화 되어져 있다.
- CSV, JSON 형태의 타입이 있기 때문에 때의 맞춰 쓰면된다.

# 왜 private static final long serialVersionUID = 1L; 선언하는가
 - 직렬화 데이터에 대한 버저닝이라고 생각하면 될것 같다.
 - "조금이라도 역직렬화 대상 클래스 구조가 바뀌면 에러 발생해야 된다." 정도의 민감한 시스템이 아닌 이상은 클래스를 변경할 때에
직접 serialVersionUID 값을 관리해주어야 클래스 변경 시 혼란을 줄일 수 있습니다.
 - 속성이 추가 되는 부분이 이런 에러는 막을 수 있다.
 - 직렬화 되었던 객체의 타입이 Stirng -> StringBuilder, int -> long 으로 변경되면 에러가 발생된다.
 - 자바 직렬화는 타입에 대해 엄격하게 관리 하고 있다. 

## 언제 사용 되나
- 서블릿 세션
- 캐시 (Ehcache, Redis)
    메모리, 외부 저장소, 파일 등을 저장소를 이용해서 데이터 객체를 저장한 후 동일한 요청이 오면 DB를 다시 요청하는 것이 아니라 저장된 객체를 찾아서 응답하게 하는 형태를 보통 캐시를 사용한다고 합니다.
- 자바 RMI(Remote Method Invocation)
    최근에는 많이 사용되지 않지만 자바 직렬화를 설명할 때는 빠지지 않고 이야기되는 기술이기 때문에 언급만 하고 넘어가려고 합니다.
    자바 RMI를 간단하게 이야기하자면 원격 시스템 간의 메시지 교환을 위해서 사용하는 자바에서 지원하는 기술입니다.
    보통은 원격의 시스템과의 통신을 위해서 IP와 포트를 이용해서 소켓통신을 해야 하지만 RMI는 그 부분을 추상화하여 원격에 있는 시스템의 메서드를 로컬 시스템의 메서드인 것처럼 호출할 수 있습니다.
    원격의 시스템의 메서드를 호출 시에 전달하는 메시지(보통 객체)를 자동으로 직렬화 시켜 사용됩니다.
    그리고 전달받은 원격 시스템에서는 메시지를 역직렬화를 통해 변환하여 사용됩니다.




- 외부 저장소로 저장되는 데이터는 짧은 만료시간의 데이터를 제외하고 자바 직렬화를 사용을 지양합니다.
- 역직렬화시 반드시 예외가 생긴다는 것을 생각하고 개발합니다.
- 자주 변경되는 비즈니스적인 데이터를 자바 직렬화을 사용하지 않습니다.
- 긴 만료 시간을 가지는 데이터는 JSON 등 다른 포맷을 사용하여 저장합니다.
