package etc.fileio.serial.app;


import etc.fileio.serial.domain.*;
import etc.fileio.serial.repository.ActivityRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {

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

        ActivityRepository<LearningActivity> allRepo = new ActivityRepository<>();
        for (LearningActivity a : activities) {
            allRepo.add(a);
        }

        Path binPath = Path.of("data/activities.ser");
        allRepo.saveToBinary(binPath);
        System.out.println("객체 직렬화 저장 완료: " + binPath.toAbsolutePath());


    }
}