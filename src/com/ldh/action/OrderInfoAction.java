package com.ldh.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IAddressDao;
import com.ldh.dao.IOrderInfoDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.OrderInfo;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/orderInfo")
public class OrderInfoAction {
	
	private IOrderInfoDao orderInfoDao;
	private IUsersDao userDao;
	private IAddressDao addressDao;
	
	public IOrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}
	@Resource(name="OrderInfoDao")
	public void setOrderInfoDao(IOrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}
	
	public IUsersDao getUsersDao() {
		return userDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao userDao) {
		this.userDao = userDao;
	}
	
	public IAddressDao getAddressDao() {
		return addressDao;
	}
	@Resource(name="AddressDao")
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	/**
	 * 保存订单信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String oTotal = ServletActionContext.getRequest().getParameter("oTotal");
		String oDetermine = ServletActionContext.getRequest().getParameter("oDetermine");
		String oComplete = ServletActionContext.getRequest().getParameter("oComplete");
		int oSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("oSign"));
		String oUId = ServletActionContext.getRequest().getParameter("oUId");
		String oAId = ServletActionContext.getRequest().getParameter("oAId");
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setoTotal(oTotal);
		orderInfo.setoDetermine(oDetermine);
		orderInfo.setoComplete(oComplete);
		orderInfo.setoSign(oSign);
		orderInfo.setoUId(userDao.getById(oUId));
		orderInfo.setoAId(addressDao.getById(oAId));
		JSONObject jobj = new JSONObject();
		if(orderInfoDao.save(orderInfo)){
			//save success
			jobj.put("mes", "保存成功!");
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
	 * 删除品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		
		String oId = ServletActionContext.getRequest().getParameter("oId");
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		JSONObject jobj = new JSONObject();
		if(orderInfoDao.delete(orderInfo)){
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
	 * 修改品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		String oId = ServletActionContext.getRequest().getParameter("oId");
		int oSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("oSign"));
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		orderInfo.setoSign(oSign);
		JSONObject jobj = new JSONObject();
		if(orderInfoDao.update(orderInfo)){
			//save success
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
	 * 根据id品牌(类型)信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="getById")
	public String getById() throws IOException{
		String oId = ServletActionContext.getRequest().getParameter("oId");
		OrderInfo orderInfo = orderInfoDao.getById(oId);
		JSONObject jobj = new JSONObject();
		if(orderInfo != null){
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
//		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
//		int pageNum = 1;
//		if(pageNumStr!=null && !"".equals(pageNumStr)){
//			pageNum = Integer.parseInt(pageNumStr);
//		}
//		List<Object> list = new ArrayList<Object>();
		List<Object> goodsTypelist = orderInfoDao.list();//获取所有类型数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = userDao.listAll(page);//带分页
//		}
		JSONObject jobj = new JSONObject();
		if(goodsTypelist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(goodsTypelist));
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
