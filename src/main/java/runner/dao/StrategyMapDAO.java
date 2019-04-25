package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.StrategiesMap;
import runner.utils.HibernateSessionFactoryUtil;

public class StrategyMapDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyMapDAO.class);

    public void save(StrategiesMap strategyMap) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(strategyMap);
        tx1.commit();
        session.close();
    }

    public void update(StrategiesMap strategiesMap) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(strategiesMap);
        tx1.commit();
        session.close();
    }

    public void delete(StrategiesMap strategiesMap) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(strategiesMap);
        tx1.commit();
        session.close();
    }

    public StrategiesMap findStrategiesMapById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(StrategiesMap.class, id);
    }

}
