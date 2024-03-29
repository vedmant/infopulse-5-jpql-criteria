import entity.Bank;
import entity.Client;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class AppJpql {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Select all
         */
        List<Client> clients = entityManager.createQuery("from entity.Client", Client.class).getResultList();

        clients.forEach(client -> System.out.println(client.getTotalSum()));

        /**
         * Where statement
         */
        List<Client> clients2 = entityManager.createQuery("from entity.Client c where c.id = :id", Client.class)
                .setParameter("id", 11l)
                .getResultList();

        clients2.forEach(client -> System.out.println(client.getTotalSum()));


        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
