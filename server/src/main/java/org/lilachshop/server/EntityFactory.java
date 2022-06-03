package org.lilachshop.server;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.lilachshop.entities.*;
import org.lilachshop.entities.Order;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Hello world!
 */
public class EntityFactory {
    private static EntityFactory ef = null;    // Singleton, creating a SessionFactory is a heavy operation!
    private final SessionFactory sf;

    private EntityFactory() {
        sf = getSessionFactory();
    }

    public static EntityFactory getEntityFactory() {
        if (ef == null) {
            ef = new EntityFactory();
        }
        return ef;
    }

    public List<Store> getAllStores() {
        return getAllRecords(Store.class);
    }

    public List<Item> getAllItems() {    // should be gotten from a specific catalog,but currently DB has a single table of Items
        return getAllRecords(Item.class);
    }

    public List<Catalog> getAllCatalogs() {
        return getAllRecords(Catalog.class);
    }

    public List<Complaint> getAllComplaints() {    // should be gotten from a specific catalog,but currently DB has a single table of Items
        return getAllRecords(Complaint.class);
    }

    public void createCatalog() {
        Catalog catalog = App.generateCatalog();
        createOrUpdateSingleRecord(catalog);
    }

    public void createCatalogFromExistingOne() {
        List<Item> items = getAllRecords(Item.class);
        Catalog catalog = new Catalog();
        catalog.setItems(items);
        createOrUpdateSingleRecord(catalog);
    }

