# EhCache 적용 과정
- 버전 2는 중요한 수정은 빼고는 지원을 안한다고 한다.
- 버전 3부터 offheap 을 사용하여 힙메모리를 벗어난 메모리를 제공
  - Java GC에 의해 데이터가 정리되지 않는 공간을 제공한다. 

- ehcache3는 캐싱할 데이터를 3가지 저장소에 옮겨 캐싱할 수 있다.
    - 1. 힙 메모리 : <heap></heap>
    - 2. 힙 메모리의 밖 메모리 <offheap></offheap>
    - 3. 디스크 <disk></disk>
>  2,3번 저장소의 경우 캐시 데이터가 삭제되지 않지만 1번의 경우에는 GC에 의해 삭제가 될 수 있다. 따라서 ehcache3는 여러 종류의 저장소에     캐싱할 수 있는 선택지를 주는 것인데, 힙 메모리는 JVM 내부에 있는 메모리이기 때문에 상관 없지만 2,3번의 경우에는 JVM을 벗어난 저장소이기 때문에
JVM의 힙 메모리가 아닌 곳에 JVM의 데이터를 저장하기 위해서는 직렬화(Serialize)가 필요하다.

## 에러내용
exception [Request processing failed; nested exception is java.lang.IllegalArgumentException: Cannot find cache named
- @EnableCaching 설정 되어져 있는지 확인 한다.
- 라이브러리 정상적으로 import 하고 있는지 본다.
- implementation 'org.ehcache:ehcache:3.10.8'
- implementation 'javax.cache:cache-api:1.1.1'


class file for net.sf.ehcache.CacheManager not found
eHcache 2.x 버전과 3.x대 설정이 섞여서 문제가 됨

## local cache, global cache
 - CacheMagner를 동일하게 사용하기 때문에 Bean에 이름을 지정해줘서 사용한다.
 - Primary로 Cache가 주 가 될지 정해준다.

