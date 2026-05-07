package oop.constructor;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningLog javaLog = new LearningLog("Java 시작", 40);
        LearningLog gitLog = new LearningLog("Git 복습", 30, false);
        LearningLog conditionLog = new LearningLog("조건문 학습", 75);
        LearningLog loopLog = new LearningLog("반복문 학습", 120);

        LearningLog[] logs = {javaLog, gitLog, conditionLog, loopLog};

        System.out.println("=== 생성자로 만든 학습 기록 ===");
        for (LearningLog log : logs) {
            log.printSummary();
        }

    }

}
