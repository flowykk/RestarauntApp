package auth.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Admin implements User {
    private String userName;
    private String password;

    public Admin(String userName, String passwordHash) {
        this.userName = userName;
        this.password = passwordHash;
    }

    @Override
    public String getUserName() { return this.userName; }

    @Override
    public String getPassword() { return this.password; }

    @Override @JsonProperty("userType")
    public String getUserTypeValue() { return "ADMIN"; }
}
