package oop.intro;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningLog javaLog = new LearningLog();
        javaLog.title = "Java 시작";
        javaLog.minutes = 40;
        javaLog.publicLog = true;

        LearningLog gitLog = new LearningLog();
        gitLog.title = "Git 복습";
        gitLog.minutes = 30;
        gitLog.publicLog = false;

        LearningLog conditionLog = new LearningLog();
        conditionLog.title = "조건문 학습";
        conditionLog.minutes = 75;
        conditionLog.publicLog = true;

        LearningLog loopLog = new LearningLog();
        loopLog.title = "반복문 학습";
        loopLog.minutes = 120;
        loopLog.publicLog = true;

        LearningLog[] logs = {javaLog, gitLog, conditionLog, loopLog};

        System.out.println("==== 객체로 관리하는 학습 기록 ===");
        for (int i = 0; i < logs.length; i++) {
            logs[i].printSummary();
        }

        System.out.println();
        System.out.println("==== 복습 필요 기록 ====");
        for (LearningLog log : logs) {
            if (log.needsReview()) {
                log.printSummary();
            }
        }

        System.out.println();
        System.out.println("==== 공개 기록 ====");
        for (LearningLog log : logs) {
            if (!log.publicLog) {
                continue;
            }
            log.printSummary();
        }





    }

}
