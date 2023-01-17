package ch12;

import java.lang.annotation.*;

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
