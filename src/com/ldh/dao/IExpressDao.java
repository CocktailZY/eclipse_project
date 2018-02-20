package com.ldh.dao;

import java.util.List;

import com.ldh.model.Express;
import com.ldh.util.PageBean;

public interface IExpressDao {
	/**
	 * 新增快递
	 * @param express
	 * @return
	 */
	public boolean save(Express express);
	
	/**
	 * 删除快递
	 * @param express
	 * @return
	 */
	public boolean delete(Express express);
	
	/**
	 * 更新快递
	 * @param express
	 * @return
	 */
	public boolean update(Express express);
	
	/**
	 * 查询所有快递
	 * @return
	 */
	public List<Object> list();
	
	/**
	 * 查询所有快递带分页
	 * @return
	 */
	public List<Object> listAll(PageBean page);
	
	/**
	 * 根据主键id查询快递
	 * @param id
	 * @return
	 */
	public Express getById(int id);
	
	/**
	 * 根据其他条件查询快递带分页
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getByConds(String hql,PageBean page);
	
	/**
	 * 根据其他条件查询快递
	 * @param hql 查询语句
	 * @return
	 */
	public List<Object> getAllByConds(String hql);

}
