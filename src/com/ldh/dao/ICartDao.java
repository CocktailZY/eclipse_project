package com.ldh.dao;

import java.util.List;

import com.ldh.model.Cart;
import com.ldh.util.PageBean;

public interface ICartDao {
	/**
	 * 新增购物车
	 * @param cart
	 * @return
	 */
	public boolean save(Cart cart);
	
	/**
	 * 删除购物车
	 * @param cart
	 * @return
	 */
	public boolean delete(Cart cart);
	
	/**
	 * 更新购物车
	 * @param cart
	 * @return
	 */
	public boolean update(Cart cart);
	
	/**
	 * 查询所有购物车
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有购物车带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询购物车
	 * @param id
	 * @return
	 */
	public Cart getById(int id);
	
	/**
	 * 根据其他条件查询购物车带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询购物车
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
