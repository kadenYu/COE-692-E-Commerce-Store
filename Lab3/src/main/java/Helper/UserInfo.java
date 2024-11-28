package Helper;

public class UserInfo {
    private String userName;
    private String userId;
    private String email;
    private String password;

    // Constructor
    public UserInfo(String userName, String userId, String email, String password) {
        this.userName = userName;
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    // Getters
    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters (if needed)
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Optional: toString method for easy debugging
    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
