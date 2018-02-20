package com.ldh.dao;

import java.util.List;

import com.ldh.model.Picture;
import com.ldh.util.PageBean;

public interface IPictureDao {
	/**
	 * 新增订单
	 * @param picture
	 * @return
	 */
	public boolean save(Picture picture);
	
	/**
	 * 删除订单
	 * @param picture
	 * @return
	 */
	public boolean delete(Picture picture);
	
	/**
	 * 更新订单
	 * @param picture
	 * @return
	 */
	public boolean update(Picture picture);
	
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
	public Picture getById(int id);
	
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
