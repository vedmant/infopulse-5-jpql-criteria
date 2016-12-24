package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity inheritance example
 */
@Entity(name = "admins")
@DiscriminatorValue(value = "admin")
public class Admin extends User {

}
