package oop.inheritance.domain;

public class LectureLog extends LearningActivity {

    public String instructorName; // 강사 이름 (LectureLog만 가지는 고유한 필드)

    public LectureLog(String title, int minutes, boolean publicActivity, String instructorName) {
        // 상속 관계 하에서 자식 객체가 생성될 때 부모의 객체도 함께 생성됩니다. (그래야 필드, 메서드를 물려줄 수 있으니까)
        // 그래서 생성자에는 항상 super()가 내장되어 있습니다.
        super(title, minutes, publicActivity);
        this.instructorName = normalizeInstructorName(instructorName);
    }

    // 메서드 재정의 (override)
    // 부모가 물려준 메서드가 자식에게 맞지 않거나 부족할 경우 자식이 재 정의해서 사용할 수 있습니다.
    // 오버라이딩 규칙: 1. 메서드 이름이 같아야 한다, 2. 리턴 타입이 같아야 한다, 3. 매개변수의 선언이 일치해야 한다.
    @Override
    public void printSummary() {
        System.out.println("[강의] #" + getId() + " " + getTitle() + " - " + getMinutes()
                + "분 - 강사: " + instructorName);
    }

    @Override
    public boolean needsReview() {
        return getMinutes() < 60;
    }

    public void method1() {
        System.out.println("자식의 고유 메서드 호출!");
    }

    // 이 메서드는 부모가 물려준 게 아니라 자식 고유의 기능 (instructorName은 부모가 처리해줄 수 없다.)
    private String normalizeInstructorName(String instructorName) {
        if (instructorName == null || instructorName.isBlank()) {
            return "강사 미정";
        }

        return instructorName;
    }



}






