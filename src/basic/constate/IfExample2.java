package basic.constate;

public class IfExample2 {

    public static void main(String[] args) {

        int age = 25;

        // 다중 분기 조건문
        if (age >= 20) {
            // 중첩 if문
            if (age < 40) {
                System.out.println("청년층 입니다.");
            } else if (age < 65) {
                System.out.println("중장년층 입니다.");
            } else {
                System.out.println("노년층 입니다.");
            }
            // else if문
        } else if (age >= 17) {
            System.out.println("고등학생 입니다.");
        } else if (age >= 14) {
            System.out.println("중학생 입니다.");
        } else if (age >= 8) {
            System.out.println("초등학생입니다.");
        } else {
            System.out.println("미취학 아동입니다.");
        }

    }

}
