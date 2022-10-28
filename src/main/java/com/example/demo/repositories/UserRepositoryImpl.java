package com.example.demo.repositories;

import com.example.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {
    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryCustom.class);

    @PersistenceContext
    private EntityManager em;

    //lay data su dung hibernate query
    @Override
    public List<User> fillByTen(String ten) {
        List<User> result = new ArrayList<>();
        try {
            String sql = "select n from User n where n.ten like :ten escape '/' ";
            Query query = em.createQuery(sql);
            query.setParameter("ten", ten);
            query.setFirstResult(0);
            query.setMaxResults(10);
            result = query.getResultList();
        } catch (Exception ex) {
            LOG.error("fillByTen: " + ex.getMessage());
        }
        return result;
    }

    //lay data su dung native query
    @Override
    public List<User> fillByTenNative(String ten) {
        List<User> result = new ArrayList<>();
        try {
            String sql = "select * from USER n where n.ten like :ten escape '/' ";
            Query query = em.createNativeQuery(sql);
            query.setParameter("ten", ten);
            query.setFirstResult(0);//phan trang, lay tu phan tu thu 0 trong list, max lay 10 phan tu
            query.setMaxResults(10);
            result = query.getResultList();
        } catch (Exception ex) {
            LOG.error("fillByTenNative: " + ex.getMessage());
        }
        return result;
    }
}

