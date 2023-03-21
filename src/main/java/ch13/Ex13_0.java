package ch13;

public class Ex13_0 {
    /**
     * process & thread
     * - 프로세스
     * : 실행중인 프로그램, 자원(resource) 과 쓰레드로 구성
     *
     * - 쓰레드
     * : 프로세스 내에서 실제 작업을 수행.
     * : 모든 프로세스는 최소한 하나의 쓰레드를 갖고 있다.
     * : 싱글 쓰레드()
     *      process + thread
     * : 멀티 쓰레드
     *      process + thread1 + thread2 + ...
     *
     * - process(공장), thread(일꾼)
     * : 2프로세스 1쓰레드 VS 1프로세스 2쓰레드
     *      2프로세스 1쓰레드(생성비용 많이 듦, 같은효율)
     *      VS
     *      1프로세스 2쓰레드(생성비용 적게 듦, 같은효율)
     * : 하나의 새로운 프로세스를 생성하는 것보다
     *   하나의 새로운 쓰레드를 생성하는 것이 더 적은 비용이 든다.
     * : ex. 1999년 웹 서버: CGI(싱글쓰레드 멀티프로세스) VS Java Servlet(멀티쓰레드 싱글프로세스)
     *      Java Servlet 의 웹 서버가 효율적인 방법으로 같은 요청을 처리하였기 때문에 Java 가 각광받게되었다.(안드로이드 때문이 아니다.)
     *
     * - 멀티쓰레드의 장단점
     * : 대부분의 프로그램이 멀티쓰레드로 작성되어있다. 그러나, 멀티쓰레드 프로그래밍이 장점만 있는 것은 아니다.
     * : 장점
     *      1. 시스템 자원을 보다 효율적으로 사용할 수 있다.
     *      2. 사용자에 대한 응답성(responseness)이 향상된다.(ex. 채팅하면서 파일전송 동시에 가능)
     *      3. 작업이 분리되어 코드가 간결해진다.(ex. 채팅과 파일전송의 코드를 기능적으로 분리가 가능)
     *      ==> "여러모로 좋다"
     * : 단점
     *      1. 동기화(synchronization)에 주의해야 한다.
     *      2. 교착상태(dead-lock)가 발생하지 않도록 주의해야 한다. (ex. threadA (망치를 들고있고 필요한것 톱) threadB(톱을 들고있고 필요한것 망치) => 서로 필요한것을 먼저 달라고 요구하게 되는 상황)
     *      3. 각 쓰레드가 효율적으로 고르게 실행될 수 있게 해야 한다. (ex. threadA 는 실행기회 많음, threadB 는 상대적으로 실행기회 적어짐 => 기아)
     *      ==> "프로그래밍 할 때 고려해야 할 사항들이 많다."
     */
}
