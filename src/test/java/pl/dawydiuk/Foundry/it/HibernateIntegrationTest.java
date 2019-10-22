package pl.dawydiuk.Foundry.it;

import models.Mass;
import models.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dawydiuk.Foundry.FoundryApplication;
import pl.dawydiuk.Foundry.builder.ProductBuilder;
import pl.dawydiuk.Foundry.repository.MassDao;
import pl.dawydiuk.Foundry.repository.ProductDao;

/**
 * Created by Konrad on 01.03.2019.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FoundryApplication.class)
@ActiveProfiles("dev")
public class HibernateIntegrationTest {

    @Autowired
    private MassDao dao;

    @Autowired
    private ProductDao productDao;

    @Test
    public void getAllMass() {
        Mass allMass = dao.getAllMass();

    }

    @Test
    public void saveProduct() {
        Product newProduct = new ProductBuilder().createNewProduct(null);
        productDao.persist(newProduct);
    }
}
