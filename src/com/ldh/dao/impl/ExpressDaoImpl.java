package com.ldh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ldh.dao.IExpressDao;
import com.ldh.model.Express;
import com.ldh.util.PageBean;

@Component(value="ExpressDao")
public class ExpressDaoImpl implements IExpressDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(Express express) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int returnId = (int) session.save(express);
		session.getTransaction().commit();
		session.close();
		if(returnId != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Express express) {
		boolean result = false;
		try{
			if(express != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(express);
				session.getTransaction().commit();
				session.close();
				result = true;
			}
		}catch(HibernateException e){
			result = false;
		}
		return result;
	}

	@Override
	public boolean update(Express express) {
		boolean result = false;
		try{
			if(express != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(express);
				session.getTransaction().commit();
				session.close();
				result = true;
			}
		}catch(HibernateException e){
			result = false;
		}
		return result;
	}
	
	@Override
	public List<Object> list() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Express");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Express");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Express getById(int id) {
		Session session = sessionFactory.openSession();
		Express dto = (Express)session.get(Express.class, id);
		session.close();
		return dto;
	}

	@Override
	public List<Object> getByConds(String hql,PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}
	
	@Override
	public List<Object> getAllByConds(String hql) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery(hql);
		List<Object> list = query.list();
		session.close();
		return list;
	}

}
