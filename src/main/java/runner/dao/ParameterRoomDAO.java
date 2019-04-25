package runner.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.models.ParametersRoom;
import runner.utils.HibernateSessionFactoryUtil;

public class ParameterRoomDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterRoomDAO.class);

    public void save(ParametersRoom parametersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(parametersRoom);
        tx1.commit();
        session.close();
    }

    public void update(ParametersRoom parametersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(parametersRoom);
        tx1.commit();
        session.close();
    }

    public void delete(ParametersRoom parametersRoom) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(parametersRoom);
        tx1.commit();
        session.close();
    }

    public ParametersRoom findParametersRoomById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(ParametersRoom.class, id);
    }


}
