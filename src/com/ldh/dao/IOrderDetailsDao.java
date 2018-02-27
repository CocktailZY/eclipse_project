package com.ldh.dao;

import java.util.List;

import com.ldh.model.OrderDetails;
import com.ldh.util.PageBean;

public interface IOrderDetailsDao {
	/**
	 * 新增订单详情
	 * @param orderDetails
	 * @return
	 */
	public boolean save(OrderDetails orderDetails);
	
	/**
	 * 删除订单详情
	 * @param orderDetails
	 * @return
	 */
	public boolean delete(OrderDetails orderDetails);
	
	/**
	 * 更新订单详情
	 * @param orderDetails
	 * @return
	 */
	public boolean update(OrderDetails orderDetails);
	
	/**
	 * 查询所有订单详情
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有订单详情带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询订单详情
	 * @param id
	 * @return
	 */
	public OrderDetails getById(String id);
	
	/**
	 * 根据其他条件查询订单详情带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询订单详情
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
