package Application.Entities;

public class Pet {
    private int petId;
    private int ownerId;
    private String petName;
    private int petRank;
    private int hatId;
    private int jacketId;

    public Pet(int petId, int ownerId, String petName, int petRank, int hatId, int jacketId) {
        this.petId = petId;
        this.ownerId = ownerId;
        this.petName = petName;
        this.petRank = petRank;
        this.hatId = hatId;
        this.jacketId = jacketId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetRank() {
        return petRank;
    }

    public void setPetRank(int petRank) {
        this.petRank = petRank;
    }

    public int getHatId() {
        return hatId;
    }

    public void setHatId(int hatId) {
        this.hatId = hatId;
    }

    public int getJacketId() {
        return jacketId;
    }

    public void setJacketId(int jacketId) {
        this.jacketId = jacketId;
    }
}
