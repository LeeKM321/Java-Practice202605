package etc.fileio.json.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import etc.fileio.json.policy.Reviewable;
import etc.fileio.json.policy.Shareable;

// LectureLog는 LearningActivity의 한 종류이고, Reviewable에 선언된 역할도 수행할 수 있다.
public class LectureLog extends LearningActivity implements Reviewable, Shareable {

    public String instructorName; // 강사 이름 (LectureLog만 가지는 고유한 필드)

    @JsonCreator // JSON을 객체로 만들 때 이 생성자를 써라
    public LectureLog(@JsonProperty("title") String title,
                      @JsonProperty("minutes") int minutes,
                      @JsonProperty("visibility") Visibility visibility,
                      @JsonProperty("instructorName") String instructorName) {
        super(title, minutes, visibility, ActivityCategory.LECTURE);
        this.instructorName = normalizeInstructorName(instructorName);
    }

    @Override
    public boolean needsReview() {
        return getCategory().isShortStudy(getMinutes());
    }

    @Override
    public void printReviewTarget() {
        System.out.println("[복습 권장] " + getTitle() + " (" + getMinutes() + "분)");
    }

    private String normalizeInstructorName(String instructorName) {
        if (instructorName == null || instructorName.isBlank()) {
            return "강사 미정";
        }

        return instructorName;
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
        return "강의";
    }

    @Override
    public String getDetailText() {
        return "강사: " + instructorName;
    }

    public String getInstructorName() {
        return instructorName;
    }
}






