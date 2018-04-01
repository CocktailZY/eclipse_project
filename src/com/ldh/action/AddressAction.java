package com.ldh.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IAddressDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.Address;
import com.ldh.util.JsonUtil;
import com.ldh.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/address")
public class AddressAction {
	
	private IAddressDao addressDao;
	private IUsersDao usersDao;
	
	public IAddressDao getAddressDao() {
		return addressDao;
	}
	@Resource(name="AddressDao")
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	
	/**
	 * 保存地址信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String aDescribe = ServletActionContext.getRequest().getParameter("aDescribe");
		String aUId = ServletActionContext.getRequest().getParameter("aUId");
		Address address = new Address();
		address.setaDescribe(aDescribe);
		address.setaUId(usersDao.getById(aUId));
		address.setaSign(1);
		JSONObject jobj = new JSONObject();
		if(addressDao.save(address)) {
			jobj.put("mes", "保存成功!");
			jobj.put("status", "success");
		}else {
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
		
	}
	/**
	 * 删除地址信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		
		
		String aId = ServletActionContext.getRequest().getParameter("aId");
		Address address = addressDao.getById(aId);
		JSONObject jobj = new JSONObject();
		if(addressDao.delete(address)){
			//save success
			jobj.put("mes", "删除成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "删除失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	/**
	 * 修改地址信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		
		String aId = ServletActionContext.getRequest().getParameter("aId");
		String aDescribe = ServletActionContext.getRequest().getParameter("aDescribe");
		int aSign =  Integer.parseInt(ServletActionContext.getRequest().getParameter("aSign"));
		Address address = addressDao.getById(aId);
		if(aDescribe != null && !"".equals(aDescribe) ) {
			address.setaDescribe(aDescribe);
			address.setaSign(aSign);
		}
		JSONObject jobj = new JSONObject();
		
		if(addressDao.update(address)) {
			jobj.put("mes", "更新成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "更新失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	/**
	 * 根据id信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="getById")
	public String getById() throws IOException{
		String aId = ServletActionContext.getRequest().getParameter("aid");
		Address address = addressDao.getById(aId);
		JSONObject jobj = new JSONObject();
		if(address != null){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	/**
	 * 获取品牌(类型)列表
	 * @return
	 * @throws IOException
	 */
	@Action(value="list")
	public String list() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> addressTypelist = addressDao.list();//获取所有类型数据，不带分页
		PageBean page=null;
		if(addressTypelist.size()>0){
			page = new PageBean(addressTypelist.size(),pageNum,5);
			list = addressDao.listAll(page);//带分页
		}
		JSONObject jobj = new JSONObject();
		if(addressTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(list));
			jobj.put("pageTotal", page.getPageCount());
			jobj.put("pageNum", page.getPageNum());
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="listAll")
	public String listAll() throws IOException{
		//分页
//		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
//		int pageNum = 1;
//		if(pageNumStr!=null && !"".equals(pageNumStr)){
//			pageNum = Integer.parseInt(pageNumStr);
//		}
//		List<Object> list = new ArrayList<Object>();
		List<Object> addressTypelist = addressDao.list();//获取所有类型数据，不带分页
//		PageBean page=null;
//		if(addressTypelist.size()>0){
//			page = new PageBean(addressTypelist.size(),pageNum,5);
//			list = addressDao.listAll(page);//带分页
//		}
		JSONObject jobj = new JSONObject();
		if(addressTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(addressTypelist));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

}
