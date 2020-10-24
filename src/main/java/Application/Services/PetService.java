package Application.Services;

import Application.DataBase.PetRepository;
import Application.Entities.Pet;
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

    public Pet getPet(int ownerId) {
        if (ownerId <= 0) {
            logger.info("getPetInfoFromDataBase: ownerId = " + ownerId);
            return null;
        }
        try {
            return petRepository.getPetInfoFromDataBase(ownerId);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getPetInfoFromDataBase: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public void createNewPet(Pet pet) {
        if (pet == null) {
            logger.info("insertNoteIntoPets: pet=null");
            return;
        }
        try {
            petRepository.insertNoteIntoPets(pet);
        } catch (DataAccessException e) {
            String msg = "error in insertNoteIntoPets: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }
}
