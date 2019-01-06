package cz.zcu.pia.revoloot.entities;

public enum State {
    CZ,
    SVK;

    public boolean isCZ() {
        return this == CZ;
    }

    public boolean isSVK() {
        return this == SVK;
    }

    public static State fromString(String state){
        if(state.toLowerCase().equals("cz")){
            return CZ;
        }
        if(state.toLowerCase().equals("svk")){
            return SVK;
        }
        throw new IllegalArgumentException("Unsupported country");
    }
}
