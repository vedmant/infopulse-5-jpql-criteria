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
import java.util.List;

public class AppCriteria {

    public static void main(String[] args) {
        SessionFactory sessionFactory = (SessionFactory) Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = sessionFactory.createEntityManager();
        entityManager.getTransaction().begin();

        /**
         * Selects
         */


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        criteriaQuery.from(Client.class);

        List<Client> clients = entityManager.createQuery(criteriaQuery).getResultList();

//        clients.forEach(client -> System.out.println(client.getTotalSum()));


        CriteriaQuery<Client> criteriaQuery2 = builder.createQuery(Client.class);
        Root<Client> root = criteriaQuery2.from(Client.class);

        ParameterExpression<Long> id = builder.parameter(Long.class);
        criteriaQuery2.where(builder.equal(root.get(Client_.id), id));

        List<Client> clients2 = entityManager.createQuery(criteriaQuery2)
                .setParameter(id, 11l)
                .getResultList();

        clients2.forEach(client -> System.out.println(client.getTotalSum()));

        entityManager.getTransaction().commit();

        entityManager.close();
        sessionFactory.close();
    }
}
