package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;
import runner.utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;

public class UsersRoomDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersRoomDAO.class);

    public void save(UsersRoom usersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(usersRoom);
        tx1.commit();
        session.close();
    }

    public void update(UsersRoom usersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(usersRoom);
        tx1.commit();
        session.close();
    }

    public void delete(UsersRoom usersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(usersRoom);
        tx1.commit();
        session.close();
    }

    public UsersRoom findUsersRoomById(UsersRoomId id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UsersRoom.class, id);
    }


    public void findUserRoomByid(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        org.hibernate.query.Query query = session.createQuery("FROM users_room AS us WHERE us.id_user =:id");
        query.setParameter("id", id);
        LOGGER.info(query.getResultList().toString());
        session.close();
    }

}