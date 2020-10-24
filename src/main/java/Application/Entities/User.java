package Application.Entities;

import java.util.Set;

public class User {
    private int userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String secondName;
    private String lastName;
    private Set<Integer> bugs;

    public User(int userId,
                String username,
                String password,
                String role,
                String firstName,
                String secondName,
                String lastName)
    {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBugs(Set<Integer> bugs) {
        this.bugs = bugs;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<Integer> getBugs() {
        return bugs;
    }
}
