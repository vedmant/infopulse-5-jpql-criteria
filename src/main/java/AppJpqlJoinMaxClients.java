import entity.Result;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class AppJpqlJoinMaxClients {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Where statement with join
         */
        List<Result> banksList = entityManager
                .createQuery("select b.name, count(b.clients) " +
                        "from entity.Bank b " +
                        "join b.clients " +
                        "group by b.name " +
                        "order by count(b.clients) desc", Result.class)
                .getResultList();

        banksList.forEach(bank -> System.out.println(bank.getName() + ": " + bank.getCount()));

        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
