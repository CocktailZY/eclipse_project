package com.ldh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ldh.dao.IBrandDao;
import com.ldh.model.Brand;
import com.ldh.util.PageBean;

@Component(value="BrandDao")
public class BrandDaoImpl implements IBrandDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(Brand brand) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String returnId = (String) session.save(brand);
		session.getTransaction().commit();
		session.close();
		if(!"".equals(returnId) && null != returnId){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean delete(Brand brand) {
		boolean result = false;
		try{
			if(brand != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(brand);
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
	public boolean update(Brand brand) {
		boolean result = false;
		try{
			if(brand != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(brand);
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
		Query query = session.createQuery("from Brand");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Brand");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Brand getById(String id) {
		Session session = sessionFactory.openSession();
		Brand dto = (Brand)session.get(Brand.class, id);
		session.close();
		return dto;
	}

	@Override
	public List<Object> getByConds(String hql, PageBean page) {
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
