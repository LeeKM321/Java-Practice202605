package oop.enum_exception.domain;

import oop.enum_exception.exception.InvalidActivityException;

public abstract class LearningActivity {

    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private Visibility visibility;
    private final ActivityCategory category;

//    public LearningActivity(String title, int minutes) {
//        this(title, minutes, Visibility.PUBLIC);
//    }

    public LearningActivity(String title, int minutes, Visibility visibility, ActivityCategory category) {
        validateTitle(title);
        validateMinutes(minutes);
        totalCreateCount++;
        this.id = totalCreateCount;
        this.title = title.trim(); // 좌우 공백 제거
        this.minutes = minutes;
        this.visibility = visibility;
        this.category = category;
    }

    public static int getTotalCreatedCount() {
        return totalCreateCount;
    }

    public void extendStudy(int additionalMinutes) {
        if (additionalMinutes <= 0) {
            throw new InvalidActivityException(
                    "추가 학습 시간은 1분 이상이어야 합니다. 입력값: " + additionalMinutes);
        }

        this.minutes += additionalMinutes;
    }

    public void changeTitle(String newTitle) {
        validateTitle(newTitle);
        this.title = newTitle;
    }

    private void validateTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            throw new InvalidActivityException("학습 제목은 비워둘 수 없습니다.");
        }
    }

    private void validateMinutes(int newMinutes) {
        if (newMinutes <= 0) {
            throw new InvalidActivityException("학습 시간은 1분 이상이여야 합니다. 입력값: " + newMinutes);
        }
    }

    public void openToPublic() {
        this.visibility = Visibility.PUBLIC;
    }

    public void hideFromPublic() {
        this.visibility = Visibility.PRIVATE;
    }


    public abstract String getActivityType(); // 강의, 실습, 독서
    public abstract String getDetailText(); // 유형별 세부 정보

    public static int getTotalCreateCount() {
        return totalCreateCount;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public String getVisibilityText() {
        return this.visibility.getLabel();
    }

    public boolean isPublicActivity() {
        return this.visibility == Visibility.PUBLIC;
    }

    public ActivityCategory getCategory() {
        return category;
    }
}
