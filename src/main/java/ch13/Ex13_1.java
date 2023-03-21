package ch13;

/**
 * JVM 이 OS에 독립적이라고는 하나,
 * Thread 처럼 종속적으로 동작하는 몇가지가 존재한다.
 */
public class Ex13_1 {
    public static void main(String[] args) {
        multiThread();
        //singleThread();
    }

    /**
     * single thread(main thread) 에서만 돌리는 로직
     * : 반드시 순차적으로 동작한다.
     */
    private static void singleThread() {
        for (int i = 0; i < 500; i++) {
            System.out.print(0);
        }
        for (int i = 0; i < 500; i++) {
            System.out.print(1);
        }
    }

    /**
     * thread.start()
     * : thread 실행
     * : 실행 가능상태로 만든다.
     * : t1, t2 동작 순서는 보장할 수 없다(먼저 start 했다고 먼저 실행 종료되는것 아님.).
     * : thread 실행 순서는 OS 의 scheduler 가 결정한다.
     */
    private static void multiThread() {
        //1. Thread 클래스를 상속해서 쓰레드를 구현
        ThreadEx1_1 t1 = new ThreadEx1_1();

        //2. Runnable 인터페이스 메소드 run()을 구현한 뒤 Thread 구현
        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r);

        t1.start();//Thread t1 실행
        t2.start();//Thread t2 실행
    }
}

/**
 * 1. Thread 클래스를 상속해서 쓰레드를 구현
 */
class ThreadEx1_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            //조상인 Thread의 getName() 호출
            //System.out.println(this.getName());
            System.out.print(0);
        }
    }
}

/**
 * 2. Runnable 인터페이스 메소드 run()을 구현한 뒤 Thread 구현
 */
class ThreadEx1_2 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            //Thread.currentThread() - 현재 실행중인 Thread 를 반환한다.
            //String threadName = Thread.currentThread().getName();
            //System.out.println(threadName);
            System.out.print(1);
        }
    }
}

/**
 * thread.start()
 * - 새로운 call stack 을 생성하고, run() 메서드를 적재하는 역할.
 * - call stack
 * |       |          |       | |       |          |       | |       |          |       | |       |
 * |-------|          |-------| |       |          |-------| |       |          |       | |       |
 * | start |    =>    | start | |       |    =>    |start-X| |       |    =>    |       | |       |
 * |-------|          |-------| |-------|          |-------| |-------|          |-------| |-------|
 * | main  |          | main  | |       |          | main  | |  run  |          | main  | |  run  |
 * |-------|          |-------| |-------|          |-------| |-------|          |-------| |-------|
 * => main thread 에 있는 start() 메서드 실행
 * => 새로운 call stack 호출
 * => 새로운 call stack 에 run() 메서드 적재, start() 메서드 main 쓰레드에서 실행 종료
 * => main thread 와 독립된 새로운 thread 가 병렬로 작업 수행
 *
 * thread.run()
 * - 기존에 정의되어있거나, 상속 혹은 구현체로 구현된 run() 메서드를 단지 실행하는 역할.
 *
 * 그래서 새로운 thread 를 생성 후 실행하려면 start() 메서드를 사용해야 한다.
 */