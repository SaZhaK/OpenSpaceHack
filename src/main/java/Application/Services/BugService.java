package Application.Services;

import Application.DataBase.BugRepository;
import Application.Entities.Bug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public class BugService {

    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    BugRepository bugRepository;

    public Bug getBugById(int bugId) throws SQLException {
        if (bugId <= 0) {
            logger.info("getBugById: bugId <= 0");
            return null;
        }
        try {
            return bugRepository.getBugById(bugId);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getBugById: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public String getUsernameByBug(Bug bug) {
        if (bug == null) {
            logger.info("getUsernameByBug: bug = null");
            return null;
        }
        try {
            return bugRepository.getUsernameByBug(bug);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUsernameByBug: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }
}
