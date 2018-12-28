package cz.zcu.pia.revoloot.entities;

import javax.persistence.*;

@Entity
@Table(name = TableConfig.TABLE_BANKERS)
@PrimaryKeyJoinColumn(name="user")
public class Banker extends User {

    String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
