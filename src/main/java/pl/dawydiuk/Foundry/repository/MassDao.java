package pl.dawydiuk.Foundry.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import models.Mass;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Judith on 25.02.2019.
 */


@AllArgsConstructor
@Transactional
@Slf4j
public class MassDao {

    private final SessionFactory sessionFactory;

    public List<Mass> getAllMass() {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();

        CriteriaQuery<Mass> query = criteriaBuilder.createQuery(Mass.class);
        Root<Mass> root = query.from(Mass.class);
        query.select(root);

        Query<Mass> query1 = sessionFactory.getCurrentSession().createQuery(query);
        return query1.getResultList();
    }


}
