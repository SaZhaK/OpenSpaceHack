package Application.Services;

import Application.DataBase.CommentRepository;
import Application.Entities.Bug;
import Application.Entities.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentService {
    private static Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsByBugId(Bug bug) {
        if (bug == null) {
            logger.info("getAllCommentsByBugId: bug = null");
            return null;
        }
        try {
            return commentRepository.getAllCommentsByBugId(bug);
        } catch (DataAccessException | SQLException e) {
            String msg = "error in getAllMessages: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
        return null;
    }

    public void writeComment(Comment comment) {
        if (comment == null) {
            logger.info("writeComment: msg = null");
            return;
        }
        try {
            commentRepository.writeComment(comment);
        } catch (DataAccessException e) {
            String msg = "error in writeComment: " + e.getMessage() + "\ncaused by: " + e.getCause();
            logger.info(msg);
        }
    }

}

