package etc.fileio.json.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import etc.fileio.json.policy.Reviewable;
import etc.fileio.json.policy.Shareable;

public class ReadingLog extends LearningActivity implements Reviewable, Shareable {

    private String bookTitle;

    @JsonCreator
    public ReadingLog(@JsonProperty("title") String title,
                      @JsonProperty("minutes") int minutes,
                      @JsonProperty("visibility") Visibility visibility,
                      @JsonProperty("bookTitle") String bookTitle) {
        super(title, minutes, visibility, ActivityCategory.READING);
        this.bookTitle = bookTitle;
    }


    @Override
    public boolean needsReview() {
        return getCategory().isShortStudy(getMinutes());
    }

    @Override
    public void printReviewTarget() {
        System.out.println("[복습 권장] " + getTitle() + " (" + bookTitle + ")");
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
        return "독서";
    }

    @Override
    public String getDetailText() {
        return "책: " + bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
