package ch13;

/**
 * 쓰레드의 우선순위(Priority of Thread)
 * - 작업의 중요도에 따라 쓰레드의 우선순위를 다르게 하여 특정 쓰레드가 더 많은 작업시간을 갖게 할 수 있다.
 * - setPriority 로 10단계 세팅을 하지만(우선순위 숫자가 높을수록 높다), Windows, MacOS 의 우선순위가 처리하기 때문에 제대로 반영되지 않는다.
 *   : 기대치만큼 우선순위가 반영될 가능성이 낮다.
 *   : 참고; 윈도우의 경우는 마우스 포인터의 쓰레드 우선순위가 높게 설정되어있다.
 */
public class Ex13_6 {
    public static void main(String[] args) {
        ThreadEx6_1 th1 = new ThreadEx6_1();
        ThreadEx6_2 th2 = new ThreadEx6_2();

        th2.setPriority(9);

        System.out.println("Priority of userThread1(-) = " + th1.getPriority());
        System.out.println("Priority of userThread2(|) = " + th2.getPriority());
        th1.start();
        th2.start();
    }//main

}

class ThreadEx6_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
            for (int x = 0; x < 10000000; x++);//시간지연용 for loop
        }
    }//run()
}

class ThreadEx6_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
            for (int x = 0; x < 10000000; x++);
        }
    }//run()
}
