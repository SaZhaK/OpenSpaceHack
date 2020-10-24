package Application.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class User {
    private static final int moneyPerBug = 100;

    private int userId;
    private String username;
    private String password;
    private String role;
    private String firstName;
    private String secondName;
    private String lastName;
    private Set<Integer> bugs;
    private int money;

    public User(int userId,
                String username,
                String password,
                String role,
                String firstName,
                String secondName,
                String lastName,
                int money) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.money = money;
    }

    public void addMoney() {
        this.money += moneyPerBug;
    }

    public void spendMoney(int price) {
        this.money -= price;
    }
}
