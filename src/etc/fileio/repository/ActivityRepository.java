package etc.fileio.repository;

import etc.fileio.domain.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 특정 타입의 학습 활동만 담는 제네릭 레포지토리
 */
public class ActivityRepository<T extends LearningActivity> {

    /** CSV 파일의 컬럼 순서. 헤더 행과 데이터 행 모두 이 순서를 따른다. */
    private static final String CSV_HEADER =
            "type,title,minutes,visibility,tags,instructorName,completionRate,bookTitle";

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

    // CSV 영속화 ----------------------------------------------------------------------

    // 저장소의 모든 활동 객체를 CSV 파일로 저장한다.
    public void saveToFile(Path csvPath) throws IOException {
        Path parent = csvPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        // 파일 입출력을 담당하는 객체 BufferedWriter(문자 기반 스트림)
        // 첫번째 매개값: 파일 경로, 두번째 매개값: 문자열 인코딩 방식 (한글 작성 시 UTF_8)
        // try-with-resource: AutoCloseable 인터페이스의 구현체인 경우 자동으로 close()를 진행해 주는 문법
        try (BufferedWriter writer = Files.newBufferedWriter(csvPath, StandardCharsets.UTF_8)) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (T activity : storage) {
                writer.write(toCsvRow(activity));
                writer.newLine();
            }
        }
    }

    // CSV 파일을 읽어 LearningActivity 레포지토리로 복원
    public static ActivityRepository<LearningActivity> loadFromFile(Path csvPath) throws IOException {

        ActivityRepository<LearningActivity> repository = new ActivityRepository<>();

        try (BufferedReader reader = Files.newBufferedReader(csvPath, StandardCharsets.UTF_8)) {
            reader.readLine(); // 헤더 행 건너뛰기 (다음 줄을 읽기는 해야 되는데, 변수에 담지는 않겠다)

            String line;
            // 한 행을 읽어 들여서 line 변수에 할당한 그 결과가 null이 아니라면 true
            while ((line = reader.readLine()) != null) {
                LearningActivity activity = parseCsvRow(line);
                repository.add(activity);
            }
        }
        return repository;
    }

    // "LECTURE,Stream 이론,55,PUBLIC,이론;stream,박코치,,"
    private static LearningActivity parseCsvRow(String line) throws IOException {
        // 두 번째 매개값 -1: 끝에 오는 빈 필드도 결과 배열에 포함시킨다.
        String[] cols = line.split(",", -1);
        if (cols.length < 8) {
            throw new IOException("CSV 컬럼 수가 부족합니다. (8개 필요, 실제 " + cols.length + "개)");
        }

        String type = cols[0];
        String title = cols[1];
        int minutes;
        try {
            minutes = Integer.parseInt(cols[2]);
        } catch (NumberFormatException e) {
            throw new IOException("minutes 컬럼이 정수가 아닙니다: " + cols[2], e);
        }

        Visibility visibility;
        try {
            visibility = Visibility.valueOf(cols[3]);
        } catch (IllegalArgumentException e) {
            throw new IOException("알 수 없는 visibility 값: " + cols[3], e);
        }

        String tagsField = cols[4];
        String instructorName = cols[5];
        String completionRateField = cols[6];
        String bookTitle = cols[7];

        ActivityCategory category;
        try {
            category = ActivityCategory.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new IOException("알 수 없는 활동 유형: " + type, e);
        }

        LearningActivity activity;
        switch (category) {
            case LECTURE:
                activity = new LectureLog(title, minutes, visibility, instructorName);
                break;
            case PRACTICE:
                int completionRate;
                try {
                    completionRate = Integer.parseInt(completionRateField);
                } catch (NumberFormatException e) {
                    throw new IOException("completionRate가 정수가 아닙니다: "
                            + completionRateField, e);
                }
                activity = new PracticeLog(title, minutes, visibility, completionRate);
                break;
            case READING:
                activity = new ReadingLog(title, minutes, visibility, bookTitle);
                break;
            default:
                throw new IOException("처리할 수 없는 활동 유형: " + type);
        }

        // 태그 복원
        if (!tagsField.isBlank()) {
            for (String tag : tagsField.split(";")) {
                if (!tag.isBlank()) {
                    activity.addTag(tag);
                }
            }
        }

        return activity;

    }


    /**
     * 활동을 CSV 한 행으로 직렬화한다.
     */
    private String toCsvRow(T activity) {
        String type = activity.getCategory().name();
        String title = activity.getTitle();
        String minutes = String.valueOf(activity.getMinutes());
        String visibility = activity.getVisibility().name();
        String tags = String.join(";", activity.getTags());

        String instructorName = "";
        String completionRate = "";
        String bookTitle = "";

        if (activity instanceof LectureLog) {
            instructorName = ((LectureLog) activity).getInstructorName();
        } else if (activity instanceof PracticeLog) {
            completionRate = String.valueOf(((PracticeLog) activity).getCompletionRate());
        } else if (activity instanceof ReadingLog) {
            bookTitle = ((ReadingLog) activity).getBookTitle();
        }

        return String.join(",",
                type, title, minutes, visibility, tags,
                instructorName, completionRate, bookTitle);

    }


}











