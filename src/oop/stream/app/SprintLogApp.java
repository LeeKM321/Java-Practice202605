package oop.stream.app;


import oop.stream.domain.*;
import oop.stream.repository.ActivityRepository;
import oop.stream.service.ActivityDashboard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ─────────────────────────────────────────────────────────────────
 * Stream 데이터 분석 실습 — SprintLogApp 스타터 파일
 * ─────────────────────────────────────────────────────────────────
 * <p>
 * 이 파일을 SprintLogApp.java에 복사해서 사용합니다.
 * <p>
 *  TODO 7개를 순서대로 완성하세요.
 *  각 TODO 위의 주석이 사용할 API를 안내합니다.
 * <p>
 * 완성하면 출력 결과가 아래와 같아야 합니다.
 * <p>
 * === partitioningBy() — 공개/비공개 분할 ===
 * 공개 활동 (4개):
 * JCF 이론
 * Stream 이론
 * List 실습
 * Stream 실습
 * 비공개 활동 (4개):
 * ...
 * <p>
 * === summarizingInt() — 학습 시간 통계 ===
 * 총 시간: 465분
 * 평균: 58.1분
 * 최대: 90분
 * 최소: 35분
 * 활동 수: 8개
 * <p>
 * === mapToInt() + sum/average ===
 * 총 학습 시간: 465분
 * 평균 학습 시간: 58.1분
 * <p>
 * === max() — 가장 긴 활동 ===
 * 가장 긴 활동: Stream 실습 (90분)
 * <p>
 * === 다중 조건 필터링 — 60분 이상 공개 실습 ===
 * List 실습 — 80분
 * Stream 실습 — 90분
 * <p>
 * === 다중 레벨 groupingBy — 카테고리 → 공개여부 → 활동 수 ===
 * 강의: 공개 2개 / 비공개 1개
 * 실습: 공개 2개 / 비공개 1개
 * 독서: 공개 0개 / 비공개 2개
 * <p>
 * === 카테고리별 총 학습 시간 ===
 * 강의: 150분
 * 실습: 240분
 * 독서: 75분
 * <p>
 * 총 생성된 활동 수: 8
 * ─────────────────────────────────────────────────────────────────
 */
public class SprintLogApp {

