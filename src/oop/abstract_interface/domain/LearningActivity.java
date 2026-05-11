package oop.abstract_interface.domain;

public abstract class LearningActivity {

    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private boolean publicActivity;

    public LearningActivity(String title, int minutes) {
        this(title, minutes, true);
    }

    public LearningActivity(String title, int minutes, boolean publicActivity) {
        totalCreateCount++;
        this.id = totalCreateCount;
        this.title = normalizeTitle(title);
        this.minutes = minutes;
        this.publicActivity = publicActivity;
    }

    public void extendStudy(int additionalMinutes) {
        if (additionalMinutes <= 0) {
            System.out.println("잘못된 공부 시간입니다.");
            return;
        }

        this.minutes += additionalMinutes;
    }

    public void changeTitle(String newTitle) {
        this.title = normalizeTitle(newTitle);
    }

    private String normalizeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            return "제목 없음";
        }

        return newTitle;
    }

    public void openToPublic() {
        this.publicActivity = true;
    }

    public void hideFromPublic() {
        this.publicActivity = false;
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

    public boolean ispublicActivity() {
        return publicActivity;
    }

    public String getVisibilityText() {
        return publicActivity ? "공개" : "비공개";
    }
}
