package Application.Services;

import Application.DataBase.BugRepository;
import Application.Entities.Bug;
import Application.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;
import java.util.Set;

public class BugService {

    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    BugRepository bugRepository;

    public Bug getBugById(int bugId) {
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

    public User getUserByBug(Bug bug) {
        if (bug == null) {
            logger.info("getUsernameByBug: bug = null");
            return null;
        }
        try {
            return bugRepository.getUserByBug(bug);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUsernameByBug: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getAllBugs()
            throws SQLException {
        try {
            return bugRepository.getAllBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getAllCheckedBugs()
            throws SQLException {
        try {
            return bugRepository.getAllCheckedBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllCheckedBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getAllUncheckedBugs()
            throws SQLException {
        try {
            return bugRepository.getAllUncheckedBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllUncheckedBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByBugName(String bugName)
            throws SQLException {
        try {
            return bugRepository.getCheckedBugsByBugName(bugName);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByBugName: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        try {
            return bugRepository.getCheckedBugsByTestedSystem(testedSystem);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByTestedSystem: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        try {
            return bugRepository.getCheckedBugsByBetaVersion(betaVersion);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByBetaVersion: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByOSModel(String OSModel)
            throws SQLException {
        try {
            return bugRepository.getCheckedBugsByOSModel(OSModel);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByOSModel: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByDate(String date)
            throws SQLException {
        try {
            return bugRepository.getCheckedBugsByDate(date);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByDate: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    //
    public Set<Integer> getUncheckedBugsByBugName(String bugName)
            throws SQLException {
        try {
            return bugRepository.getUncheckedBugsByBugName(bugName);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByBugName: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        try {
            return bugRepository.getUncheckedBugsByTestedSystem(testedSystem);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByTestedSystem: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        try {
            return bugRepository.getUncheckedBugsByBetaVersion(betaVersion);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByBetaVersion: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByOSModel(String OSModel)
            throws SQLException {
        try {
            return bugRepository.getUncheckedBugsByOSModel(OSModel);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByOSModel: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByDate(String date)
            throws SQLException {
        try {
            return bugRepository.getUncheckedBugsByDate(date);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByDate: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }
}
