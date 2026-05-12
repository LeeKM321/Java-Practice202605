package oop.collection.app;

import oop.collection.domain.*;
import oop.collection.printer.ActivityPrinter;
import oop.collection.printer.ConsoleActivityPrinter;
import oop.collection.service.ActivityDashboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SprintLogApp {

    public static void main(String[] args) {

        // ── 1. 활동 목록 구성 ─────────────────────────────────────────
        // 달라진 점: ActivityDashboard가 이제 이 List를 직접 받는다.
        List<LearningActivity> activities = new ArrayList<>();

        LearningActivity lecture = new LectureLog(
                "Java 컬렉션 프레임워크 이론", 50, Visibility.PUBLIC, "박코치");
        LearningActivity lecture2 = new LectureLog(
                "Java 내부 클래스 활용", 70, Visibility.PUBLIC, "김코치");
        LearningActivity practice1 = new PracticeLog(
                "ArrayList 실습", 80, Visibility.PUBLIC, 90);
        LearningActivity reading = new ReadingLog(
                "Effective Java 3판", 35, Visibility.PRIVATE, "조슈아 블로크");
        LearningActivity practice2 = new PracticeLog(
                "LinkedList 비교 실습", 40, Visibility.PRIVATE, 75);
        LearningActivity practice3 = new PracticeLog(
                "HashMap 비교 실습", 30, Visibility.PRIVATE, 95);

        activities.add(lecture);
        activities.add(lecture2);
        activities.add(practice1);
        activities.add(reading);
        activities.add(practice2);
        activities.add(practice3);

        // ── 2. 태그 부착 ──────────────────────────────────────────────
        // 태그는 Set<String>으로 관리되므로 중복은 자동으로 무시된다.
        System.out.println("=== 태그 부착 ===");
        lecture.addTag("이론");
        lecture.addTag("JCF");
        practice1.addTag("실습");
        practice1.addTag("JCF");
        practice2.addTag("실습");
        reading.addTag("이론");
        reading.addTag("도서");
        lecture2.addTag("추가");
        lecture2.addTag("이론");
        practice3.addTag("추가");
        practice3.addTag("실습");

        // 같은 태그를 두 번 추가해도 Set이므로 한 번만 들어간다
        lecture.addTag("JCF");  // 이미 있으면 무시됨
        System.out.println("lecture 태그: " + lecture.getTags());
        System.out.println("reading 태그: " + reading.getTags());

        // ── 3. ActivityDashboard — 이제 List를 받는다 ─────────────────
        // 기존: new ActivityDashboard(배열)  →  new ActivityDashboard(List)
        System.out.println();
        System.out.println("=== ActivityDashboard(List) 생성 ===");
        ActivityDashboard dashboard = new ActivityDashboard(activities);

        ActivityDashboard.Summary summary = dashboard.summarize();
        System.out.println("총 활동: " + summary.getTotalCount()
                + "개 (강의 " + summary.getLectureCount()
                + " / 실습 " + summary.getPracticeCount()
                + " / 독서 " + summary.getReadingCount() + ")");

        // ── 4. groupByCategory() — 카테고리별 그룹화 ─────────────────
        // Map<ActivityCategory, List<LearningActivity>> 반환
        System.out.println();
        System.out.println("=== 카테고리별 그룹화 ===");
        Map<ActivityCategory, List<LearningActivity>> grouped = dashboard.groupByCategory();

        ActivityCategory[] arr = ActivityCategory.values();
        System.out.println(Arrays.toString(arr));

        for (ActivityCategory cat : ActivityCategory.values()) {
            List<LearningActivity> group = grouped.get(cat);
            if (group == null) {
                System.out.println(cat.getLabel() + ": 없음");
            } else {
                System.out.println(cat.getLabel() + ": " + group.size() + "개");
                for (LearningActivity a : group) {
                    System.out.println("  - " + a.getTitle() + " (" + a.getMinutes() + "분)");
                }
            }
        }

        // ── 5. filterByTag() — 태그로 필터링 ─────────────────────────
        // 이 패턴으로 조건부 필터링이 가능하다.
        System.out.println();
        System.out.println("=== 태그 필터링: 'JCF' ===");
        List<LearningActivity> jcfActivities = dashboard.filterByTag("JCF");
        System.out.println("'JCF' 태그 활동 수: " + jcfActivities.size());
        for (LearningActivity a : jcfActivities) {
            System.out.println("  - " + a.getTitle());
        }

        System.out.println();
        System.out.println("=== 태그 필터링: '추가' ===");
        List<LearningActivity> practiceTagged = dashboard.filterByTag("추가");
        System.out.println("'추가' 태그 활동 수: " + practiceTagged.size());
        for (LearningActivity a : practiceTagged) {
            System.out.println("  - " + a.getTitle());
        }

        // ── 6. ReportBuilder — 기존 API 그대로 동작 ──────────────────
        // 예전부터 있던 ReportBuilder는 인터페이스가 변하지 않았다.
        // ActivityDashboard 내부가 배열 → List로 바뀌어도 외부에서는 차이가 없다.
        System.out.println();
        System.out.println("=== ReportBuilder (기존 API 유지) ===");
        ActivityPrinter printer = new ConsoleActivityPrinter();
        dashboard.new ReportBuilder(printer).print();

        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());


    }



}









