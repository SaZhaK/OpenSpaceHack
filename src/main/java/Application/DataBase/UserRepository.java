package Application.DataBase;

import Application.Entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return jdbc.query("SELECT * FROM users WHERE username='" + username + "'", this::rowToUser).get(0);
    }

    public void insertNoteIntoUsers(User user) {
        jdbc.update("INSERT INTO users (id, username, password, role, first_name, second_name, last_name) VALUES ("+
                user.getUserId() + ", " + user.getUsername() + ", " + user.getPassword() + ", " +
                user.getRole() + ", " + user.getFirstName() + ", " + user.getSecondName() + ", " +
                user.getLastName() + ")");
    }

    public void getBugsWhichThisUserFound(User user)
        throws SQLException {
        Set<Integer> userBugs = jdbc.query(
                "SELECT * FROM user_to_bugs WHERE user_id=" + user.getUserId() +
                " INNER JOIN bugs ON" + "user_to_bugs.bug_id = bugs.id", this::rowsToSet);
        user.setBugs(userBugs);
    }

    public Set<Integer> getBugsWhichWereNotProcessed()
        throws SQLException {
        return jdbc.query("SELECT * FROM bugs WHERE status=1", this::rowsToSet);
    }

    private User rowToUser(ResultSet resultSet, int rowNum)
            throws SQLException {
        int userId = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        String first_name = resultSet.getString("first_name");
        String second_name = resultSet.getString("second_name");
        String last_name = resultSet.getString("last_name");
        return new User(
                userId, username, password,
                role, first_name, second_name,
                last_name);
    }

    private Set<Integer> rowsToSet(ResultSet resultSet)
            throws SQLException {
        Set<Integer> set = new HashSet<>();
        do {
            set.add(resultSet.getInt("id"));
        } while (resultSet.next());
        return set;
    }

}
