package com.ldh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ldh.dao.IGoodsDao;
import com.ldh.model.Goods;
import com.ldh.util.PageBean;

@Component(value="GoodsDao")
public class GoodsDaoImpl implements IGoodsDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String save(Goods goods) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String returnId = (String) session.save(goods);
		session.getTransaction().commit();
		session.close();
		if(!"".equals(returnId) && null != returnId){
			return returnId;
		}else{
			return "0";
		}
	}

	@Override
	public boolean delete(Goods goods) {
		boolean result = false;
		try{
			if(goods != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.delete(goods);
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
	public boolean update(Goods goods) {
		boolean result = false;
		try{
			if(goods != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(goods);
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
		Query query = session.createQuery("from Goods");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Goods");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Goods getById(String id) {
		Session session = sessionFactory.openSession();
		Goods dto = (Goods)session.get(Goods.class, id);
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

	@Override
	public List<Object> listByState() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Goods where gsign = 1");
		List<Object> list = query.list();
		session.close();
		return list;
	}
	
	
	@Override
	public List<Object> listByUId(String uId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Goods where 1=1 and uid = "+ uId);
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listByUId(PageBean page,String uId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Goods where 1=1 and uid = "+ uId);
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

}
