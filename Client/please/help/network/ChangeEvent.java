package please.help.network;

import please.help.organizationBuilding.Organization;

import java.io.Serializable;
import java.util.Objects;

public class ChangeEvent implements Serializable {

    private static final long serialVersionUID = 20210412L;
    private final Organization org;
    private final Type type;
    private final int hash;

    public ChangeEvent(Organization org, Type type){
        this.org = org;
        this.type = type;
        this.hash = hashCode();
    }

    public enum Type{
        ADDED, DELETED, UPDATED
    }

    public Organization getOrg() {
        return org;
    }

    public Type getType() {
        return type;
    }

    public int getHash() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeEvent that = (ChangeEvent) o;
        return hash == that.hash;
    }

    @Override
    public int hashCode() {
        return Objects.hash(org, type);
    }
}
