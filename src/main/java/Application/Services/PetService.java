package Application.Services;

import Application.DataBase.PetRepository;
import Application.Entities.Item;
import Application.Entities.Pet;
import Application.Entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.logging.Level;

@Service
public class PetService {
    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    PetRepository petRepository;

    public Pet getUserPet(User user) {
        if (user == null) {
            logger.info("getUserPet: user = null");
            return null;
        }
        try {
            return petRepository.getUserPet(user);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getUserPet: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public void createNewPet(Pet pet) {
        if (pet == null) {
            logger.info("createNewPet: pet=null");
            return;
        }
        try {
            petRepository.createNewPet(pet);
        } catch (DataAccessException e) {
            String msg = "error in createNewPet: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

    public void wearItem(Pet pet, Item item) {
        if (pet == null) {
            logger.info("wearItem: pet = null");
            return;
        }
        if (item == null) {
            logger.info("wearItem: item = null");
            return;
        }
        try {
            petRepository.wearItem(pet, item);
        } catch (DataAccessException e) {
            String msg = "error in wearItem: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }
}
