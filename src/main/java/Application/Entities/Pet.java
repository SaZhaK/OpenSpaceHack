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
    // clothes
    private int faceId;
    private int bodyId;
    private int mouthId;
    private int legId;
    private int hatId;
    private int backpackId;

    public Pet(int ownerId, String petName, int petRank) {
        this.ownerId = ownerId;
        this.petName = petName;
        this.petRank = petRank;
    }

    public Pet(){};
}
