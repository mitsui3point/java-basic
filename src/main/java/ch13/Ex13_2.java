package ch13;

/**
 * - main 쓰레드
 * : main 메서드 의 코드를 수행하는 쓰레드
 *
 * - 쓰레드는 '사용자 쓰레드' 와 '데몬 쓰레드' 두 종류가 있다.
 * : 사용자 쓰레드 -> main 쓰레드, new Thread(new Runnable()), extend Thread..
 * : 데몬 쓰레드 -> '사용자 쓰레드' 역할을 '보조해주는 쓰레드'
 * : 프로그램은 '사용자 쓰레드'가 하나도 없을 때 종료된다.
 *      -> '보조 쓰레드'가 종료되기 전에도 '사용자 쓰레드'가 종료되면 프로그램이 종료될 수 있다.
 *
 * - ex call stack
 * main thread(사용자 쓰레드) start()
 * |       |
 * |       |
 * |-------|
 * | main  |
 * |-------|
 * ↓
 * extend Thread (사용자 쓰레드) th1 thread start; th1.start()
 * |           |
 * |           |
 * |-----------|
 * | th1.run() |
 * |-----------|
 * ↓
 * extend Thread (사용자 쓰레드) th2 thread start; th2.start()
 * |           |
 * |           |
 * |-----------|
 * | th2.run() |
 * |-----------|
 * ↓
 * main thread end
 * ↓
 * th1.run() & th2.run()
 */
public class Ex13_2 {
    public static void main(String[] args) {
        ThreadEx2_1 userThread1 = new ThreadEx2_1();
        ThreadEx2_2 userThread2 = new ThreadEx2_2();

        userThread1.start();
        userThread2.start();
        long startTime = System.currentTimeMillis();

        System.out.print("소요시간:" + (System.currentTimeMillis() - startTime));//userThread1, userThread2 실행보다 main thread 의 로직이 먼저 실행된다.
    }//main
}

class ThreadEx2_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
    }//run()
}

class ThreadEx2_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
    }//run()
}
