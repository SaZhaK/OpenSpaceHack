package Application.DataBase;

import Application.Services.UserService;
import Application.Entities.Bug;
import Application.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Repository
public class BugRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    BugRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Bug getBugById(int bugId) throws SQLException {
        return jdbc.query("SELECT * FROM bugs WHERE id = " + bugId, this::rowToBug);
    }

    public User getUserByBug(Bug bug)
            throws SQLException {
        return jdbc.query("SELECT * FROM user_to_bugs WHERE bug_id=" + bug.getBugId(), this::rowsToUser);
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

    private User rowsToUser(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new UserService().getUserByUsername(resultSet.getString("username"));
        }
        return null;
    }

    private Bug rowToBug(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            String[] tmp = resultSet.getString("screenshot").split(", ");
            byte[] img = new byte[tmp.length];
            int idx = 0;
            for (String imgByte : tmp)
                img[idx++] = Byte.parseByte(imgByte);

            Bug bug = new Bug();
            bug.setBugId(resultSet.getInt("id"));
            bug.setBetaVersion(resultSet.getString("betaVersion"));
            bug.setBugName(resultSet.getString("bugName"));
            bug.setDescription(resultSet.getString("description"));
            bug.setOSModel(resultSet.getString("OSModel"));
            bug.setTestedSystem(resultSet.getString("testedSystem"));
            bug.setScreenshot(img);
            bug.setUser(getUserByBug(bug));
            bug.setDate(resultSet.getDate("date"));
            bug.setTime(resultSet.getTime("time").toLocalTime());
            bug.setStatus(resultSet.getInt("status"));

            return bug;
        }
        return null;
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
