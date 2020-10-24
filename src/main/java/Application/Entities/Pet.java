package Application.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {
    private int petId;
    private int ownerId;
    private String petName;
    private int petRank;
    private int hatId;
    private int jacketId;

    public Pet(int ownerId, String petName, int petRank) {
        this.ownerId = ownerId;
        this.petName = petName;
        this.petRank = petRank;
    }
}
