package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity inheritance example
 */
@Entity(name = "bad_users")
@DiscriminatorValue(value = "bad_user")
public class BadUser extends User {

    private String debt;

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }
}
