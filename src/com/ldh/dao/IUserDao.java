package com.ldh.dao;

import java.util.List;

import com.ldh.model.Users;
import com.ldh.util.PageBean;

public interface IUserDao {
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean save(Users user);
	
	/**
	 * 删除用户
	 * @param user
	 * @return
	 */
	public boolean delete(Users user);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public boolean update(Users user);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有用户带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询用户
	 * @param id
	 * @return
	 */
	public Users getById(int id);
	
	/**
	 * 根据其他条件查询用户带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询用户
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
