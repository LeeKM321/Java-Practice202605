package oop.access_modifier;

public class LearningLog {

    // 접근 제한자를 활용한 정보 은닉과 캡슐화 (information hiding, encapsulation)
    // 1. 필드에 private 접근 제한을 붙여 외부에서의 직접적인 접근을 허용하지 않게 막습니다.
    private String title;
    private int minutes;
    private boolean publicLog;

    LearningLog() {
        System.out.println("기본 생성자 호출!");
    }

    LearningLog(String title, int minutes) {
        this(title, minutes, true);
    }

    LearningLog(String title, int minutes, boolean publicLog) {
        this.title = normalizeTitle(title);
        this.minutes = minutes;
        this.publicLog = publicLog;
    }

    // 2. private 접근 제한을 지정하니까, 제대로 된 값도 수정이 불가능한 것을 확인했습니다.
    // 필드 값을 대신 받아서 할당하고, 값을 돌려줄 수 있는 메서드를 활용해서 값을 보호하겠습니다.
    // 이 때 사용하는 메서드의 이름을 getter, setter라고 합니다.
    // set + 필드이름으로 짓는 것이 관례.

    public void extendStudy(int additionalMinutes) {
        if (additionalMinutes <= 0) {
            System.out.println("잘못된 공부 시간입니다.");
            return; // void 메서드에서 return은 메서드를 강제 종료합니다.
        }

        this.minutes += additionalMinutes;
    }

    public void changeTitle(String newTitle) {
        this.title = normalizeTitle(newTitle);
    }

    // 이 메서드는 굳이 외부에서 알 필요가 없고, 호출할 일도 없기 때문에 private으로 설정해서
    // 이 클래스 안에서만 사용할 수 있도록 범위를 지정
    private String normalizeTitle(String newTitle) {
        if (newTitle == null || newTitle.isBlank()) {
            return "제목 없음";
        }

        return newTitle;
    }

    public void openToPublic() {
        this.publicLog = true;
    }

    public void hideFromPublic() {
        this.publicLog = false;
    }


    void printSummary() {
        // 3항 연산식: 조건식의 결과에 따라 변수에 대입할 값을 다르게 할 수 있는 문법
        // 논리형 조건식 ? 좌항 : 우항
        // 논리형 조건식이 true라면 좌항의 값이, false라면 우항의 값이 도출됩니다.
        String visibility = publicLog ? "공개" : "비공개";
        System.out.println(this.title + " - " + this.minutes + "분 - " + visibility);
    }

    boolean needsReview() {
        return minutes < 60;
    }

    // 외부로 필드값을 돌려주는 getter 메서드
    // get + 필드이름으로 지어주는 것이 관례. boolean 타입의 값을 돌려주는 getter는 is로 시작하도록 이름을 짓습니다.
    public String getTitle() {
        return title;
    }

    public int getMinutes() {
        return minutes;
    }

    public boolean isPublicLog() {
        return publicLog;
    }
}
