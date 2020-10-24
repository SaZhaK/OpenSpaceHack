package Application.DataBase;

import Application.Services.UserService;
import Application.Entities.Bug;
import Application.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
public class BugRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    BugRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Bug getBugById(int bugId) throws SQLException {
        return jdbc.query("SELECT * FROM bugs WHERE id = " + bugId, this::rowToUser);
    }

    public String getUsernameByBug(Bug bug)
            throws SQLException {
        return jdbc.query("SELECT * FROM user_to_bugs WHERE bug_id=" + bug.getBugId() +
                        " INNER JOIN users ON" + "user_to_bugs.user_id = users.id", this::rowsToUsername);
    }


    private String rowsToUsername(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString("username");
        }
        return null;
    }

    private Bug rowToUser(ResultSet resultSet) throws SQLException {
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
            bug.setUser(new UserService().getUserByUsername(getUsernameByBug(bug)));
            bug.setDate(resultSet.getDate("date"));
            bug.setTime(resultSet.getTime("time").toLocalTime());

            return bug;
        }
        return null;
    }
}
