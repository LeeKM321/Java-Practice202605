package oop.enum_exception.exception.custom;

// extends Exception -> checked Exception: try/catch를 강제합니다.
// extends RuntimeException -> unchecked Exception: 예외처리를 강제하지 않음. (실무 선호)

/**
 * @author 이경민
 * @since 2026.05.11 ~
 * @version 1.0.0
 *
 * @apiNote
 * LoginInvalidException은 로그인 실패 시 발생할 수 있는 예외입니다.
 * id 없음, 비밀번호 틀림을 모두 이 예외 타입으로 선언합시다.
 */

public class LoginInvalidException extends RuntimeException {

    // 나중에 예외 상황에 대한 추가 정보를 담기 위한 필드
    private String inputAccount;

    public LoginInvalidException(String message, String inputAccount) {
        super(message);
        this.inputAccount = inputAccount;
    }

    public String getInputAccount() {
        return inputAccount;
    }
}
