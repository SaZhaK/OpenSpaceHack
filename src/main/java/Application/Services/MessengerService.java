package Application.Services;

import Application.DataBase.MessengerRepository;
import Application.Entities.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MessengerService {
    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    MessengerRepository messengerRepository;

    public MessengerService(MessengerRepository messengerRepository) {
        this.messengerRepository = messengerRepository;
    }

    public List<Message> getAllMessages() {
        try {
            return messengerRepository.getAllMessages();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllMessages: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public void writeMessage(Message message) {
        if (message == null) {
            logger.info("writeMessage: msg = null");
            return;
        }
        try {
            messengerRepository.writeMessage(message);
        } catch (DataAccessException e) {
            String msg = "error in writeMessages: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

}