    public static void main(String[] args) {

        // ── 1. 활동 목록 구성 — 이 섹션은 완성되어 있습니다 ─────────────
        List<LearningActivity> activities = new ArrayList<>();

        LearningActivity l1 = new LectureLog("JCF 이론", 50, Visibility.PUBLIC, "박코치");
        LearningActivity l2 = new LectureLog("Stream 이론", 55, Visibility.PUBLIC, "박코치");
        LearningActivity l3 = new LectureLog("람다 이론", 45, Visibility.PRIVATE, "박코치");
        LearningActivity p1 = new PracticeLog("List 실습", 80, Visibility.PUBLIC, 90);
        LearningActivity p2 = new PracticeLog("Stream 실습", 90, Visibility.PUBLIC, 88);
        LearningActivity p3 = new PracticeLog("람다 실습", 70, Visibility.PRIVATE, 75);
        LearningActivity r1 = new ReadingLog("Effective Java", 40, Visibility.PRIVATE, "조슈아 블로크");
        LearningActivity r2 = new ReadingLog("Java 8 in Action", 35, Visibility.PRIVATE, "하먀");

        for (LearningActivity a : List.of(l1, l2, l3)) a.addTag("이론");
        for (LearningActivity a : List.of(p1, p2, p3)) a.addTag("실습");
        for (LearningActivity a : List.of(r1, r2)) a.addTag("도서");
        for (LearningActivity a : List.of(l2, p2)) a.addTag("stream");

        activities.addAll(List.of(l1, l2, l3, p1, p2, p3, r1, r2));


        // ── 2. partitioningBy() — true/false 기준으로 분할 ───────────────
        System.out.println("=== partitioningBy() — 공개/비공개 분할 ===");

        /*
         * TODO 1: Collectors.partitioningBy()를 사용해 공개 활동과 비공개 활동을 분리하세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .collect(Collectors.partitioningBy( 조건 ))
         *
         * 조건: LearningActivity::isPublicActivity
         * 반환 타입: Map<Boolean, List<LearningActivity>>
         *   - true  키 → 공개 활동 목록
         *   - false 키 → 비공개 활동 목록
         */
        Map<Boolean, List<LearningActivity>> byVisibility = null; // TODO 1: 위 힌트를 참고해 완성하세요

        byVisibility = activities.stream()
                .collect(Collectors.partitioningBy(a -> a.getVisibility() == Visibility.PUBLIC));

        System.out.println("공개 활동 (" + byVisibility.get(true).size() + "개):");
        byVisibility.get(true).forEach(a -> System.out.println("  " + a.getTitle()));
        System.out.println("비공개 활동 (" + byVisibility.get(false).size() + "개):");
        byVisibility.get(false).forEach(a -> System.out.println("  " + a.getTitle()));


        // ── 3. summarizingInt() — IntSummaryStatistics ───────────────────
        System.out.println();
        System.out.println("=== summarizingInt() — 학습 시간 통계 ===");

        /*
         * TODO 2: Collectors.summarizingInt()로 학습 시간 통계를 한 번에 구하세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .collect(Collectors.summarizingInt( 숫자 추출 함수 ))
         *
         * 숫자 추출 함수: LearningActivity::getMinutes
         * 반환 타입: IntSummaryStatistics
         *   - .getSum()     → 합계
         *   - .getAverage() → 평균 (double)
         *   - .getMax()     → 최대값
         *   - .getMin()     → 최소값
         *   - .getCount()   → 개수
         */
        IntSummaryStatistics stats = null; // TODO 2: 위 힌트를 참고해 완성하세요

        stats = activities.stream()
                .collect(Collectors.summarizingInt(LearningActivity::getMinutes));

        System.out.println("총 시간: " + stats.getSum() + "분");
        System.out.println("평균: " + String.format("%.1f", stats.getAverage()) + "분");
        System.out.println("최대: " + stats.getMax() + "분");
        System.out.println("최소: " + stats.getMin() + "분");
        System.out.println("활동 수: " + stats.getCount() + "개");


        // ── 4. mapToInt() — 기본 숫자 연산 ──────────────────────────────
        System.out.println();
        System.out.println("=== mapToInt() + sum/average ===");

        /*
         * TODO 3: mapToInt()로 총 학습 시간을 구하세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .mapToInt( 숫자 추출 함수 )  ← IntStream 반환
         *   .sum()
         *
         * mapToInt()는 IntStream을 반환합니다.
         * IntStream에는 .sum(), .average(), .max(), .min()이 직접 있습니다.
         */
        int totalMinutes = 0; // TODO 3: 위 힌트를 참고해 완성하세요

        totalMinutes = activities.stream()
                .mapToInt(LearningActivity::getMinutes)
                .sum();

        System.out.println("총 학습 시간: " + totalMinutes + "분");

        /*
         * TODO 4: mapToInt()로 평균 학습 시간을 구하세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .mapToInt(LearningActivity::getMinutes)
         *   .average()           ← IntStream의 average()
         *   .getAsDouble()       ← activities가 비어있지 않으므로 바로 꺼낼 수 있습니다
         */
        double avgMinutes = 0.0; // TODO 4: 위 힌트를 참고해 완성하세요

        avgMinutes = activities.stream()
                .mapToInt(LearningActivity::getMinutes)
                .average()
                .getAsDouble();

        System.out.println("평균 학습 시간: " + String.format("%.1f", avgMinutes) + "분");


        // ── 5. max() — 가장 긴 활동 찾기 ────────────────────────────────
        System.out.println();
        System.out.println("=== max() — 가장 긴 활동 ===");

        /*
         * TODO 5: max()로 가장 긴 활동을 찾으세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .max(Comparator.comparingInt(LearningActivity::getMinutes))
         *   .get()   ← activities가 비어있지 않으므로 바로 꺼낼 수 있습니다
         *
         * 반환 타입: LearningActivity
         */
        LearningActivity longestActivity = null; // TODO 5: 위 힌트를 참고해 완성하세요

        longestActivity = activities.stream()
                .max(Comparator.comparing(LearningActivity::getMinutes))
                .get();

        System.out.println("가장 긴 활동: " + longestActivity.getTitle() + " (" + longestActivity.getMinutes() + "분)");


        // ── 6. 다중 조건 필터링 ──────────────────────────────────────────
        System.out.println();
        System.out.println("=== 다중 조건 필터링 — 60분 이상 공개 실습 ===");

        /*
         * TODO 6: filter()를 3번 연결해 60분 이상, 공개, 실습 카테고리인 활동만 출력하세요.
         *
         * 조건 3가지 (순서는 어떻게 해도 결과는 같습니다):
         *   1) a.getCategory() == ActivityCategory.PRACTICE
         *   2) a.getMinutes() >= 60
         *   3) LearningActivity::isPublicActivity  (또는 람다로 써도 됩니다)
         *
         * 출력: a.getTitle() + " — " + a.getMinutes() + "분"
         */
        // TODO 6: activities.stream()으로 시작해 위 힌트를 참고해 완성하세요

        activities.stream()
                .filter(a -> a.getMinutes() >= 60)
                .filter(a -> a.getVisibility() == Visibility.PUBLIC)
                .filter(a -> a.getCategory() == ActivityCategory.PRACTICE)
                .forEach(a -> System.out.println(a.getTitle() + " — " + a.getMinutes() + "분"));


        // ── 7. 다중 레벨 groupingBy ──────────────────────────────────────
        System.out.println();
        System.out.println("=== 다중 레벨 groupingBy — 카테고리 → 공개여부 → 활동 수 ===");

        /*
         * TODO 7: groupingBy를 두 번 중첩해 카테고리 → 공개여부 → 활동 수를 집계하세요.
         *
         * 사용할 것:
         *   activities.stream()
         *   .collect(Collectors.groupingBy(
         *       LearningActivity::getCategory,         ← 바깥 분류 기준
         *       Collectors.groupingBy(
         *           LearningActivity::isPublicActivity, ← 안쪽 분류 기준
         *           Collectors.counting()              ← 개수 집계
         *       )))
         *
         * 반환 타입: Map<ActivityCategory, Map<Boolean, Long>>
         */
        Map<ActivityCategory, Map<Boolean, Long>> grouped = null; // TODO 7: 위 힌트를 참고해 완성하세요

        grouped = activities.stream()
                .collect(Collectors.groupingBy(LearningActivity::getCategory,
                        Collectors.groupingBy(LearningActivity::isPublicActivity,
                                Collectors.counting())));

        for (ActivityCategory cat : ActivityCategory.values()) {
            Map<Boolean, Long> inner = grouped.getOrDefault(cat, Map.of());
            long pub = inner.getOrDefault(true, 0L);
            long prv = inner.getOrDefault(false, 0L);
            System.out.println(cat.getLabel() + ": 공개 " + pub + "개 / 비공개 " + prv + "개");
        }


        // ── 8. 카테고리별 총 학습 시간 — 이 섹션은 완성되어 있습니다 ────
        System.out.println();
        System.out.println("=== 카테고리별 총 학습 시간 ===");
        Map<ActivityCategory, Integer> minutesByCategory = activities.stream()
                .collect(Collectors.groupingBy(
                        LearningActivity::getCategory,
                        Collectors.summingInt(LearningActivity::getMinutes)));

        minutesByCategory.forEach((cat, mins) ->
                System.out.println(cat.getLabel() + ": " + mins + "분"));

        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());

