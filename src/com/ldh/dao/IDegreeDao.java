package com.ldh.dao;

import java.util.List;

import com.ldh.model.Degree;
import com.ldh.util.PageBean;

public interface IDegreeDao {
	/**
	 * 新增使用程度
	 * @param degree
	 * @return
	 */
	public boolean save(Degree degree);
	
	/**
	 * 删除使用程度
	 * @param degree
	 * @return
	 */
	public boolean delete(Degree degree);
	
	/**
	 * 更新使用程度
	 * @param degree
	 * @return
	 */
	public boolean update(Degree degree);
	
	/**
	 * 查询所有使用程度
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有使用程度带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询使用程度
	 * @param id
	 * @return
	 */
	public Degree getById(int id);
	
	/**
	 * 根据其他条件查询使用程度带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询使用程度
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
