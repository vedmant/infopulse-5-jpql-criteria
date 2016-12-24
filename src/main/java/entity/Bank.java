package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vedmant on 12/24/16.
 */

@Getter
@Setter
@Entity(name = "banks")
public class Bank {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany()
    private List<Client> clients = new ArrayList<Client>();
}
