package etc.fileio.repository;

import etc.fileio.domain.LearningActivity;
import etc.fileio.domain.LectureLog;
import etc.fileio.domain.PracticeLog;
import etc.fileio.domain.ReadingLog;

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











