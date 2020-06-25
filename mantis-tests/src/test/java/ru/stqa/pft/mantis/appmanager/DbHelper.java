package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Issues;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users usersWithoutAdmin() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<UserData> result = session.createQuery( "from UserData where access_level = 25" ).list();
      session.getTransaction().commit();
      session.close();
    return new Users(result);
  }

  public Issues openIssues() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Issue> result = session.createQuery( "from Issue where resolution = 10" ).list();
    session.getTransaction().commit();
    session.close();
    return new Issues(result);
  }

  public Issues fixIssues() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Issue> result = session.createQuery( "from Issue where resolution = 20" ).list();
    session.getTransaction().commit();
    session.close();
    return new Issues(result);
  }
}
