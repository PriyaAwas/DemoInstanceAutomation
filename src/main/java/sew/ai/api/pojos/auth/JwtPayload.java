package sew.ai.api.pojos.auth;

public class JwtPayload {
    String username;
    String password;
    Boolean isTfaEnable;

    public JwtPayload(String userName, String password, Boolean isTfaEnable) {
        setUsername(userName);
        setPassword(password);
        setIsTfaEnable(isTfaEnable);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsTfaEnable() {
        return isTfaEnable;
    }

    public void setIsTfaEnable(Boolean tfaEnable) {
        isTfaEnable = tfaEnable;
    }
}
