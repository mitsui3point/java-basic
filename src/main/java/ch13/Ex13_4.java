package ch13;

import javax.swing.*;

/**
 * 싱글 쓰레드의 I/O 블락킹(blocking)
 * - Input/Output 을 막음(입출력시 작업중단)
 * - main 쓰레드 한곳에서 진행하므로, 먼저 실행된 오래걸리는 I/O를 main 쓰레드가 끝내지 못하고 있으면, 다음 I/O는 I/O 블락킹에 의해 수행되지 못한다.
 */
public class Ex13_4 {
    public static void main(String[] args) {
        //10초 카운트 이후 입력창 노출; IO blocking O
        for (int i = 10; i > 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.printf("입력하신 값은 input = %s 입니다.%n", input);
    }//main

}