package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.Strategies;
import runner.utils.HibernateSessionFactoryUtil;

public class StrategyDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyDAO.class);

    public void save(Strategies strategies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(strategies);
        tx1.commit();
        session.close();
    }

    public void update(Strategies strategies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(strategies);
        tx1.commit();
        session.close();
    }

    public void delete(Strategies strategies) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(strategies);
        tx1.commit();
        session.close();
    }

    public Strategies findStrategyById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Strategies.class, id);
    }
}
