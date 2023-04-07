package ch12;

import java.lang.annotation.*;

/**
 * - 메타 애노테이션
 *      : 표준 애노테이션 내에 애노테이션을 만들 때 사용하는 애노테이션
 *      : {@link Target} 애노테이션 적용가능한 대상을 지정하는데 사용한다.
 *              예시) {@link SuppressWarnings} @Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE}); 타입, 필드, 메서드, 파라미터, 생성자, 로컬변수 모두에 적용가능
 *      : {@link Documented} 애노테이션 정보가 javadoc 으로 작성된 문서에 포함되게 한다.
 *              - javadoc 으로 작성한 문서에 포함시키려면 작성
 *              - java 사용하는 개발자는 잘사용하지 않음
 *              예시) {@link FunctionalInterface}
 *      : {@link Inherited} 애노테이션이 자손 클래스에 상속되도록 한다.
 *              - 애노테이션을 자손 클래스에 상속하고자 할 때 붙인다.
 *      : {@link Retention} 애노테이션이 유지(retention)되는 범위를 지정하는데 사용한다.
 *              - {@link RetentionPolicy} SOURCE; 소스 파일에만 존재, 클래스 파일에는 존재하지 않음
 *              - {@link RetentionPolicy} CLASS; 클래스 파일에 존재, 실행시에 사용불가, 기본값.(클래스 로더까지 사용할수 있음, 잘안씀)
 *              - {@link RetentionPolicy} RUNTIME; 클래스 파일에 존재, 실행시에 사용가능
 *              예시) {@link Override}
 *                  ; 클래스 파일에 X, 실행시에도 사용 불가, 컴파일 타임에도 사용 불가
 *                  ; 오버라이딩을 올바르게 했는지 컴파일러가 체크하기 위한 목적, 체크 이후에는 필요없게 되므로 유지정책 SOURCE(실행시, 혹은 클래스파일에 유지될 필요 없음)
 *              예시) {@link FunctionalInterface}
 *                  ; 클래스 파일에 존재, 실행시에도 사용 가능, 컴파일 타임에도 사용 가능
 *      : {@link Repeatable} 애노테이션을 반복해서 적용할 수 있게 한다.
 */
enum TestType {
    FIRST, FINAL

}

@Retention(RetentionPolicy.RUNTIME)
@interface MarkerAnnotationTest {
}

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface DateTime {
    String yymmdd() default "000000";

    String hhmmss() default "000000";
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestInfo {
    int count() default 1;

    String testedBy();

    String[] testTools() default "JUnit";

    TestType testType() default TestType.FIRST;

    DateTime testDate();
}

@TestInfo(testedBy = "yhkim", testDate = @DateTime(yymmdd = "161123", hhmmss = "235959"))
@MarkerAnnotationTest
public class Ex12_8 {
    @DateTime
    @MarkerAnnotationTest
    public static void main(String[] args) throws NoSuchMethodException {
        annotationTest();
    }

    private static void annotationTest() throws NoSuchMethodException {
        printTestInfo();
        printMarkerAnnotationTest();
        printClassAnnotations();
        printMethodAnnotations();
    }

    private static void printTestInfo() {
        System.out.printf("testInfo = %s%n", Ex12_8.class.getAnnotation(TestInfo.class));
    }

    private static void printMarkerAnnotationTest() {
        MarkerAnnotationTest markerAnnotationTest = Ex12_8.class
                .getAnnotation(MarkerAnnotationTest.class);
        if (markerAnnotationTest != null) {
            System.out.printf("markerAnnotationTest = %s%n", markerAnnotationTest);
        }
    }

    private static void printClassAnnotations() {
        Annotation[] classAnnotations = Ex12_8.class.getAnnotations();
        for (Annotation classAnnotation : classAnnotations) {
            System.out.printf("classAnnotation = %s%n", classAnnotation);
        }
    }

    private static void printMethodAnnotations() throws NoSuchMethodException {
        Annotation[] methodAnnotations = Ex12_8.class.getMethod("main", String[].class).getAnnotations();
        for (Annotation methodAnnotation : methodAnnotations) {
            System.out.println("methodAnnotation = " + methodAnnotation);
        }
    }
}
