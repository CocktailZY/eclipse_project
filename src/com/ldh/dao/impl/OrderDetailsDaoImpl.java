package com.ldh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ldh.dao.IOrderDetailsDao;
import com.ldh.model.OrderDetails;
import com.ldh.util.PageBean;

@Component(value="OrderDetailsDao")
public class OrderDetailsDaoImpl implements IOrderDetailsDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(OrderDetails orderDetails) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int returnId = (int) session.save(orderDetails);
		session.getTransaction().commit();
		session.close();
		if(returnId != 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(OrderDetails orderDetails) {
		boolean result = false;
		try{
			if(orderDetails != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(orderDetails);
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
	public boolean update(OrderDetails orderDetails) {
		boolean result = false;
		try{
			if(orderDetails != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(orderDetails);
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
		Query query = session.createQuery("from OrderDetails");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from OrderDetails");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public OrderDetails getById(int id) {
		Session session = sessionFactory.openSession();
		OrderDetails dto = (OrderDetails)session.get(OrderDetails.class, id);
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
