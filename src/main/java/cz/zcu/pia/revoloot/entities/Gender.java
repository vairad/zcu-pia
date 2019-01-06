package cz.zcu.pia.revoloot.entities;

public enum Gender {
    MALE,
    FEMALE;

    boolean isMALE(){
        return  this == MALE;
    }

    boolean isFEMALE(){
        return this == FEMALE;
    }
}
