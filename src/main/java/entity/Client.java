package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by vedmant on 12/24/16.
 */
@Getter
@Setter
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "percents")
    private Double percents;

    @Column(name = "deposit")
    private Double deposit;

    @Formula(value = "deposit * percents")
    private Double totalSum;

}
