package Application.Services;

import Application.DataBase.UserRepository;
import Application.Entities.User;

import java.util.Set;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    static Logger LOGGER;

    @Autowired
    UserRepository userRepository;

    public User getUser(String username) {
        if (username == null) {
            LOGGER.log(Level.INFO, "getUserInfoFromDataBase: username = null");
            return null;
        }
        try {
            return userRepository.getUserInfoFromDataBase(username);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUserInfoFromDataBase: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
        return null;
    }

    public void createNewUser(User user) {
        if (user == null) {
            LOGGER.log(Level.INFO, "insertNoteIntoUsers: user = null");
            return;
        }
        try {
            userRepository.insertNoteIntoUsers(user);
        } catch (DataAccessException e) {
            String msg = "error in insertNoteIntoUsers: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
    }

    public void getUserFoundBugs(User user) {
        if (user == null) {
            LOGGER.log(Level.INFO, "getBugsWhichThisUserFound: user = null");
            return;
        }

        try {
            userRepository.getBugsWhichThisUserFound(user);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getBugsWhichThisUserFound: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
    }

    public Set<Integer> getNotProccessedBugs() {
        try {
            return userRepository.getBugsWhichWereNotProcessed();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getBugsWhichWereNotProcessed: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
        return null;
    }

}
