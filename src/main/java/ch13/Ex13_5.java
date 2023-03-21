package ch13;

import javax.swing.*;

/**
 * 멀티 쓰레드의 I/O 블락킹(blocking)
 * - 각각 다른 쓰레드이기 때문에 Input/Output 이 병렬로 수행되어 막히지 않는다.
 * - 먼저 실행된 오래걸리는 I/O를 th1 쓰레드가 진행하고 있어도, main 쓰레드는 독립적으로 I/O 를 수행하므로, I/O 블락킹으로 막히지 않는다.
 */
public class Ex13_5 {
    public static void main(String[] args) {
        //바로 입력창 노출; IO blocking X
        ThreadEx5_1 th1 = new ThreadEx5_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.printf("입력하신 값은 input = %s 입니다.%n", input);
    }//main

}

class ThreadEx5_1 extends Thread {
    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

