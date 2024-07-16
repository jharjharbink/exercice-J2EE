package org.example.tp_j2ee.repository;

import org.hibernate.Session;

import java.util.List;

public abstract class BaseRepository <T>{
    protected Session _session;

    public BaseRepository(Session session){
        _session = session;
    }

    public void create(T o){
        _session.save(o);
    }

    public void update(T o){
        _session.update(o);
    }

    public void delate(T o){
        _session.delete(o);
    }

    abstract T findById(int id);

    abstract List<T> findAll();

}
