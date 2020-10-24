package Application.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private int messageId;
    private String text;
    private int userId;
    private LocalTime time;
}
