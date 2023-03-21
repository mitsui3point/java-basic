package ch13;

/**
 * 데몬 쓰레드
 * - 쓰레드는 일반 쓰레드, 데몬 쓰레드 두 가지 종류가 있다.
 * - 일반 쓰레드(non-daemon thread) 의 작업을 돕는 보조적인 역할을 수행.
 * - 일반 쓰레드가 모두 종료되면 자동적으로 종료된다.
 * - Garbage collector, 자동저장, 화면 자동갱신 등(보조작업)에 사용된다.
 * - 무한루프와 조건문을 이용해서 실행 후 대기(일정시간 주기로 무한루프를 계속 돌면서)하다가 특정조건이 만족되면 작업을 수행하고 다시 대기(일정시간 주기로 무한루프를 계속 돌면서)하도록 작성한다.
 * - 관련 메서드
 * : {@link Thread#isDaemon()}; 쓰레드가 데몬 쓰레드인지 확인한다. 데몬 쓰레드이면 true 반환
 * : {@link Thread#setDaemon(boolean)}
 * ; 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경, 매개변수 on 을 true 로 지정하면 데몬 쓰레드가 된다.
 * ; 반드시 {@link Thread#start()} 메서드를 호출하기 전에 실행되어야 한다. 그렇지 않으면 {@link IllegalThreadStateException} 이 발생한다.
 */
public class Ex13_7 implements Runnable {

    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new Ex13_7());//Thread(Runnable r)
        t.setDaemon(true);//이부분이 없으면 종료되지 않는다.
        t.start();

        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            System.out.println(i);

            if (i == 5) {
                autoSave = true;
            }
        }

        System.out.println("프로그램을 종료합니다.");
    }

    @Override
    public void run() {//t.setDaemon(true) 로 데몬 쓰레드가 된다.
        while (true) {//무한루프
            try {
                Thread.sleep(3000);//3초마다
            } catch (InterruptedException e) {}

            //autosave의 값이 true 이면 autoSave()를 호출한다
            if (autoSave) autoSave();
        }
    }

    private void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
