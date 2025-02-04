package woowacourse.auth.application.dto;

public class LoginServiceRequest {

    private final String email;
    private final String password;

    public LoginServiceRequest(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
