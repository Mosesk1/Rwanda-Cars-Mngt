package Dao;

import org.hibernate.Session;

public class ManagingDb {

Session session = null;
	
    public Session createSession(){
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }
    
    public void closeSession(){
        session.getTransaction().commit();
        session.close();
    }
}
