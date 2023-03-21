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

    /**
     * 쓰레드 그룹
     * - 서로 관련된 쓰레드를 그룹으로 묶어서 다루기 위한 것
     * - 모든 쓰레드는 반드시 하나의 쓰레드 그룹에 포함되어 있어야 한다.
     * - new Thread(ThreadGroup group, Runnable target, ...) 생성자에서 쓰레드 그룹을 지장허지 않고 생성한 쓰레드는 'main 쓰레드 그룹'(main 쓰레드도 포함된 그룹) 에 속한다
     * - 자신을 생성한 쓰레드(부모 쓰레드)의 그룹과 우선순위(priority, 5)를 상속받는다.
     * <p>
     * - 관련 메서드
     * : ThreadGroup getThreadGroup() //쓰레드 자신이 속한 쓰레드 그룹을 반환한다.
     * : void uncaughtException(Thread t, Throwable e) //처리되지 않은 예외에 의해 쓰레드 그룹의 쓰레드가 실행이 종료되었을 때, JVM 에 의해 이 메서드가 자동적으로 호출된다.
     *      => 처리되지 않은 예외 : JVM 이 처리한다.
     *      => 우리가 바꿀수도 있다 다른동작을 하도록 처리할수 있다.
     *      => ThreadGroup 에 있는 Thread 가 예외처리를 하지 못하고 죽었을 때, 수행될 동작을 우리가 Overriding 해줄 수 있다.
     *
     * - 쓰레드 그룹 메서드
     * {@link ThreadGroup#}
     * : 그룹으로 Thread 를 제어하거나 정보를 조회할 수 있다.
     */

    /**
     * 쓰레드의 상태
     * {@link Thread.State}   | 설명
     * 1.NEW                  | 쓰레드가 생성되고 아직 start() 가 호출되지 않은 상태
     * 2.RUNNABLE             | 실행 중 또는 실행 가능한 상태
     * 3.BLOCKED              | 동기화블럭에 의해서 일시정지된 상태(lock 이 풀릴 때 까지는 기다리는 상태)
     * 4.WAITING,             | 쓰레드의 작업이 종료되지는 않았지만 실행가능하지 않은(unrunnable) 일시정지상태.
     *   TIMED_WAITING        | 일시정지시간이 지정된 경우는 TIMED_WAITING
     * 5.TERMINATED           | 쓰레드의 작업이 종료된 상태
     *
     *             <==   <==   <==      쓰레드 쉼터              <==   <==   <==   <==
     *             ↓             | ↑        thread-z            ↓ |                  ↑
     * time-out,   ↓             | thread-f                       |                  ↑  suspend(); 일시정지,
     * resume(),   ↓             |                                |                  ↑  sleep(); 잠자기,
     * notify(),   ↓             |                   thread-e     |                  ↑  wait(); 기다리기,
     * interrupt() ↓             |--------------------------------|                  ↑  join(),
     *             ↓                  일시정지(WAITING, BLOCKED),                     ↑  I/O block; 입출력 대기
     *             ↓    <==   <==   <==   <==   <==   <==   <==   <==   <==   <==    ↑
     *             ↓  ↓   ===========줄서기================================        ↑  ↑    stop() or 작업완료
     * thread        ==>  thread-1  thread-A  thread-B  thread-2  thread-3  ==> thread-0        ==>       thread-0
     * 생성        start()                                                       실행                      소멸
     * (NEW)                                                                                          (TERMINATED)
     *                    ================================================
     *                                    실행 대기(RUNNABLE)
     */

    /**
     * 쓰레드의 실행 제어
     * - 쓰레드의 실행을 제어할 수 있는 메서드가 제공된다
     * - 이들을 활용해서 보다 효율적인 프로그램을 작성할 수 있다.
     *
     * {@link Thread#sleep(long)}       | 지정된 시간(천분의 일 초 단위) 동안 쓰레드를 일시정지(WAITING) 시킨다.
     *                                  | 지정한 시간이 지나고 나면, 자동적으로 다시 실행대기상태(RUNNABLE)가 된다.(잠들게)
     *                                  | (static); 내가 나를 재움
     * {@link Thread#join()}            | 지정된 시간동안 쓰레드가 실행되도록 한다.
     *                                  | 지정된 시간이 지나거나 작업이 종료되면 join()을 호출한 쓰레드로 다시 돌아와 실행을 계속 한다.(다른 쓰레드 기다리기)
     * {@link Thread#interrupt()}       | sleep()이나 join() 에 의해 일시정지상태인 쓰레드를 깨워서 실행대기상태(RUNNABLE)로 만든다.(깨우는것)
     *                                  | 해당 쓰레드에서는 {@link InterruptedException} 이 발생함으로써 일시정지 상태를 벗어나게 된다.
     * {@link Thread#stop()}            | 쓰레드를 즉시 종료시킨다. (종료) deprecated..
     * {@link Thread#suspend()}         | 쓰레드를 일시 정지시킨다. resume()을 호출하면 다시 실행대기상태(RUNNABLE)가 된다.(일시정지) deprecated..
     * {@link Thread#resume()}          | suspend() 에 의해 일시정지상태에 잇는 쓰레드를 실행대기상태(RUNNABLE) 로 만든다.(재개) deprecated..
     * {@link Thread#yield()}           | 실행중에 자신에게 주어진 실행시간을 다른 쓰레드에게 양보(yield) 하고 자신은 실행대기상태(RUNNABLE) 가 된다.
     *                                  | (static); 내가 나를 다른 쓰레드에 양보
     * static 이 아닌 메서드들은 남의 쓰레드 조작
     * static 인 메서드들은 자기자신의 쓰레드만 조작(자기 자신에게만 호출 가능)
     */
}
