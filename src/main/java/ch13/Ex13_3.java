package ch13;

import static ch13.Ex13_3.startTime;


/**
 * 싱글 쓰레드, 멀티 쓰레드
 *
 * single thread
 * : 반드시 순차적으로 동작한다.
 *
 * multi thread(userThread1, userThread2)
 * : t1, t2 동작 순서는 보장할 수 없다(먼저 start 했다고 먼저 실행 종료되는것 아님.).
 * : thread 실행 순서는 OS 의 scheduler 가 결정한다.
 * : userThread1 userThread2 사이 옮겨갈때 마다 context switching 이 발생하면서 약간의 시간지연(overhead)이 발생한다
 *      => https://mycareerwise.com/storage/editor/images/image(1349).png
 */
public class Ex13_3 {
    static long startTime = 0L;
    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
//        singleThread();
        multiThread();
    }

    private static void singleThread() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println("소요시간1: "+ (System.currentTimeMillis() - startTime));
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println("소요시간2: "+ (System.currentTimeMillis() - startTime));
    }

    private static void multiThread() {
        ThreadEx3_1 userThread1 = new ThreadEx3_1();
        ThreadEx3_2 userThread2 = new ThreadEx3_2();
        userThread1.start();
        userThread2.start();
    }
}

class ThreadEx3_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println("소요시간1: " + (System.currentTimeMillis() - startTime));
    }
}

class ThreadEx3_2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println("소요시간2: " + (System.currentTimeMillis() - startTime));
    }
}
