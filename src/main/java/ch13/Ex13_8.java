package ch13;

/**
 * Thread.sleep()
 * - {@link Thread#sleep(long, int)}
 * - 현재 쓰레드를 지정된 시간동안 멈추게 한다
 * - 예외처리를 해야 한다({@link InterruptedException}이 발생하면 깨어남, Time-up 이 되었을때도 깨어남)
 */
public class Ex13_8 {
    public static void main(String[] args) {
        ThreadEx8_1 th1 = new ThreadEx8_1();
        ThreadEx8_2 th2 = new ThreadEx8_2();

        th1.start();
        th2.start();

        delay(2000);
        System.out.println("<<main 종료>>");
    }

    private static void delay(int millis) {
        try {
            //th1.sleep(2000);//th1을 sleep 시키는게 아니라 현재 Thread(main) 를 sleep 시킨다
            Thread.sleep(millis);
        } catch (InterruptedException e) {}
    }

}

class ThreadEx8_1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.print("<<th1 종료>>");
    }//run()
}

class ThreadEx8_2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.print("<<th2 종료>>");
    }//run()
}
