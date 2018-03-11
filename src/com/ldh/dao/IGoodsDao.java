package com.ldh.dao;

import java.util.List;

import com.ldh.model.Goods;
import com.ldh.util.PageBean;

public interface IGoodsDao {
	/**
	 * 新增商品
	 * @param goods
	 * @return
	 */
	public String save(Goods goods);
	
	/**
	 * 删除商品
	 * @param goods
	 * @return
	 */
	public boolean delete(Goods goods);
	
	/**
	 * 更新商品
	 * @param goods
	 * @return
	 */
	public boolean update(Goods goods);
	
	/**
	 * 查询所有商品
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有商品带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询商品
	 * @param id
	 * @return
	 */
	public Goods getById(String id);
	
	/**
	 * 根据其他条件查询商品带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询商品
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
