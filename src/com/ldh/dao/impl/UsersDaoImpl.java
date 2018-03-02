package com.ldh.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.ldh.dao.IUsersDao;
import com.ldh.model.Users;
import com.ldh.util.PageBean;

@Component(value="UsersDao")
public class UsersDaoImpl implements IUsersDao {
	
	private SessionFactory sessionFactory;
	
	@Resource(name="sessionFactory")//sessionFactory注入
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(Users user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String returnId = (String) session.save(user);
		session.getTransaction().commit();
		session.close();
		if("".equals(returnId) && null != returnId){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean delete(Users user) {
		user.setuSign(2);//逻辑删除
		boolean result = false;
		try{
			if(user != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(user);
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
	public boolean update(Users user) {
		boolean result = false;
		try{
			if(user != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.update(user);
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
		Query query = session.createQuery("from Users where uSign != 2");
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public List<Object> listAll(PageBean page) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Users where uSign != 2");
		query.setFirstResult(page.getRowStart());
		query.setMaxResults(page.getPageSize());
		List<Object> list = query.list();
		session.close();
		return list;
	}

	@Override
	public Users getById(String id) {
		Session session = sessionFactory.openSession();
		Users dto = (Users)session.get(Users.class, id);
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
