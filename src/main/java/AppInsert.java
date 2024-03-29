import entity.Bank;
import entity.Client;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AppInsert {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client1 = new Client();
        client1.setFirstName("John");
        client1.setLastName("Snow");
        client1.setDeposit(2000.0);
        client1.setPercents(10.0);

        Client client2 = new Client();
        client2.setFirstName("Daenerys");
        client2.setLastName("Targaryen");
        client2.setDeposit(3000.0);
        client2.setPercents(12.0);

        Client client3 = new Client();
        client3.setFirstName("Arya");
        client3.setLastName("Stark");
        client3.setDeposit(5000.0);
        client3.setPercents(15.0);

        List<Client> clientList1 = new ArrayList<Client>();
        clientList1.add(client1);
        clientList1.add(client2);
        clientList1.add(client3);

        List<Client> clientList2 = new ArrayList<Client>();
        clientList2.add(client1);
        clientList2.add(client2);

        Bank bank1 = new Bank();
        bank1.setName("Privat Bank");
        bank1.setClients(clientList1);

        Bank bank2 = new Bank();
        bank2.setName("Oshchad Bank");
        bank1.setClients(clientList2);

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.persist(client3);

        entityManager.persist(bank1);
        entityManager.persist(bank2);

        entityManager.getTransaction().commit();

        entityManager.close();

        sessionFactory.close();
    }
}
