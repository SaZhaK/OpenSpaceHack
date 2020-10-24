package Application.Services;

import Application.DataBase.ItemRepository;
import Application.Entities.Item;
import Application.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ItemService {
    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    ItemRepository itemRepository;

    public List<Integer> getUserItems(User user) {
        if (user == null) {
            logger.info("getUserItems: user = null");
            return null;
        }
        try {
            return itemRepository.getUserItems(user);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUserItems: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public List<Item> getAllItems() {
        try {
            return itemRepository.getAllItems();
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllItems: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public List<Item> getItemsByType(String type) {
        try {
            return itemRepository.getItemsByType(type);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getItemsByType: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }
}
