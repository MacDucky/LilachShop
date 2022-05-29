package org.lilachshop.server;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.lilachshop.entities.*;

import java.util.List;

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
    void shouldCreateEntityFactory(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        System.out.println("Creating Entity Factory...");
        Assertions.assertNotNull(entityFactory);
        System.out.println("Done");
    }

    @Order(2)
    @Test
    void shouldNotContainAnyCatalogs(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        List<Catalog> catalogs = entityFactory.getAllCatalogs();
        Assertions.assertTrue(catalogs.isEmpty());
        Assertions.assertNull(entityFactory.getCatalogByCatalogID(1L));
    }


    @Order(3)
    @Test
    void shouldCreateCatalog(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        Assertions.assertDoesNotThrow(entityFactory::createCatalog);
        Assertions.assertTrue(entityFactory.getAllCatalogs().size() > 0);
    }

    @Order(4)
    @Test
    void shouldContainOneCatalog(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        Catalog catalog = entityFactory.getCatalogByCatalogID(1L);
        Assertions.assertNotNull(catalog);
        Assertions.assertTrue(entityFactory.getAllCatalogs().size() < 2);
        System.out.println(catalog);
    }

    @Order(5)
    @Test
    void createAnotherCatalogAndQueryOnlyIt(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        Assertions.assertDoesNotThrow(entityFactory::createCatalog);
        Catalog catalog = entityFactory.getCatalogByCatalogID(2L);
        Assertions.assertEquals(catalog.getId(), 2L);
        Assertions.assertEquals(2, entityFactory.getAllCatalogs().size());
        System.out.println(catalog);
    }

    @Order(6)
    @Test
    void shouldCreateStore(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        String address = "Baker Street 221b, London";
        String storeName = "Sherlok's shop";
        Catalog catalog = entityFactory.getCatalogByCatalogID(2L);
        Store store = new Store(address, storeName, catalog, null, null);
        Assertions.assertDoesNotThrow(() -> entityFactory.addStore(store));
        Assertions.assertNotNull(catalog);
    }

    @Order(7)
    @Test
    void shouldCreateOneEmployeeAndAssignHimToStoreWithCatalog2(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        Store storeWithID1 = entityFactory.getStoreByID(1L);
        Employee employee = new Employee(storeWithID1, Role.STORE_EMPLOYEE, "Utzliguz", "Li");
        Assertions.assertDoesNotThrow(() -> entityFactory.addEmployee(employee));
        Assertions.assertTrue(entityFactory.getAllStores().size() < 2);
    }

    @Order(8)
    @Test
    void shouldCreateCustomer(TestInfo testInfo) {
        System.out.println("In Function: " + testInfo.getDisplayName());
        CreditCard card = new CreditCard("1234-5678-9087-6543", "02/99", "123");
        Account account = new Account(AccountType.STORE_ACCOUNT);
        Customer customer = new Customer("Utzguz", "12345", "Utzliguz Li", "Nesher,Israel",
                "0541231234", card, null, entityFactory.getStoreByID(1L), account);
        entityFactory.addCustomer(customer);
        Assertions.assertNotNull(entityFactory.getCustomerByID(1L));
    }

}
