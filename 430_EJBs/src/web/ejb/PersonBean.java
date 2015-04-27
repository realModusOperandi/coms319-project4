package web.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import web.jpa.Person;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
@LocalBean
@Named
public class PersonBean {

    @PersistenceContext(unitName = "430_EJBs")
    EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Person> getPeople() {
        Query query = em.createQuery("SELECT u FROM Person u");
        List<Person> users = query.getResultList();
        return users;
    }

    public void addPerson(String uname, String email) {
        Person user = new Person(email, uname, "default_password");
        em.persist(user);
    }

    public Person getPerson(String uname) {
        TypedQuery<Person> query = em.createQuery("SELECT u FROM Person u WHERE u.uname = :name", Person.class);
        Person result = query.setParameter("name", uname).getSingleResult();
        return result;
    }

    public void updatePerson(String username, String email, String passwd) {
        Person toUpdate = getPerson(username);
        toUpdate.setEmail(email);
        toUpdate.setPasswd(passwd);
        em.persist(toUpdate);
    }
}
