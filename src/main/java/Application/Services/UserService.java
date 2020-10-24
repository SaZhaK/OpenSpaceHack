package Application.Services;

import Application.DataBase.UserRepository;
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

    public Set<Integer> getNotProcessedBugs() {
        try {
            return userRepository.getBugsWhichWereNotProcessed();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getBugsWhichWereNotProcessed: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

}
