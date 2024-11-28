package Helper;

public class UserInfo {
    private String UserName;
    private String userId;
    private String email;
    private String Password;

    // Constructor
    public UserInfo(String UserName, String Password, String Email) {
        this.UserName = UserName;
        this.userId = userId;
        this.email = email;
        this.Password = Password;
    }

    // Getters
    public String getUserName() {
        return UserName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    // Setters (if needed)
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    // Optional: toString method for easy debugging
    @Override
    public String toString() {
        return "UserInfo{" +
                "UserName='" + UserName + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
