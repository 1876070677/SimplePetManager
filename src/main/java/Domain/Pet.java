package Domain;

public class Pet {
    private String name;
    private String birth;
    private String kind;
    private String ownerName;
    private String ownerPhone;

    public Pet(String name, String birth, String kind, String ownerName, String ownerPhone) {
        this.name = name;
        this.birth = birth;
        this.kind = kind;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }

    public String getBirth() {
        return birth;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }
}
