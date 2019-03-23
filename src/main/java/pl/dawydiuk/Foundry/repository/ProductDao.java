package pl.dawydiuk.Foundry.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Product;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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
}
