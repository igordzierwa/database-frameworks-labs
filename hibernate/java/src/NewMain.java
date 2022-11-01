//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class NewMain {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("derby");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction et = em.getTransaction();
//
//        et.begin();
//
//        Product product1 = new Product("Jablko", 100);
//        Product product2 = new Product("Gruszka", 200);
//        Product product3 = new Product("Banan", 300);
//        Supplier supplier = new Supplier("Zabka", "Dobrego Pasterza 99", "Krakow");
//
//        supplier.addProduct(product1);
//        supplier.addProduct(product2);
//        supplier.addProduct(product3);
//        product1.setSupplier(supplier);
//        product2.setSupplier(supplier);
//        product3.setSupplier(supplier);
//        em.persist(supplier);
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//
//        et.commit();
//        em.close();
//    }
//}
