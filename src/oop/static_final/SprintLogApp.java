package oop.static_final;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningLog javaLog = new LearningLog("Java 시작", 40);
        LearningLog gitLog = new LearningLog("Git 복습", 30, false);
        LearningLog encapLog = new LearningLog("캡슐화 연습", 90);

        LearningLog[] logs = {javaLog, gitLog, encapLog};

        for (LearningLog log : logs) {
            log.printSummary();
        }

        System.out.println("=======================================================");

        // static 변수나 메서드는 객체마다 가지고 있는 값이 아니기 때문에
        // 값을 참조할 때 선언된 클래스의 이름으로 참조할 수 있습니다.
        System.out.println("지금까지 생성된 학습 기록 수: " + LearningLog.getTotalCreateCount());


    }

}









