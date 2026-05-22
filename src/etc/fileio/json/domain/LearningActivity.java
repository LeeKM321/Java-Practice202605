package etc.fileio.json.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import etc.fileio.json.exception.InvalidActivityException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// 다형성을 어떻게 표현할 지 정의.
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // 타입을 이름으로 식별
        include = JsonTypeInfo.As.PROPERTY,
        property = "type" // JSON에 type이라는 필드를 추가해서 거기에 이름을 넣겠다
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LectureLog.class, name = "LECTURE"),
        @JsonSubTypes.Type(value = PracticeLog.class, name = "PRACTICE"),
        @JsonSubTypes.Type(value = ReadingLog.class, name = "READING")
})
@JsonIgnoreProperties({"id", "category"}) // id와 category 필드는 JSON 데이터에 포함시키지 말아라.
public abstract class LearningActivity {

    private static int totalCreateCount = 0;

    private final long id;
    private String title;
    private int minutes;
    private Visibility visibility;
    private final ActivityCategory category;
    private final Set<String> tags = new HashSet<>();


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

    /**
     * 태그를 추가한다. 공백은 제거하고, 소문자로 저장한다.
     * 중복 태그는 무시한다 (Set의 특성)
     */
    public void addTag(String tag) {
        if (tag == null || tag.isBlank()) {
            throw new InvalidActivityException("태그는 비워둘 수 없습니다.");
        }
        tags.add(tag.trim().toLowerCase());
    }

    /**
     * 등록된 태그 목록을 읽기 전용으로 반환한다.
     */
    public Set<String> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * 해당 태그가 등록되어 있는지 확인한다.
     */
    public boolean hasTag(String tag) {
        if (tag == null) return false;
        return tags.contains(tag.trim().toLowerCase());
    }

    // tags 필드는 final이라 Jackson이 직접 set 하기가 어렵다.
    // tags가 Set인데, final이라 Set 자체를 통째로 갈아 끼울 수가 없다.
    // JSON에서 읽어온 Set 내용을 하나씩 tags에 add 시켜주는 메서드
    @JsonProperty("tags") // tags 필드를 세팅 할 때 이 메서드를 써라!
    private void setTagsFromJson(Set<String> incoming) {
        if (incoming == null) return;
        this.tags.clear();
        for (String tag : incoming) {
            if (tag != null && !tag.isBlank()) {
                this.tags.add(tag.trim().toLowerCase());
            }
        }
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
