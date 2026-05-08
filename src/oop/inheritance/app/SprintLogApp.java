package oop.inheritance.app;

import oop.inheritance.domain.LearningActivity;
import oop.inheritance.domain.LectureLog;
import oop.inheritance.domain.PracticeLog;

public class SprintLogApp {

    public static void main(String[] args) {

        LearningActivity javaLecture = new LectureLog("Java 객체지향", 50, true, "박코치");
        LearningActivity gitPractice = new PracticeLog("Git 브랜치 실습", 70, true, 80);
        PracticeLog oopPractice = new PracticeLog("캡슐화 리팩터링", 40, false, 45);

        // 다형성은 분명 많은 장점이 있지만, 부모의 정보로 자식의 고유한 필드와 메서드를 호출할 수는 없습니다.
        // 만약 하고 싶다면 자식 타입의 변수를 선언해서 주소값을 넘겨야 합니다. 그 과정에서 형 변환이 필요합니다.
//        String instructorName = javaLecture.instructorName;
//        javaLecture.method1();
        LectureLog lec = (LectureLog) javaLecture;
        lec.method1();

        // 다형성
        LearningActivity[] logs = {javaLecture, gitPractice, oopPractice};

        for (LearningActivity log : logs) {
            log.printSummary();
        }

        System.out.println("=== 복습이 필요한 활동 ===");
        for (LearningActivity log : logs) {
            if (log.needsReview()) {
                log.printSummary();
            }
        }

        System.out.println();
        System.out.println("=== 실습 활동만 보기 ===");
        for (LearningActivity log : logs) {
            // 좌항의 객체가 우항의 타입을 가질 수 있는가? 가질 수 있으면 true, 가질 수 없으면 false
            if (log instanceof PracticeLog practiceLog) {
//                System.out.println(log.getTitle() + " 완료율: " + ((PracticeLog)log).getCompletionRate() + "%");
                System.out.println(practiceLog.getTitle() + " 완료율: " + practiceLog.getCompletionRate() + "%");
            }
        }

    }

}









