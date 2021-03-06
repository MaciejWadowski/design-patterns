package agh.dp.facade;

import agh.dp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.OptimisticLockException;
import javax.transaction.TransactionScoped;
import javax.transaction.TransactionalException;
import java.io.Serializable;
import java.util.List;

public class DatabaseOperations {

    private final Session session;


    public DatabaseOperations(Session session) {
        this.session = session;
    }

    public Long save(Object object) {
        session.clear();
        org.hibernate.Transaction tr = session.beginTransaction();
        Long addedRecordId = (Long) session.save(object);
        tr.commit();
        if (session.contains(object)) return addedRecordId;
        else return null;
    }

    public Boolean delete(Object object, Class clazz, Serializable key) {
        Boolean flag = true;
        session.clear();
        try {
            org.hibernate.Transaction tr = session.beginTransaction();
            Object o = session.get(clazz, key);
            if (o == null) {
                tr.rollback();
                return false;
            }
            session.delete(o);
            tr.commit();
        } catch (OptimisticLockException e) {
            flag = false;
        }
        return flag;
    }

    public Boolean update(Object object) {
        Boolean flag = true;
        session.clear();
        try {
            org.hibernate.Transaction tr = session.beginTransaction();
            session.update(object);
            tr.commit();
        } catch (OptimisticLockException e) {
            flag = false;
        }
        return flag;
    }

    public Object load(Class clazz, Serializable key) {
        Object obj;
        try {
            obj = session.load(clazz, key);
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    public Object get(Class clazz, Serializable key) {
        session.clear();
        return session.get(clazz, key);
    }

    public List<Object> fetchAll(Class clazz) {
        return HibernateUtil.loadAllData(clazz, session);
    }
}
