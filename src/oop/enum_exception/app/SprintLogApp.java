package oop.enum_exception.app;

import oop.enum_exception.domain.*;
import oop.enum_exception.exception.InvalidActivityException;
import oop.enum_exception.policy.Reviewable;
import oop.enum_exception.policy.Shareable;
import oop.enum_exception.printer.ActivityPrinter;
import oop.enum_exception.printer.CompactActivityPrinter;
import oop.enum_exception.printer.ConsoleActivityPrinter;

public class SprintLogApp {

    public static void main(String[] args) {

        // ── 1. 기본 활동 목록 ──────────────────────────────────────────
        // boolean 대신 Visibility enum으로 공개 상태를 표현한다.
        LearningActivity[] activities = {
                new LectureLog("Java enum 기초", 50, Visibility.PUBLIC, "박코치"),
                new PracticeLog("예외 처리 실습", 80, Visibility.PUBLIC, 90),
                new ReadingLog("객체지향의 사실과 오해", 35, Visibility.PRIVATE, "조영호")
        };

        ActivityPrinter printer = new ConsoleActivityPrinter();
        System.out.println("=== 학습 활동 목록 ===");
        for (LearningActivity activity : activities) {
            printer.print(activity);
        }

        // ── 2. enum 기본 메서드 시연 ───────────────────────────────────
        System.out.println();
        System.out.println("=== enum 기본 정보 ===");
        System.out.println("name():    " + ActivityCategory.LECTURE.name());
        System.out.println("ordinal(): " + ActivityCategory.LECTURE.ordinal());
        System.out.println("label:     " + ActivityCategory.LECTURE.getLabel());
        printCategoryGuide(ActivityCategory.PRACTICE);

        // ── 3. 잘못된 입력 → 예외 시연 ───────────────────────────────
        System.out.println();
        System.out.println("=== 잘못된 입력 처리 ===");

        try {
            new LectureLog("", 50, Visibility.PUBLIC, "박코치");
        } catch (InvalidActivityException e) {
            System.out.println("등록 실패: " + e.getMessage());
        }

        try {
            new PracticeLog("예외 실습", -10, Visibility.PUBLIC, 110);
        } catch (InvalidActivityException e) {
            System.out.println("등록 실패: " + e.getMessage());
        }

        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());

    }

    /** switch 문 + enum 조합 시연. */
    private static void printCategoryGuide(ActivityCategory category) {
        // 정수나 문자열 이외에 Enum도 switch의 괄호 안에 기준으로서 들어갈 수 있습니다.
        switch (category) {
            case LECTURE  -> System.out.println("강의: 핵심 개념을 놓치지 않았는지 확인합니다.");
            case PRACTICE -> System.out.println("실습: 직접 손으로 흐름을 만들어보는 것이 중요합니다.");
            case READING  -> System.out.println("독서: 짧게 읽어도 정리 품질이 중요합니다.");
        }
    }

}









