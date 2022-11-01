import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final SessionFactory ourSessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Company supplier = new Supplier("Zabka", "Dobrego Pasterza 99",
                "Krakow", "31-416", "123456789");
        Company supplier1 = new Supplier("Auchan", "Bora Komorowskiego 16",
                "Krakow", "30-049", "987654321");
        Company customer = new Customer("Bar pod sosnami", "Babinskiego 10",
                "Olkusz", "30-010", 100.00);
        Company customer1 = new Customer("Vistula Bar", "Czarnowiejska 10",
                "Krakow", "30-049", 50.00);

        Transaction tx = session.beginTransaction();
        session.save(supplier);
        session.save(supplier1);
        session.save(customer);
        session.save(customer1);
        tx.commit();

        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
}