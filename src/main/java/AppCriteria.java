import entity.Bank;
import entity.Client;
import entity.Client_;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class AppCriteria {

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



        /**
         * Selects
         */

        EntityManager entityManager2 = sessionFactory.createEntityManager();

        entityManager2.getTransaction().begin();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        criteriaQuery.from(Client.class);

        List<Client> clients = entityManager2.createQuery(criteriaQuery).getResultList();

//        clients.forEach(client -> System.out.println(client.getTotalSum()));


        CriteriaQuery<Client> criteriaQuery2 = builder.createQuery(Client.class);
        Root<Client> root = criteriaQuery2.from(Client.class);
        ParameterExpression<Long> id = builder.parameter(Long.class);
        criteriaQuery2.where(builder.equal(root.get(Client_.id), id));

        List<Client> clients2 = entityManager2.createQuery(criteriaQuery2)
                .setParameter(id, 11l)
                .getResultList();

        clients2.forEach(client -> System.out.println(client.getTotalSum()));

        entityManager2.getTransaction().commit();

        entityManager2.close();
        sessionFactory.close();
    }
}
