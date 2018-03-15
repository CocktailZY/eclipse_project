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

	@Override
	public Users money(String id, String change,int sign) {
		Session session = sessionFactory.openSession();
		Users dto = (Users)session.get(Users.class, id);
		double money = Double.parseDouble(dto.getuMoney());
		String uMoney ;
		//Sign ==1,代表充值操作，正在进行充值
		if(sign == 1){
			uMoney = String.valueOf ( money + Double.parseDouble(change) );
			dto.setuMoney(uMoney);
		}else if(sign == 2){
		//Sign ==2，代表消费操作，正在进行消费
			if(money - Double.parseDouble(change) < 0){
				//提示余额不足，跳转到充值页面
			}else{
				uMoney = String.valueOf ( money - Double.parseDouble(change) );
				dto.setuMoney(uMoney);
			}
			
		}
		session.close();
		return dto;
	}
	
	@Override
	public Users fraction(String id,int sign) {
		Session session = sessionFactory.openSession();
		Users dto = (Users)session.get(Users.class, id);
		double fraction = Double.parseDouble(dto.getuFraction());
		String uFraction = dto.getuFraction() ;
		//Sign ==1,代表完成订单，信用增加
		if(sign == 1){
			if(fraction <100){
				uFraction = String.valueOf ( fraction + 5 );
				dto.setuFraction(uFraction);
			}else{
				dto.setuFraction(uFraction);
			}
			
		}else if(sign == 2){
		//Sign ==2，代表消费操作，正在进行消费
			if( (fraction-10) <30 ){
				dto.setuSign(0);
				//提示余额不足，跳转到充值页面
			}else{
				uFraction = String.valueOf ( fraction - 10 );
				dto.setuFraction(uFraction);
			}
			
		}
		session.close();
		return dto;
	}

}
