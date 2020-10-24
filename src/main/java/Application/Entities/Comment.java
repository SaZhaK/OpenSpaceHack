package Application.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    private int commentId;
    private String text;
    private int userId;
    private int bugId;
}
