package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.Templates;
import runner.utils.HibernateSessionFactoryUtil;

public class TemplateDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateDAO.class);

    public void save(Templates templates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(templates);
        tx1.commit();
        session.close();
    }

    public void update(Templates templates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(templates);
        tx1.commit();
        session.close();
    }

    public void delete(Templates templates) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(templates);
        tx1.commit();
        session.close();
    }

    public Templates findTemplatesById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Templates.class, id);
    }

}
