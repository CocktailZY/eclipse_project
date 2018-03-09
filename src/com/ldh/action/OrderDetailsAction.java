package com.ldh.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IExpressDao;
import com.ldh.dao.IGoodsDao;
import com.ldh.dao.IOrderDetailsDao;
import com.ldh.dao.IOrderInfoDao;
import com.ldh.model.OrderDetails;
import com.ldh.model.OrderInfo;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/orderDetails")
public class OrderDetailsAction {
	
	private IOrderDetailsDao orderDetailsDao;
	private IGoodsDao goodsDao;
	private IExpressDao expressDao;
	private IOrderInfoDao orderInfoDao;
	
	public IOrderDetailsDao getOrderDetailsDao() {
		return orderDetailsDao;
	}
	@Resource(name="OrderDetailsDao")
	public void setOrderDetailsDao(IOrderDetailsDao orderDetailsDao) {
		this.orderDetailsDao = orderDetailsDao;
	}
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public IExpressDao getExpressDao() {
		return expressDao;
	}
	@Resource(name="ExpressDao")
	public void setExpressDao(IExpressDao expressDao) {
		this.expressDao = expressDao;
	}
	
	public IOrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}
	@Resource(name="OrderInfoDao")
	public void setOrderInfoDao(IOrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}
	
	
	/**
	 * 保存订单详情信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String dSubTotal = ServletActionContext.getRequest().getParameter("dSubTotal");
		String dNumber = ServletActionContext.getRequest().getParameter("dNumber");
		String dGId = ServletActionContext.getRequest().getParameter("dGId");
		String dExId = ServletActionContext.getRequest().getParameter("dExId");
		String dOId = ServletActionContext.getRequest().getParameter("dOId");
		
		OrderDetails orderDetails = new OrderDetails();
		
		orderDetails.setdSubTotal(dSubTotal);
		orderDetails.setdNumber(dNumber);
		orderDetails.setdGId(goodsDao.getById(dGId));
		orderDetails.setdExId(expressDao.getById(dExId));
		orderDetails.setdOId(orderInfoDao.getById(dOId));
		JSONObject jobj = new JSONObject();
		if(orderDetailsDao.save(orderDetails)){
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
	 * 删除订单详情信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		
		String dId = ServletActionContext.getRequest().getParameter("dId");
		OrderDetails orderDetails = orderDetailsDao.getById(dId);
		JSONObject jobj = new JSONObject();
		if(orderDetailsDao.delete(orderDetails)){
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
	 * 修改订单详情信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		
		String bId = ServletActionContext.getRequest().getParameter("bId");
		String dExId = ServletActionContext.getRequest().getParameter("dExId");
		OrderDetails orderDetails = orderDetailsDao.getById(bId);
		if(dExId != null && !"".equals(dExId)){
			orderDetails.setdExId(expressDao.getById(dExId));
		}
		JSONObject jobj = new JSONObject();
		if(orderDetailsDao.update(orderDetails)){
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
		String bId = ServletActionContext.getRequest().getParameter("bId");
		OrderDetails orderDetails = orderDetailsDao.getById(bId);
		JSONObject jobj = new JSONObject();
		if(orderDetails != null){
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
		List<Object> goodsTypelist = orderDetailsDao.list();//获取所有类型数据，不带分页
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
