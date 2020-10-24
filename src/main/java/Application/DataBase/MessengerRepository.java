package Application.DataBase;

import Application.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessengerRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public MessengerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Message> getAllMessages() throws SQLException {
        return jdbc.query("SELECT * FROM messages", this::rowsToArray);
    }

    public void writeMessage(Message message) {
        jdbc.update("INSERT INTO messages (user_id, text) VALUES ('" +
                message.getUserId() + "', '" + message.getText() + "')");
    }

    private List<Message> rowsToArray(ResultSet resultSet) throws SQLException {
        List<Message> messages = new ArrayList<>();
        do {
            Message msg = new Message();
            msg.setMessageId(resultSet.getInt("id"));
            msg.setUserId(resultSet.getInt("user_id"));
            msg.setText(resultSet.getString("text"));
            msg.setTime(resultSet.getTime("time").toLocalTime());

            messages.add(msg);
        } while (resultSet.next());
        return messages;
    }
}
