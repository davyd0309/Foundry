package pl.dawydiuk.Foundry.repository;

import models.Product;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Judith on 09.03.2019.
 */

@Repository
@Transactional
public class ProductDao {

    private final SessionFactory sessionFactory;

    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void persist(final Product product) {
        sessionFactory.getCurrentSession().persist(product);
    }
}
