package ch12;

public class Ex12_7 {
    public static void main(String[] args) {
        new Child().parentMethod();
        new Child().parentmethod();
    }
}
class Parent {
    void parentMethod() {
        System.out.println("Parent.parentMethod");
    }

}

/**
 *
 * @Override
 * : {@link Override}<br>
 * : 오버라이딩을 올바르게 했는지 컴파일러가 체크하게 한다.<br>
 * : 오버라이딩할 때 메서드이름을 잘못 적는 실수를 하는 경우가 많다.<br>
 * : 오버라이딩 할 때는 메서드 선언부 앞에 @Override 를 붙이자.
 */
class Child extends Parent {
    //@Override
    public void parentmethod() {
        //부모의 이름을 잘못 적었기 때문에 Override 되지 않음, @Override 애노테이션이 있을 경우 컴파일 오류
        System.out.println("Child.parentMethod");
    }
}
