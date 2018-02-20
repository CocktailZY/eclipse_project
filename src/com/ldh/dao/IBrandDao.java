package com.ldh.dao;

import java.util.List;

import com.ldh.model.Brand;
import com.ldh.util.PageBean;

public interface IBrandDao {
	/**
	 * 新增商品类型
	 * @param goodsType
	 * @return
	 */
	public boolean save(Brand goodsType);
	
	/**
	 * 删除商品类型
	 * @param goodsType
	 * @return
	 */
	public boolean delete(Brand goodsType);
	
	/**
	 * 更新商品类型
	 * @param goodsType
	 * @return
	 */
	public boolean update(Brand goodsType);
	
	/**
	 * 查询所有商品类型
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有商品类型带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询商品类型
	 * @param id
	 * @return
	 */
	public Brand getById(int id);
	
	/**
	 * 根据其他条件查询商品类型带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询商品类型
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
