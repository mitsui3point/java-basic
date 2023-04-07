package ch12;

import java.lang.annotation.Annotation;
import java.lang.annotation.Native;
import java.util.Date;

/**
 * {@link Annotation}애노테이션이란?
 * - 주석처럼 프로그래밍 언어에 영향을 미치지 않으며, 유용한 정보를 제공
 * - 예시(JavaDoc)
 *      : public class MyClass v1 -> 소스코드 설명 문서 v1 -> public class MyClass v1.1 -> 소스코드 설명 문서 v1(수정 누락, 불일치 발생)
 *      : 해결 방안; 메서드 및 class 위에 JavaDoc 작성
 * - 예시(Annotation)
 *      : public class MyClass v1 -> xml 설정 v1 -> public class MyClass v1.1 -> xml 설정 v1(수정 누락, 불일치 발생)
 *      : 해결 방안; 메서드 및 class 위에 메타정보를 담은 Annotation 을 선언
 * - 특정 프로그램에 메타정보를 제공
 *
 * 표준 애노테이션
 * - Java 에서 제공하는 애노테이션
 *      : {@link Override}, {@link Deprecated}, {@link SuppressWarnings}, {@link SafeVarargs}, {@link FunctionalInterface}, {@link Native}
 */
public class Ex12_7 {
    /**
     * @SuppressWarnings
     * : {@link SuppressWarnings}<br>
     * : 컴파일시 경고메시지가 나타나지 않게 억제한다<br>
     * : 파라미터에 억제하고자 하는 경고의 종류를 문자열로 지정<br>
     * : @SuppressWarnings({"deprecation","unchecked","varargs"})<br>
     * $ javac -encoding utf-8 src/main/java/ch12/Ex12_7.java<br>
     * Note: src\main\java\ch12\Ex12_7.java uses or overrides a deprecated API.<br>
     * Note: Recompile with -Xlint:deprecation for details.<br>
     */
    @SuppressWarnings("deprecation")
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
 * $ javac -encoding utf-8 src/main/java/ch12/Ex12_7.java<br>
 * Note: src\main\java\ch12\Ex12_7.java uses or overrides a deprecated API.<br>
 * Note: Recompile with -Xlint:deprecation for details.<br>
 * <br>
 * $ javac -encoding utf-8 -Xlint:deprecation src/main/java/ch12/Ex12_7.java<br>
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

/**
 * @FunctionalInterface
 * : {@link FunctionalInterface}<br>
 * : 함수형 인터페이스에 붙이면, 컴파일러가 올바르게 작성했는지 체크<br>
 * : 함수형 인터페이스에는 하나의 추상메서드만 가져야 한다는 제약이 있음 () -> {}<br>
 * : 예) {@link Runnable#run()}
 */
@FunctionalInterface//함수형 인터페이스는 하나의 추상 메서드만 가능
interface Testable {
    void test();//추상메서드
    //void check();//추상메서드
}