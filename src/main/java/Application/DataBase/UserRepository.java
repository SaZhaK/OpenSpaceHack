package Application.DataBase;

import Application.Entities.Bug;
import Application.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public User getUserInfoFromDataBase(String username) throws SQLException {
        return jdbc.query("SELECT * FROM users WHERE username='" + username + "'", this::rowToUser);
    }

    public void insertNoteIntoUsers(User user) {
        jdbc.update("INSERT INTO users (username, password, role, first_name, second_name, last_name, money)" +
                "VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" +
                user.getRole() + "', '" + user.getFirstName() + "', '" + user.getSecondName() + "', '" +
                user.getLastName() + "', " + user.getMoney() + ")");
    }

    public void getBugsWhichThisUserFound(User user)
        throws SQLException {
        Set<Integer> userBugs = jdbc.query(
                "SELECT * FROM user_to_bugs WHERE user_id = " + user.getUserId(), this::rowsToSet);
        user.setBugs(userBugs);
    }

    public void addReportedBug2DataBase(Bug bug)
            throws SQLException {
        jdbc.update("INSERT INTO bugs (bugName, description, testedSystem, betaVersion, OSModel, date, time, " +
                "screenshot, status)" + "VALUES ('" + bug.getBugName() + "', '" + bug.getDescription() + "', '" +
                bug.getTestedSystem() + "', '" + bug.getBetaVersion() + "', '" + bug.getOSModel() + "', '" +
                bug.getDate() + "', '" + bug.getTime() + "', '" + Arrays.toString(bug.getScreenshot()) + "', 1)");
        Integer bugId = jdbc.query("SELECT id FROM bugs ORDER BY id DESC LIMIT 1", this::getLastId);
        jdbc.update("INSERT INTO user_to_bugs (user_id, bug_id) VALUES (" + bug.getUser().getUserId() + ", " + bugId + ")");
    }

    public void updateUserWallet(User user) {
        jdbc.update("UPDATE users SET money = " + user.getMoney() + " WHERE id = " + user.getUserId());
    }

    private User rowToUser(ResultSet resultSet)
            throws SQLException {
        if (resultSet.next()) {
            int userId = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String role = resultSet.getString("role");
            String first_name = resultSet.getString("first_name");
            String second_name = resultSet.getString("second_name");
            String last_name = resultSet.getString("last_name");
            int money = resultSet.getInt("money");
            return new User(
                    userId, username, password,
                    role, first_name, second_name,
                    last_name, money);
        }
        return new User();
    }

    private Set<Integer> rowsToSet(ResultSet resultSet)
            throws SQLException {
        Set<Integer> set = new HashSet<>();
        do {
            set.add(resultSet.getInt("bug_id"));
        } while (resultSet.next());
        return set;
    }

    private Integer getLastId(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }

}
