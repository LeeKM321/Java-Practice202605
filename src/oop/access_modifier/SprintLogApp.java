package oop.access_modifier;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningLog javaLog = new LearningLog("Java 시작", 40);
        LearningLog gitLog = new LearningLog("Git 복습", 30, false);

//        javaLog.title = "야호";
//        javaLog.minutes = -374872;
//        javaLog.publicLog = false; 모든 필드가 private 접근 제한자를 지정했기 때문에 값을 참조하거나 수정이 불가능해 집니다.

        javaLog.extendStudy(200);
        javaLog.changeTitle("Java 객체지향과 캡슐화");
        javaLog.hideFromPublic();

        gitLog.extendStudy(45);
        gitLog.openToPublic();

        javaLog.printSummary();
        gitLog.printSummary();


    }

}
