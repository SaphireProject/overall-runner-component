package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.Room;
import runner.utils.HibernateSessionFactoryUtil;

public class RoomDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomDAO.class);

    public void save(Room room) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(room);
        tx1.commit();
        session.close();
    }

    public void update(Room room) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(room);
        tx1.commit();
        session.close();
    }

    public void delete(Room room) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(room);
        tx1.commit();
        session.close();
    }

    public Room findRoomById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Room.class, id);
    }

}
