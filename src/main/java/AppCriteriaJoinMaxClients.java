import entity.*;
import entity.Bank_;
import entity.Client_;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.List;

public class AppCriteriaJoinMaxClients {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Selects
         */

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Result> criteriaQuery = builder.createQuery(Result.class);
        Root<Bank> rootBank = criteriaQuery.from(Bank.class);
        Join<Bank, Client> join = rootBank.join(Bank_.clients);

        Expression<Long> count = builder.count(join.get(Client_.id));

        criteriaQuery
                .multiselect(rootBank.get(Bank_.name), count)
                .groupBy(rootBank.get(Bank_.name))
                .orderBy(builder.desc(count));

        List<Result> banksList = entityManager.createQuery(criteriaQuery).getResultList();

        banksList.forEach(bank -> System.out.println(bank.getName() + ": " + bank.getCount()));

        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
