package cz.zcu.pia.revoloot.entities;

public enum Gender {
    MALE,
    FEMALE;

    public boolean isMALE(){
        return  this == MALE;
    }

    public boolean isFEMALE(){
        return this == FEMALE;
    }

    public static Gender fromString(String gender){
        if(gender.toLowerCase().equals("male")){
            return MALE;
        }
        if(gender.toLowerCase().equals("female")){
            return FEMALE;
        }
        throw new IllegalArgumentException("Unsuported gender: "+gender);
    }
}
