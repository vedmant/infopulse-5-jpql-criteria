import entity.Bank;
import entity.Bank_;
import entity.Client;
import entity.Client_;
import org.hibernate.SessionFactory;

import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.*;
import java.util.List;

public class AppCriteriaJoin {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Selects
         */

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Bank> criteriaQuery = builder.createQuery(Bank.class);
        Root<Bank> rootBank = criteriaQuery.from(Bank.class);
        Join<Bank, Client> join = rootBank.join(Bank_.clients);

        ParameterExpression<String> firstName = builder.parameter(String.class);

        criteriaQuery
                .select(rootBank)
                .where(builder.equal(join.get(Client_.firstName), firstName));

        List<Bank> banks = entityManager.createQuery(criteriaQuery)
                .setParameter(firstName, "John1")
                .getResultList();

        banks.forEach(bank -> System.out.println(bank.getName()));

        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
