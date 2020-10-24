package Application.DataBase;

import Application.Entities.Pet;

import Application.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class PetRepository {
    private final JdbcTemplate jdbc;

    @Autowired

    public PetRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Pet getUserPet(User user)
            throws SQLException {
        return jdbc.query("SELECT * FROM pets WHERE owner = " + user.getUserId(), this::rowToPet);
    }

    public void createNewPet(Pet pet) {
        jdbc.update("INSERT INTO pets (owner, name, rank, face, mouth, body, arm, leg, hat, backpack)" +
                "VALUES (" + pet.getOwnerId() + ", '" + pet.getPetName() + "', " +
                pet.getPetRank() + ", " + pet.getFaceId() + ", " + pet.getMouthId() + ", " +
                pet.getBodyId() + ", " + pet.getArmId() + ", " + pet.getLegId() + ", " +
                pet.getHatId() + ", " + pet.getBackpackId() + ")");
    }

    private Pet rowToPet(ResultSet resultSet)
            throws SQLException {
        Pet pet = new Pet();
        if (resultSet.next()) {
            pet.setPetId(resultSet.getInt("id"));
            pet.setPetName(resultSet.getString("name"));
            pet.setPetRank(resultSet.getInt("rank"));
            pet.setOwnerId(resultSet.getInt("owner"));
            pet.setHatId(resultSet.getInt("hat"));
            pet.setFaceId(resultSet.getInt("face"));
            pet.setMouthId(resultSet.getInt("mouth"));
            pet.setBodyId(resultSet.getInt("body"));
            pet.setArmId(resultSet.getInt("arm"));
            pet.setLegId(resultSet.getInt("leg"));
            pet.setBackpackId(resultSet.getInt("backpack"));
        }
        return pet;
    }
}
