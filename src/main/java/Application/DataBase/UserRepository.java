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
        return jdbc.query("SELECT * FROM users WHERE username='" + username + "'", this::rowToUser).get(0);
    }

    public void insertNoteIntoUsers(User user) {
        jdbc.update("INSERT INTO users (username, password, role, first_name, second_name, last_name, money)" +
                "VALUES (" + user.getUsername() + ", " + user.getPassword() + ", " +
                user.getRole() + ", " + user.getFirstName() + ", " + user.getSecondName() + ", " +
                user.getLastName() + ", " + user.getMoney() + ")");
    }

    public void getBugsWhichThisUserFound(User user)
        throws SQLException {
        Set<Integer> userBugs = jdbc.query(
                "SELECT * FROM user_to_bugs WHERE user_id=" + user.getUserId() +
                " INNER JOIN bugs ON" + "user_to_bugs.bug_id = bugs.id", this::rowsToSet);
        user.setBugs(userBugs);
    }

    public void addReportedBug2DataBase(Bug bug)
            throws SQLException {
        jdbc.update("INSERT INTO bugs (bugName, description, testedSystem, betaVersion, OSModel, date, time, " +
                "screenshot, status)" + "VALUES ('" + bug.getBugName() + "', '" + bug.getDescription() + "', '" +
                bug.getTestedSystem() + "', '" + bug.getBetaVersion() + "', '" + bug.getOSModel() + "', '" +
                bug.getDate() + "', '" + bug.getTime() + "', '" + Arrays.toString(bug.getScreenshot()) + "', '1')");
        Integer bugId = jdbc.query("SELECT id FROM bugs ORDER BY id DESC LIMIT 1", this::getLastId);
        jdbc.update("INSERT INTO user_to_bugs (user_id, bug_id) VALUES ('" + bug.getUser() + "', '" + bugId + "')");
    }

    public Set<Integer> getAllBugs()
            throws SQLException {
        return getBugsByParam("", "");
    }

    public Set<Integer> getAllCheckedBugs()
            throws SQLException {
        return getCheckedBugs("");
    }

    public Set<Integer> getAllUncheckedBugs()
            throws SQLException {
        return getUncheckedBugs("");
    }

    public Set<Integer> getCheckedBugsByBugName(String bugName)
            throws SQLException {
        return getCheckedBugs("bugName = " + bugName);
    }

    public Set<Integer> getCheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        return getCheckedBugs("testedSystem = " + testedSystem);
    }

    public Set<Integer> getCheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        return getCheckedBugs("betaVersion = " + betaVersion);
    }

    public Set<Integer> getCheckedBugsByOSModel(String OSModel)
            throws SQLException {
        return getCheckedBugs("OSModel = " + OSModel);
    }

    public Set<Integer> getCheckedBugsByDate(String date)
            throws SQLException {
        return getCheckedBugs("date = " + date);
    }

    public Set<Integer> getUncheckedBugsByBugName(String bugName)
            throws SQLException {
        return getUncheckedBugs("bugName = " + bugName);
    }

    public Set<Integer> getUncheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        return getUncheckedBugs("testedSystem = " + testedSystem);
    }

    public Set<Integer> getUncheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        return getUncheckedBugs("betaVersion = " + betaVersion);
    }

    public Set<Integer> getUncheckedBugsByOSModel(String OSModel)
            throws SQLException {
        return getUncheckedBugs("OSModel = " + OSModel);
    }

    public Set<Integer> getUncheckedBugsByDate(String date)
            throws SQLException {
        return getUncheckedBugs("date = " + date);
    }

    private Set<Integer> getUncheckedBugs(String param)
            throws SQLException {
        return getBugsByParam(param, "1");
    }

    private Set<Integer> getCheckedBugs(String param)
            throws SQLException {
        return getBugsByParam(param, "0");
    }

    private Set<Integer> getBugsByParam(String param, String status)
            throws SQLException {

        String sqlQuery = "SELECT * FROM bugs";
        if (!param.isEmpty() && !status.isEmpty())
            sqlQuery += "WHERE " + param + " AND status = " + status;
        else if (param.isEmpty() && !status.isEmpty())
            sqlQuery += "WHERE status = " + status;
        else if (!param.isEmpty())
            sqlQuery += "WHERE " + param;

        return jdbc.query(sqlQuery, this::rowsToSet);
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
        int money = resultSet.getInt("money");
        return new User(
                userId, username, password,
                role, first_name, second_name,
                last_name, money);
    }

    private Set<Integer> rowsToSet(ResultSet resultSet)
            throws SQLException {
        Set<Integer> set = new HashSet<>();
        do {
            set.add(resultSet.getInt("id"));
        } while (resultSet.next());
        return set;
    }

    private int getLastId(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        throw new SQLException();
    }

}
