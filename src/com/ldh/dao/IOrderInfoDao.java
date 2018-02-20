package com.ldh.dao;

import java.util.List;

import com.ldh.model.OrderInfo;
import com.ldh.util.PageBean;

public interface IOrderInfoDao {
	/**
	 * 新增订单
	 * @param orderInfo
	 * @return
	 */
	public boolean save(OrderInfo orderInfo);
	
	/**
	 * 删除订单
	 * @param orderInfo
	 * @return
	 */
	public boolean delete(OrderInfo orderInfo);
	
	/**
	 * 更新订单
	 * @param orderInfo
	 * @return
	 */
	public boolean update(OrderInfo orderInfo);
	
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有订单带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询订单
	 * @param id
	 * @return
	 */
	public OrderInfo getById(int id);
	
	/**
	 * 根据其他条件查询订单带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询订单
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
