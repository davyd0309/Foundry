package pl.dawydiuk.Foundry.repository;

import lombok.extern.slf4j.Slf4j;
import models.Mass;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by Konrad on 25.02.2019.
 */


@Transactional
@Slf4j
public class MassDao {

    private final SessionFactory sessionFactory;

    public MassDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Mass getAllMass() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Mass> query = criteriaBuilder.createQuery(Mass.class);
        Root<Mass> root = query.from(Mass.class);
        query.select(root);

        Query<Mass> query1 = sessionFactory.getCurrentSession().createQuery(query);
        return query1.uniqueResult();
    }




}
