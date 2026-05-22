package etc.fileio.json.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import etc.fileio.json.policy.Reviewable;
import etc.fileio.json.policy.Shareable;

public class PracticeLog extends LearningActivity implements Reviewable, Shareable {

    private static final int MINIMUM_COMPLETION_RATE = 70;

    private int completionRate; // PracticeLog만 가지는 고유한 필드

    @JsonCreator
    public PracticeLog(@JsonProperty("title") String title,
                       @JsonProperty("minutes") int minutes,
                       @JsonProperty("visibility") Visibility visibility,
                       @JsonProperty("completionRate") int completionRate) {
        super(title, minutes, visibility, ActivityCategory.PRACTICE);
        this.completionRate = normalizeCompletionRate(completionRate);
    }


    @Override
    public boolean needsReview() {
        return getCategory().isShortStudy(getMinutes()) || completionRate < MINIMUM_COMPLETION_RATE;
    }

    @Override
    public void printReviewTarget() {
        System.out.println("[복습 권장] " + getTitle() + " (완료율: " + completionRate + "%)");
    }

    public int getCompletionRate() {
        return completionRate;
    }

    private int normalizeCompletionRate(int completionRate) {
        if (completionRate < 0) {
            return 0;
        }

        if (completionRate > 100) {
            return 100;
        }

        return completionRate;
    }

    @Override
    public boolean canShare() {
        return isPublicActivity();
    }

    @Override
    public String getShareTitle() {
        return getTitle();
    }

    @Override
    public String getActivityType() {
        return "실습";
    }

    @Override
    public String getDetailText() {
        return "완료율: " + completionRate + "%";
    }
}









