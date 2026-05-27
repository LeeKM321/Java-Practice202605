package etc.singleton;

// Singleton: 프로그램에서 객체는 단 하나임을 보장하고, 외부에서 객체를 임의로 생성하지 못하게 막는 코드 디자인 패턴
public class Singleton {

    // 1. 생성자를 단 하나만 생성하고, 접근 제한을 private으로 지정
    private Singleton() {
    }

    // 2. 생성자를 호출할 수 있는 곳은 같은 클래스 내부 뿐이므로
    // 스스로의 객체를 단 하나만 생성
    private static Singleton s = new Singleton();

    // 3. 외부에서 객체 요구할 시 미리 만들어 놓은 단 하나의 객체 주소값을 리턴하는 메서드를 제작
    public static Singleton getInstance() {
        if (s == null) {
            s = new Singleton();
        }
        return s;
    }

    public void method1() {
        System.out.println("아주 중요한 메서드");
    }
}
