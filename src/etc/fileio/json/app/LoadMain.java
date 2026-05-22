package etc.fileio.json.app;

import etc.fileio.json.domain.LearningActivity;
import etc.fileio.json.repository.ActivityRepository;

import java.io.IOException;
import java.nio.file.Path;

public class LoadMain {

    public static void main(String[] args) throws IOException {

        Path jsonPath = Path.of("data/activities.json");
        ActivityRepository<LearningActivity> loadedRepo
                = ActivityRepository.loadFromJson(jsonPath);

        System.out.println("CSV 로드 완료: " + loadedRepo.count() + "건");
        loadedRepo.findAll().forEach(a -> System.out.println("- " + a.getActivityType()
                + " | " + a.getTitle() + "(" + a.getMinutes() + "분) ["
                + a.getVisibility().getLabel() + "]" + a.getTags()));




    }

}
