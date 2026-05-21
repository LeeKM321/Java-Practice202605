package oop.stream.repository;

import oop.stream.domain.LearningActivity;
import oop.stream.domain.LectureLog;
import oop.stream.domain.PracticeLog;
import oop.stream.domain.ReadingLog;
import oop.stream.policy.Shareable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 특정 타입의 학습 활동만 담는 제네릭 레포지토리
 */
public class ActivityRepository<T extends LearningActivity> {

    private final List<T> storage = new ArrayList<>();

    public void add(T activity) {
        if (activity == null) {
            throw new IllegalArgumentException("저장할 활동은 null일 수 없습니다.");
        }
        storage.add(activity);
    }

    // 저장된 모든 활동을 반환한다.
    public List<T> findAll() {
        return Collections.unmodifiableList(storage);
    }

    // 조건에 맞는 활동'들'만 골라 반환한다.
    public List<T> filter(Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T activity : storage) {
            if (predicate.test(activity)) {
                result.add(activity);
            }
        }
        return result;
    }

    // 조건에 맞는 첫 번째 활동을 골라 반환한다.
    public Optional<T> findFirst(Predicate<T> predicate) {
        for (T activity : storage) {
            if (predicate.test(activity)) {
                return Optional.of(activity);
            }
        }
        return Optional.empty();
    }

    // 저장한 활동 수를 반환한다.
    public int count() {
        return storage.size();
    }

    // 저장된 모든 활동의 총 학습 시간(분)을 반환한다.
    public int getTotalMinutes() {
        int total = 0;
        for (T activity : storage) {
            total += activity.getMinutes(); // T가 LearningActivity의 자식이기 때문에 getMinutes() 호출 가능
        }
        return total;
    }





}











