/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

/**
 *
 * @author ayushi.jain
 * @param <T>
 * @param <ID>
 */
public class GenericHibernateDAO<T, ID extends Serializable> implements GenericDAO<T, ID> {

    private final Class<T> persistentClass;
    public Session session;

    public GenericHibernateDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session session) {
        this.session = session;
        this.session.beginTransaction();
    }

    protected Session getSession() {
        try {
            if (session == null) {
//            throw new IllegalStateException("Session has not been set on DAO before usage");
                System.out.println("Session Not Created On GenericHibernateDAO>>getSession method");
            }

        } catch (Exception e) {
            System.out.println("Exception on getting Session---" + e.getCause() + "-" + e.getMessage());
        }
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

//    @Override
//    public T readById(ID id, boolean lock) {
//        T entity;
//        try {
//            if (lock) {
//                entity = (T) getSession().load(getPersistentClass(), id, LockMode.UPGRADE);
//            } else {
//                entity = (T) getSession().load(getPersistentClass(), id);
//            }
//            getSession().getTransaction().commit();
//        } catch (Exception e) {
//            entity = null;
//            getSession().getTransaction().rollback();
//        }
//        return entity;
//    }

    @Override
    public List<T> readAll() {
        return readByCriteria();
    }//end method

    @Override
    public List<T> readByExample(T exampleInstance, String[] excludeProperty) {
        List<T> data;
        try {
            Criteria crit = getSession().createCriteria(getPersistentClass());
            Example example = Example.create(exampleInstance);
            for (String exclude : excludeProperty) {
                example.excludeProperty(exclude);
            }
            crit.add(example);
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
        }
        return data;
    }//end method

    @Override
    public boolean save(T entity) {
        boolean state = true;
        try {
            getSession().persist(entity);
            getSession().flush();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            state = false;
            getSession().getTransaction().rollback();
            e.printStackTrace();
            String localizedMessage = e.getLocalizedMessage();
        }
        return state;
    }//end method

    @Override
    public boolean update(T entity) {
        boolean state = true;
        try {
            getSession().update(entity);
            getSession().flush();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            state = false;
            getSession().getTransaction().rollback();
        }
        return state;
    }//end method

    @Override
    public boolean delete(T entity) {
        boolean state = true;
        try {
            getSession().delete(entity);
            getSession().flush();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            state = false;
            getSession().getTransaction().rollback();
        }
        return state;
    }

    protected List<T> readByCriteria(Criterion... criterion) {
        List<T> data;
        try {
            Criteria crit = getSession().createCriteria(getPersistentClass());
            for (Criterion c : criterion) {
                crit.add(c);
            }
            data = crit.list();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            data = null;
            getSession().getTransaction().rollback();
        }
        return data;
    }//end method

    @Override
    public Object saveAndReturnIdentifier(T entity) {
        Object identifier;
        try {
            getSession().persist(entity);
            getSession().flush();
            identifier = getSession().getIdentifier(entity);
            getSession().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            identifier = null;
            getSession().getTransaction().rollback();
        }
        return identifier;
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        boolean state = true;
        try {
            getSession().saveOrUpdate(entity);
            getSession().flush();
            getSession().getTransaction().commit();
        } catch (Exception e) {
            state = false;
            getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        return state;
    }
}//end class

