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
}
