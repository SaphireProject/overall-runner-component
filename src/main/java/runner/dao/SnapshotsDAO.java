package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.Snapshots;
import runner.utils.HibernateSessionFactoryUtil;

public class SnapshotsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(SnapshotsDAO.class);

    public void save(Snapshots snapshots) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(snapshots);
        tx1.commit();
        session.close();
    }

    public void update(Snapshots snapshots) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(snapshots);
        tx1.commit();
        session.close();
    }

    public void delete(Snapshots snapshots) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(snapshots);
        tx1.commit();
        session.close();
    }

    public Snapshots findSnapshotsById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Snapshots.class, id);
    }

}
