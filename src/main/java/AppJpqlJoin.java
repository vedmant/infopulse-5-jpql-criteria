import entity.Bank;
import entity.Client;
import org.hibernate.SessionFactory;

import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class AppJpqlJoin {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Where statement with join
         */
        List<Bank> banks = entityManager
                .createQuery("select b from entity.Bank b join b.clients c where c.firstName = :firstName", Bank.class)
                .setParameter("firstName", "John")
                .getResultList();

        banks.forEach(bank -> System.out.println(bank.getName()));


        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
