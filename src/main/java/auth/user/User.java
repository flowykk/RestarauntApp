package auth.user;

public interface User {
    String getUserName();
    String getPassword();
    String getUserTypeValue();
    // Другие методы, связанные с пользовательской функциональностью
}


//public class User {
//    private String userName;
//
//    private String passwordHash;
//
//    private UserType userType;
//
//    public User(String userName, String password, UserType userType) {
//        this.userName = userName;
//        this.passwordHash = password;
//        this.userType = userType;
//    }
//
//    public String getUserName() {
//        return this.userName;
//    }
//
//    public String getPasswordHash() {
//        return this.passwordHash;
//    }
//
//    @JsonProperty("userType")
//    public String getUserTypeValue() {
//        return userType.getValue();
//    }
//
//}
