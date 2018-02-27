package com.ldh.dao;

import java.util.List;

import com.ldh.model.Judge;
import com.ldh.util.PageBean;

public interface IJudgeDao {
	/**
	 * 新增判定条件
	 * @param judge
	 * @return
	 */
	public boolean save(Judge judge);
	
	/**
	 * 删除判定条件
	 * @param judge
	 * @return
	 */
	public boolean delete(Judge judge);
	
	/**
	 * 更新判定条件
	 * @param judge
	 * @return
	 */
	public boolean update(Judge judge);
	
	/**
	 * 查询所有判定条件
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有判定条件带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询判定条件
	 * @param id
	 * @return
	 */
	public Judge getById(String id);
	
	/**
	 * 根据其他条件查询判定条件带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询判定条件
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
