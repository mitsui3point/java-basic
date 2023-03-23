package ch13;


import ch13.stateprint.StatePrintThread;

import javax.swing.*;

/**
 * new Thread().interrupt()
 * - 대기상태(WAITING) 인 쓰레드를 실행대기 상태(RUNNABLE)로 만든다.
 * - 메서드
 * : {@link Thread#interrupt()}         | 쓰레드의 interrupted 상태를 false 에서 true 로 변경
 * : {@link Thread#isInterrupted()}     | 쓰레드의 interrupted 상태를 반환
 * : {@link Thread#interrupted()}       | 현재 쓰레드의 interrupted 상태를 알려주고, false 로 초기화
 */
public class Ex13_9 {
    public static void main(String[] args) {
        ThreadEx9_1 th1 = new ThreadEx9_1();
        th1.setName("th1");
        StatePrintThread th2 = new StatePrintThread(th1);//state 출력용 thread
        th2.setName("th2");
        th2.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은" + input + "입니다.");
        th1.interrupt();

        System.out.println("[" + th1.getName() + "]" + " th1.isInterrupted():" + th1.isInterrupted());//isInterrupted() 는 변수에담긴쓰레드.isInterrrupted() 가능
//        System.out.println("Thread.isInterrupted():" + Thread.interrupted());//interrupted() 는 현재쓰레드.interrupted() 상태만 호출가능(th1.interrupted() 를 실행하는것은 가능하나 결과값은 Thread.interrupted() 를 반환한다)
//        System.out.println("re Thread.isInterrupted():" + Thread.interrupted());
    }

}

class ThreadEx9_1 extends Thread {
    @Override
    public void run() {
        int i = 10;

        while (i != 0 && !isInterrupted()) {
            System.out.println("[" + this.getName() + "] " + i--);
            System.out.println("[" + this.getName() + "]" + " isInterrupted() = " + isInterrupted());
            if (isSleepInterrupted(2500)) break;//sleep 으로 TIMED_WAITING state
//            for (long x = 0; x < 2_500_000_000L; x++);//시간지연
        }

//        System.out.println("[" + this.getName() + "]" + " ThreadEx9_1.this.isInterrupted():" + this.isInterrupted());//true(단지 interrupted 상태만 항상 return)
//        System.out.println("[" + Thread.currentThread().getName() + "]" + " Thread.isInterrupted():" + Thread.interrupted());//true(return interrupted, set interrupted = false) -> false
//        System.out.println("[" + Thread.currentThread().getName() + "]" + " re Thread.isInterrupted():" + Thread.interrupted());//false(다시 호출시 return interrupted, set interrupted = false) -> false

        System.out.println("[" + Thread.currentThread().getName() + "]" + " 카운트가 종료되었습니다.");
    }

    private boolean isSleepInterrupted(int millis) {
        try {
            System.out.println("[" + this.getName() + "]" + " prevSleep");
            Thread.sleep(millis);
            System.out.println("[" + this.getName() + "]" + " afterSleep");
        } catch (InterruptedException e) {
            System.out.println("[" + this.getName() + "]" + " isSleepInterrupted InterruptedException this.isInterrupted() = " + this.isInterrupted());
            System.out.println("[" + this.getName() + "]" + " expected InterruptedException e = " + e);
            return true;
        }
        return false;
    }

}