        // ── 9. TreeMap — 카테고리별 활동 수 (정렬 보장) ────────
        System.out.println();
        System.out.println("=== 카테고리별 활동 수 (TreeMap — 정렬된 순서) ===");
        ActivityDashboard dashboard = new ActivityDashboard(activities);
        Map<ActivityCategory, List<LearningActivity>> grouped2 = dashboard.groupByCategory();
        grouped2.forEach((cat, list) ->
                System.out.println(cat.getLabel() + ": " + list.size() + "개"));

        // ── 10. TreeSet — 정렬된 태그 목록 ─────────────────────
        System.out.println();
        System.out.println("=== 등록된 태그 목록 (TreeSet — 알파벳 정렬) ===");
        Set<String> sortedTags = dashboard.getSortedTagSet();
        System.out.println("태그: " + sortedTags);

        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());


        // ── 11. 제네릭 레포지토리 — 타입별 저장소 ───────────────
        System.out.println();
        System.out.println("=== ActivityRepository<T> — 제네릭 레포지토리 ===");

        // LectureLog만 담는 레포지토리 — 다른 타입을 add()하면 컴파일 오류
        ActivityRepository<LectureLog> lectureRepo = new ActivityRepository<>();
        lectureRepo.add((LectureLog) l1);
        lectureRepo.add((LectureLog) l2);
        lectureRepo.add((LectureLog) l3);

        // PracticeLog만 담는 레포지토리
        ActivityRepository<PracticeLog> practiceRepo = new ActivityRepository<>();
        practiceRepo.add((PracticeLog) p1);
        practiceRepo.add((PracticeLog) p2);
        practiceRepo.add((PracticeLog) p3);

        ActivityRepository<ReadingLog>  readingRepo = new ActivityRepository<>();
        readingRepo.add((ReadingLog) r1);
        readingRepo.add((ReadingLog) r2);

        // findAll() 반환 타입이 List<LectureLog> — 캐스팅 없이 바로 쓴다
        List<LectureLog> allLectures = lectureRepo.findAll();
        System.out.println("강의 레포지토리 — 저장된 수: " + lectureRepo.count() + "개");
        System.out.println("강의 레포지토리 — 총 학습 시간: " + lectureRepo.getTotalMinutes() + "분");

        // filter()도 타입 안전 — Predicate<LectureLog>, 결과도 List<LectureLog>
        List<LectureLog> publicLectures = lectureRepo.filter(LearningActivity::isPublicActivity);
        System.out.println("공개 강의: " + publicLectures.size() + "개");
        publicLectures.forEach(lec -> System.out.println("  " + lec.getTitle()
                + " [강사: " + lec.getInstructorName() + "]"));

        System.out.println("실습 레포지토리 — 저장된 수: " + practiceRepo.count() + "개");
        System.out.println("실습 레포지토리 — 총 학습 시간: " + practiceRepo.getTotalMinutes() + "분");

        Optional<PracticeLog> first
                = practiceRepo.findFirst(a -> a.getCompletionRate() >= 70);

        first.ifPresent(practiceLog -> System.out.println("findFirst의 결과: " + practiceLog));


        System.out.println();
        System.out.println("총 생성된 활동 수: " + LearningActivity.getTotalCreatedCount());


    }
}