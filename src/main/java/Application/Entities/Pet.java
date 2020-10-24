package Application.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pet {
    private int petId;
    private int ownerId;
    private String petName;
    private int petRank;
    private int hatId;
    private int jacketId;
}
