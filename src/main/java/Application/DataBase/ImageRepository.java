package Application.DataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@Repository
public class ImageRepository {

    @Autowired
    private final JdbcTemplate jdbc;

    public ImageRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void addImage(String image) {
        jdbc.update("INSERT INTO images (image) VALUES ( '" + image + "' )");
    }

    public String getImage() {
        return jdbc.query("SELECT * FROM images", this::handler).get(0);
    }

    public String handler(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("image");
    }
}
