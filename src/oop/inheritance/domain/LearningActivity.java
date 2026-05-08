package oop.inheritance.domain;

public class LearningActivity {

    private static final int REVIEW_THRESHOLD_MINUTES = 60;
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


    public void printSummary() {
        String visibility = publicActivity ? "공개" : "비공개";
        System.out.println("#" + id + ". " + this.title + " - " + this.minutes + "분 - " + visibility);
    }

    public boolean needsReview() {
        return minutes < REVIEW_THRESHOLD_MINUTES;
    }

    public static int getTotalCreateCount() {
        return totalCreateCount;
    }

    public static int getReviewThresholdMinutes() {
        return REVIEW_THRESHOLD_MINUTES;
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
}
