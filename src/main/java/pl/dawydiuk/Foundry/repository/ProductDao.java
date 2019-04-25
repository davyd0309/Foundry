package pl.dawydiuk.Foundry.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Judith on 09.03.2019.
 */


@AllArgsConstructor
@Transactional
@Slf4j
public class ProductDao {

    private final SessionFactory sessionFactory;

    public void persist(final Product product) {
        sessionFactory.getCurrentSession().persist(product);
    }

    public List<Product> getAll(){
        Session currentSession = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = currentSession.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> rootEntry = cq.from(Product.class);
        CriteriaQuery<Product> all = cq.select(rootEntry);

        TypedQuery<Product> allQuery = currentSession.createQuery(all);
        return allQuery.getResultList();}
}
