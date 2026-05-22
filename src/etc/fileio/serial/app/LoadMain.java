package etc.fileio.serial.app;

import etc.fileio.serial.domain.LearningActivity;
import etc.fileio.serial.repository.ActivityRepository;

import java.io.IOException;
import java.nio.file.Path;

public class LoadMain {

    public static void main(String[] args) throws IOException {

        Path csvPath = Path.of("data/activities.csv");
        ActivityRepository<LearningActivity> loadedRepo
                = ActivityRepository.loadFromFile(csvPath);

        System.out.println("CSV 로드 완료: " + loadedRepo.count() + "건");
        loadedRepo.findAll().forEach(a -> System.out.println("- " + a.getActivityType()
                + " | " + a.getTitle() + "(" + a.getMinutes() + "분) ["
                + a.getVisibility().getLabel() + "]" + a.getTags()));




    }

}
