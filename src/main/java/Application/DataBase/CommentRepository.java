package Application.DataBase;

import Application.Entities.Bug;
import Application.Entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public CommentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Comment> getAllCommentsByBugId(Bug bug) throws SQLException {
        return jdbc.query("SELECT * FROM comments WHERE bug_id = " + bug.getBugId(), this::rowsToArray);
    }

    public void writeComment(Comment comment) {
        jdbc.update("INSERT INTO comments (user_id, bug_id, text) VALUES (" +
                comment.getUserId() + ", " + comment.getBugId() + ", '" + comment.getText() + "')");
    }

    private List<Comment> rowsToArray(ResultSet resultSet) throws SQLException {
        List<Comment> comments = new ArrayList<>();
        do {
            Comment comment = new Comment();
            comment.setCommentId(resultSet.getInt("id"));
            comment.setUserId(resultSet.getInt("user_id"));
            comment.setBugId(resultSet.getInt("bug_id"));
            comment.setText(resultSet.getString("text"));

            comments.add(comment);
        } while (resultSet.next());
        return comments;
    }
}
