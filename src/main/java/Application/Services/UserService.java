package Application.Services;

import Application.DataBase.UserRepository;
import Application.Entities.Bug;
import Application.Entities.User;

import java.util.Set;
import java.util.logging.*;

import Application.JWT.JwtProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username) {
        if (username == null) {
            logger.info("getUserInfoFromDataBase: username = null");
            return null;
        }
        try {
            return userRepository.getUserInfoFromDataBase(username);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUserInfoFromDataBase: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public void createNewUser(User user) {
        if (user == null) {
            logger.info("insertNoteIntoUsers: user = null");
            return;
        }
        try {
            userRepository.insertNoteIntoUsers(user);
        } catch (DataAccessException e) {
            String msg = "error in insertNoteIntoUsers: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

    public void getUserFoundBugs(User user) {
        if (user == null) {
            logger.info("getBugsWhichThisUserFound: user = null");
            return;
        }

        try {
            userRepository.getBugsWhichThisUserFound(user);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getBugsWhichThisUserFound: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

    public void addReportedBug2DataBase(Bug bug) {
        if (bug == null) {
            logger.info("addReportedBug2DataBase: bug = null");
            return;
        }
        try {
            userRepository.addReportedBug2DataBase(bug);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in addReportedBug2DataBase: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

    public Set<Integer> getAllBugs()
            throws SQLException {
        try {
            return userRepository.getAllBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getAllCheckedBugs()
            throws SQLException {
        try {
            return userRepository.getAllCheckedBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllCheckedBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getAllUncheckedBugs()
            throws SQLException {
        try {
            return userRepository.getAllUncheckedBugs();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllUncheckedBugs: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByBugName(String bugName)
            throws SQLException {
        try {
            return userRepository.getCheckedBugsByBugName(bugName);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByBugName: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        try {
            return userRepository.getCheckedBugsByTestedSystem(testedSystem);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByTestedSystem: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        try {
            return userRepository.getCheckedBugsByBetaVersion(betaVersion);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByBetaVersion: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByOSModel(String OSModel)
            throws SQLException {
        try {
            return userRepository.getCheckedBugsByOSModel(OSModel);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getCheckedBugsByOSModel: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getCheckedBugsByDate(String date)
            throws SQLException {
        try {
            return userRepository.getCheckedBugsByDate(date);
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
            return userRepository.getUncheckedBugsByBugName(bugName);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByBugName: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByTestedSystem(String testedSystem)
            throws SQLException {
        try {
            return userRepository.getUncheckedBugsByTestedSystem(testedSystem);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByTestedSystem: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByBetaVersion(String betaVersion)
            throws SQLException {
        try {
            return userRepository.getUncheckedBugsByBetaVersion(betaVersion);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByBetaVersion: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByOSModel(String OSModel)
            throws SQLException {
        try {
            return userRepository.getUncheckedBugsByOSModel(OSModel);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByOSModel: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public Set<Integer> getUncheckedBugsByDate(String date)
            throws SQLException {
        try {
            return userRepository.getUncheckedBugsByDate(date);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUncheckedBugsByDate: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

}
