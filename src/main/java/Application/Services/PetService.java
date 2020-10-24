package Application.Services;

import Application.DataBase.PetRepository;
import Application.Entities.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PetService {
    static Logger LOGGER;

    @Autowired
    PetRepository petRepository;

    public Pet getPetInfoFromDataBase(int ownerId) {
        if (ownerId <= 0) {
            LOGGER.log(Level.INFO, "getPetInfoFromDataBase: ownerId = " + ownerId);
            return null;
        }
        try {
            return petRepository.getPetInfoFromDataBase(ownerId);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getPetInfoFromDataBase: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
        return null;
    }

    public void insertNoteIntoPets(Pet pet) {
        if (pet == null) {
            LOGGER.log(Level.INFO, "insertNoteIntoPets: pet=null");
            return;
        }
        try {
            petRepository.insertNoteIntoPets(pet);
        } catch (DataAccessException e) {
            String msg = "error in insertNoteIntoPets: " + e.getMessage() + "\ncaused by: " + e.getCause();
            LOGGER.log(Level.INFO, msg);
        }
    }
}
