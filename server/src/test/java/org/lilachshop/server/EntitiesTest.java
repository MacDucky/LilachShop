package org.lilachshop.server;


import org.junit.jupiter.api.*;
import org.lilachshop.entities.Catalog;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntitiesTest {

    static EntityFactory entityFactory;

    @BeforeAll
    static void createConnection() throws InterruptedException {
        int seconds_to_wait = 5;
        entityFactory = EntityFactory.getEntityFactory();
        Thread.sleep(seconds_to_wait * 1000);
    }

    @Order(1)
    @Test
    void shouldCreateEntityFactory() {
        Assertions.assertNotNull(entityFactory);
    }

    @Order(2)
    @Test
    void shouldCreateCatalog() {
        Assertions.assertDoesNotThrow(entityFactory::createCatalog);
    }

    @Order(3)
    @Test
    void shouldContainOneCatalog() {
        Catalog catalog = entityFactory.getCatalogByCatalogID(1L);
        Assertions.assertNotNull(catalog);
        System.out.println(catalog);
    }

    @Order(4)
    @Test
    void createAnotherCatalog


    @Disabled
    @Order(3)
    @Test
    void shouldCreateStore() {
        String address = "Baker Street 221b, London";
        String storeName = "Sherlok's shop";
//        Store store = new Store(address, storeName, )
    }

    @Order(4)
    @Test
    void shouldCreateTwoEmployees() {
//        Employee employee1 = new Employee()
        Assertions.assertTrue(true);
    }

}
