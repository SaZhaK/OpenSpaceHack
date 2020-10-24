package Application.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Item {
    private int itemId;
    private String type;
    private String link;
    private int cost;
}