    public void fillDataBase() {
        // ---------------- creating 3 catalogs -------------
        Catalog catalog1 = App.generateCatalog();
        Catalog catalog2 = App.generateCatalog();
        Catalog catalog3 = App.generateCatalog();

        Store store1 = new Store("חיפה, דרך אבא חושי 1", "לילך חיפה", catalog1, new ArrayList<Complaint>(), new ArrayList<Order>());
        Store store2 = new Store("הרצליה, דרך הים 41", "לילך הרצליה", catalog2, new ArrayList<Complaint>(), new ArrayList<Order>());
        Store store3 = new Store("תל אביב, דיזינגוף 52", "לילך תל אביב", catalog3, new ArrayList<Complaint>(), new ArrayList<Order>());

        catalog1.setStore(store1);
        catalog2.setStore(store2);
        catalog3.setStore(store3);

        createOrUpdateSingleRecord(store1);
        createOrUpdateSingleRecord(store2);
        createOrUpdateSingleRecord(store3);

        createOrUpdateSingleRecord(catalog1);
        createOrUpdateSingleRecord(catalog2);
        createOrUpdateSingleRecord(catalog3);


        addOredersToStoresStore(store1, store2, store3);
        addComplaintsToStores(store1, store2, store3);
        createOrUpdateSingleRecord(store1);
        createOrUpdateSingleRecord(store2);
        createOrUpdateSingleRecord(store3);

        //
        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_EMPLOYEE, "ronaldo", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.CUSTOMER_SERVICE, "yossi", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_MANAGER, "yaakov", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.CHAIN_MANAGER, "asaf", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.SYSTEM_MANAGER, "omer", "1234"));
        createOrUpdateSingleRecord(new Employee(store1, Role.STORE_EMPLOYEE, "ido", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE, "neta", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE, "ziv", "1234"));
        createOrUpdateSingleRecord(new Employee(store2, Role.STORE_EMPLOYEE, "malcy", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE, "messy", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE, "george", "1234"));
        createOrUpdateSingleRecord(new Employee(store3, Role.STORE_EMPLOYEE, "john", "1234"));

    }


    public void addComplaintsToStores(Store store1, Store store2, Store store3) {
        LocalDate dt = LocalDate.of(2022, 5, 27);
        String time = dt.toString();

        Complaint complaint1 = new Complaint(dt.plusDays(1), ComplaintStatus.OPEN, "אני כועס מאוד על השירות בחיפה1", dt, "");
        Complaint complaint2 = new Complaint(dt.plusDays(2), ComplaintStatus.OPEN, "אני כועס מאוד על השירות בחיפה2", dt.plusDays(1), "");
        Complaint complaint3 = new Complaint(dt.plusDays(1), ComplaintStatus.OPEN, "אני כועס מאוד על השירות בהרצליה3", dt, "");

        complaint1.setOrder(store1.getOrders().get(0));
        complaint1.setStore(store1);
        createOrUpdateSingleRecord(store1);
        complaint2.setOrder(store1.getOrders().get(1));
        complaint2.setStore(store1);
        createOrUpdateSingleRecord(store1);
        complaint3.setOrder(store2.getOrders().get(0));
        complaint3.setStore(store2);
        createOrUpdateSingleRecord(store2);

        createOrUpdateSingleRecord(complaint1);
        createOrUpdateSingleRecord(complaint2);
        createOrUpdateSingleRecord(complaint3);

        store1.addComplaint(complaint1);
        store1.addComplaint(complaint2);
        store1.addComplaint(complaint3);

    }

    public void addOredersToStoresStore(Store store1, Store store2, Store store3) {
        LocalDate dt = LocalDate.of(2022, 5, 27);
        List<Item> generalItemList = App.createItemList();
        for (Item item : generalItemList) {
            createOrUpdateSingleRecord(item);
        }

        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(new CreditCard("1234123412341234", dt, "gil", "12345734", "123"));
        creditCards.add(new CreditCard("4321123412341234", dt, "ziv", "82121312", "412"));
        creditCards.add(new CreditCard("1111123412341234", dt, "tsvika", "43232354", "415"));


        Account account1 = new Account(AccountType.STORE_ACCOUNT);
        Account account2 = new Account(AccountType.CHAIN_ACCOUNT);
        Account account3 = new Account(AccountType.ANNUAL_SUBSCRIPTION);

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("gil", "1234", "גיל קרטגינר", "חיפה 32", "0542494993", false, creditCards.get(0), new ArrayList<Order>(), store1, account1));
        customers.add(new Customer("yaron", "1111", "ירון מלמד", "חיפה 55", "0542493123", false, creditCards.get(1), new ArrayList<Order>(), store1, account2));
        customers.add(new Customer("ziv", "4444", "זיו קרטגינר", "הרצליה 32", "0542453293", false, creditCards.get(2), new ArrayList<Order>(), store2, account3));

        int i = 0;
        for (Customer customer : customers) {
            customer.setCard(creditCards.get(i % 3));
            createOrUpdateSingleRecord(customer);
            i++;
        }

        // ---------------------------------------------------------
        List<myOrderItem> itemList1 = new ArrayList<>();
        itemList1.add(new myOrderItem(generalItemList.get(0), 3));
        itemList1.add(new myOrderItem(generalItemList.get(1), 2));
        itemList1.add(new myOrderItem(generalItemList.get(2), 1));
        itemList1.add(new myOrderItem(generalItemList.get(3), 5));


        DeliveryDetails deliveryDetails1 = new DeliveryDetails(dt, "05429384384", "גיל", "חיפה 42");
        Order order1 = new Order(dt, "מזל טוב תתחדשי על הפרחים!", itemList1, 100, 4, deliveryDetails1, null, null, customers.get(0));
        order1.setStore(store1);
        deliveryDetails1.setOrder(order1);
        List<myOrderItem> itemList2 = new ArrayList<>();
        itemList2.add(new myOrderItem(generalItemList.get(4), 2));
        itemList2.add(new myOrderItem(generalItemList.get(5), 5));
        itemList2.add(new myOrderItem(generalItemList.get(11), 7));

        DeliveryDetails deliveryDetails2 = new DeliveryDetails(dt, "05429384384", "זיו", "חיפה, נווה שאנן 42");
        Order order2 = new Order(dt, "מזל טוב תתחדשו על הפרחים שלכם, הם יפים!", itemList2, 200, 4, deliveryDetails2, null, null, customers.get(1));
        order2.setStore(store1);
        deliveryDetails2.setOrder(order2);
        createOrUpdateSingleRecord(deliveryDetails1);
        createOrUpdateSingleRecord(deliveryDetails2);
        createOrUpdateSingleRecord(order1);
        createOrUpdateSingleRecord(order2);

        store1.addOrder(order1);
        store1.addOrder(order2);
        customers.get(0).addOrderToList(order1);
        customers.get(1).addOrderToList(order2);

        List<myOrderItem> itemList3 = new ArrayList<>();
        itemList3.add(new myOrderItem(generalItemList.get(1), 5));
        itemList3.add(new myOrderItem(generalItemList.get(7), 1));
        itemList3.add(new myOrderItem(generalItemList.get(3), 3));
        PickUpDetails pickUpDetails1 = new PickUpDetails(dt);
        Order order3 = new Order(dt, "", itemList3, 400, 4, null, pickUpDetails1, null, customers.get(2));
        order3.setStore(store2);
        pickUpDetails1.setOrder(order3);
        customers.get(2).addOrderToList(order3);
        createOrUpdateSingleRecord(pickUpDetails1);
        createOrUpdateSingleRecord(order3);
        store2.addOrder(order3);

    }

    public List<Store> getStores() {
        return getAllRecords(Store.class);
    }

    public List<Employee> getEmployees() {
        return getAllRecords(Employee.class);
    }

    public List<Complaint> getComplaints() {
        return getAllRecords(Complaint.class);
    }

    public List<Customer> getCustomers() {
        return getAllRecords(Customer.class);
    }

    public List<Order> getOrders() {
        return getAllRecords(Order.class);
    }

    public void addCustomer(Customer customer) {
        createOrUpdateSingleRecord(customer);
    }
    public void addOrder(Order order) {
        createOrUpdateSingleRecord(order);
    }

    public Customer getCustomerByUserName(String userNameKey) {
        return getSingleRecord(Customer.class, "userName", userNameKey);
    }

    public void addAllEmployees(List<Employee> employees) {
        for (Employee e : employees) {
            createOrUpdateSingleRecord(e);
        }
    }

    public void updateEmployee(Employee employee) {
        createOrUpdateSingleRecord(employee);
    }

    public List<Complaint> getComplaintsByStoreId(long storeId) {
        return getListOfRecordByKey(Complaint.class, "store", storeId);
    }

    public List<Order> getOrderCustomerID(long customerID) {
        return getListOfRecordByKey(Order.class, "customer", customerID);
    }

    public List<Order> getOrdersByStoreId(long storeId) {
        return getListOfRecordByKey(Order.class, "store", storeId);
    }

    public Catalog getSingleCatalogEntityRecord(long entityID) {
        return getSingleRecord(Catalog.class, "id", entityID);
    }

    public void addItemToCatalog(Long catalogId, Item item) {
        Catalog catalog = getSingleCatalogEntityRecord(catalogId);
        catalog.addItem(item);
        createOrUpdateSingleRecord(catalog);
    }

    public Store getStoreById(long entityID) {
        return getSingleRecord(Store.class, "id", entityID);
    }

    public void removeEmployeeByID(long id) {
        deleteRecord(Employee.class, "id", id);
    }

    public void removeEmployeesByID(Set<Long> ids) {
        for (Long id : ids) {
            removeEmployeeByID(id);
        }
    }

    /*
     *****************************************  Example Entity Methods   ******************************************************
     */

    public List<ExampleEntity> getAllExampleEntities() {
        return getAllRecords(ExampleEntity.class);
    }

    public Catalog getSingleCatalogEntityRecordByStoreID(long storeID) {
        Store store = getSingleRecord(Store.class, "id", storeID);
        return store.getCatalog();
    }

    // Usage of query API
    public ExampleEntity getSingleExampleEntityRecord(long entityID) {
        return getSingleRecord(ExampleEntity.class, "id", entityID);
    }

    // Usage of query API
    public void createExampleEntity(ExampleEntity exampleEntity) {
        createOrUpdateSingleRecord(exampleEntity);
    }

    // Usage of query API
    public void updateExampleEntityEnumByID(int entityID, ExampleEnum enumToSet) {
        updateRecordField(ExampleEntity.class, "exampleEnum", enumToSet, entityID, "id");
    }

    // Overload
    public void updateExampleEntityEnumByID(ExampleEntity toUpdateEntity) {
        updateRecordField(ExampleEntity.class, "exampleEnum", toUpdateEntity.getExampleEnum(), toUpdateEntity.getId(), "entity_id");
    }

    // Usage of query API
    public void deleteExampleEntityByID(int entityID) {
        deleteRecord(ExampleEntity.class, "entity_id", entityID);
    }



    /*
     ******************************************************************************************************************
     */


    /*
     *****************************************   Utilities   **********************************************************
     */


    /**
     * Get a list of all table records of a certain entity
     *
     * @param entityClass Entity metamodel
     * @return List of entities
     */
    private <T> List<T> getAllRecords(Class<T> entityClass) {
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);

        Root<T> root = cq.from(entityClass);
        cq.select(root);

        Query<T> query = session.createQuery(cq);
        List<T> result = new LinkedList<>(query.getResultList());
        session.close();
        return result;
    }

    private <T, S> T getSingleRecord(Class<T> entityClass, String keyColumn, S key) {
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get(keyColumn), key));

        Query<T> query = session.createQuery(cq);
        List<T> record_list = query.getResultList();
        session.close();
        return record_list.isEmpty() ? null : record_list.get(0);
    }

    private <T, S> List<T> getListOfRecordByKey(Class<T> entityClass, String keyColumn, S key) { // todo: test this
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
        cq.select(root);
        cq.where(cb.equal(root.get(keyColumn), key));

        Query<T> query = session.createQuery(cq);
        return query.getResultList();
    }


    /**
     * Add a single record.
     *
     * @param entityToCreate Entity record to add
     * @param <T>            Entity type
     */
    <T> void createOrUpdateSingleRecord(T entityToCreate) {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(entityToCreate);

        transaction.commit();
        session.close();
    }

    /**
     * Update specific attribute in a record.
     *
     * @param <T>                Entity class type
     * @param <S>                Value type of to be set type
     * @param <K>                Value type of object to be checked
     * @param entityClass        Entity metamodel
     * @param mutateAttribColumn Attribute field column name to update
     * @param valueToSet         The value to be set
     * @param key                Update where key matches
     * @param keyColumn          Key column name to match where to update
     */
    private <T, S, K> void updateRecordField(Class<T> entityClass, String mutateAttribColumn, S valueToSet, K key, String keyColumn) {
        Session session = sf.openSession(); // todo: add exception handling
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<T> cu = cb.createCriteriaUpdate(entityClass);
        Root<T> root = cu.from(entityClass);
        cu.set(root.get(mutateAttribColumn), valueToSet);
        cu.where(cb.equal(root.get(keyColumn), key));

        Transaction transaction = session.beginTransaction();
        session.createQuery(cu).executeUpdate();
        transaction.commit();
        session.close();
    }

    /**
     * Delete specific record.
     *
     * @param entityClass Entity metamodel
     * @param keyColumn   Key column name to match where to delete
     * @param key         Delete where key matches
     * @param <T>         Entity class
     * @param <S>         Value type of object to be checked
     */
    private <T, S> void deleteRecord(Class<T> entityClass, String keyColumn, S key) {   //todo: test this
        Session session = sf.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<T> cd = cb.createCriteriaDelete(entityClass);
        Root<T> root = cd.from(entityClass);
        cd.where(cb.equal(root.get(keyColumn), key));

        Transaction transaction = session.beginTransaction();
        session.createQuery(cd).executeUpdate();
        transaction.commit();
        session.close();
    }


    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(ExampleEntity.class).addAnnotatedClass(ExampleEnum.class).addAnnotatedClass(Item.class).addAnnotatedClass(Catalog.class).addAnnotatedClass(Complaint.class).addAnnotatedClass(DeliveryDetails.class).addAnnotatedClass(PickUpDetails.class).addAnnotatedClass(Order.class).addAnnotatedClass(Store.class).addAnnotatedClass(User.class).addAnnotatedClass(Employee.class).addAnnotatedClass(Customer.class).addAnnotatedClass(CreditCard.class).addAnnotatedClass(Account.class).addAnnotatedClass(myOrderItem.class);


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }



}
