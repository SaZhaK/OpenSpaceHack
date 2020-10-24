package Application.DataBase;

import Application.Entities.Pet;

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

    public Pet getPetInfoFromDataBase(int ownerId)
            throws SQLException
    {
        return jdbc.query("SELECT * FROM pets WHERE owner='" + ownerId + "'", this::rowToPet);
    }

    public void insertNoteIntoPets(Pet pet) {
        jdbc.update("INSERT INTO pets (id, owner, name, rank, hat, jacket) VALUES ("+
                pet.getPetId() + ", " + pet.getOwnerId() + ", " + pet.getPetName() + ", " +
                pet.getPetRank() + ", " + pet.getHatId() + ", " + pet.getJacketId() + ")");
    }

    private Pet rowToPet(ResultSet resultSet)
            throws SQLException
    {
        int id = resultSet.getInt("id");
        int owner = resultSet.getInt("owner");
        String name = resultSet.getString("name");
        int rank = resultSet.getInt("rank");
        int hat = resultSet.getInt("hat");
        int jacket = resultSet.getInt("jacket");

        return new Pet(id, owner, name, rank, hat, jacket);
    }
}
