package ch12;

import java.util.Date;

public class Ex12_7 {
    public static void main(String[] args) {
        new Child().parentMethod();
    }
}
class Parent {
    void parentMethod() {
        System.out.println("Parent.parentMethod");
    }

}

/**
 * @Override
 * : {@link Override}<br>
 * : 오버라이딩을 올바르게 했는지 컴파일러가 체크하게 한다.<br>
 * : 오버라이딩할 때 메서드이름을 잘못 적는 실수를 하는 경우가 많다.<br>
 * : 오버라이딩 할 때는 메서드 선언부 앞에 @Override 를 붙이자.
 *
 * @Deprecated
 * : {@link Deprecated}<br>
 * : 앞으로 사용하지 않을 것을 권장하는 필드나 메서드에 붙인다.<br>
 * : @Deprecated 사용 예, {@link Date#getDate()}<br>
 * : @Deprecated 가 붙은 대상이 사용된 코드를 컴파일하면 나타나는 메시지<br>
 * javac -encoding utf-8 src/main/java/ch12/Ex12_7.java<br>
 * Note: src\main\java\ch12\Ex12_7.java uses or overrides a deprecated API.<br>
 * Note: Recompile with -Xlint:deprecation for details.<br>
 * <br>
 * javac -encoding utf-8 -Xlint:deprecation src/main/java/ch12/Ex12_7.java<br>
 * src\main\java\ch12\Ex12_7.java:7: warning: [deprecation] parentMethod() in Child has been deprecated<br>
 *         new Child().parentMethod();<br>
 *                    ^<br>
 * 1 warning<br>
 */
class Child extends Parent {
    @Override
    @Deprecated
    public void parentMethod() {
        System.out.println("Child.parentMethod");
    }
}
