package auth.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import service.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Visitor implements User {
    private String userName;
    private String password;

    public Visitor(String userName, String passwordHash) {
        this.userName = userName;
        this.password = passwordHash;
    }

    @Override
    public String getUserName() { return this.userName; }

    @Override
    public String getPassword() { return this.password; }

    @Override @JsonProperty("userType")
    public String getUserTypeValue() { return "VISITOR"; }
}

