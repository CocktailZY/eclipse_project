package com.ldh.dao;

import java.util.List;

import com.ldh.model.Address;
import com.ldh.util.PageBean;

public interface IAddressDao {
	/**
	 * 新增品牌(类型)
	 * @param address
	 * @return
	 */
	public boolean save(Address address);
	
	/**
	 * 删除品牌(类型)
	 * @param address
	 * @return
	 */
	public boolean delete(Address address);
	
	/**
	 * 更新品牌(类型)
	 * @param address
	 * @return
	 */
	public boolean update(Address address);
	
	/**
	 * 查询所有品牌(类型)
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有品牌(类型)带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询品牌(类型)
	 * @param id
	 * @return
	 */
	public Address getById(int id);
	
	/**
	 * 根据其他条件查询品牌(类型)带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询品牌(类型)
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
