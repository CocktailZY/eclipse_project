package com.ldh.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IAddressDao;
import com.ldh.dao.IGoodsDao;
import com.ldh.dao.IOrderDetailsDao;
import com.ldh.dao.IOrderInfoDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.Brand;
import com.ldh.model.Degree;
import com.ldh.model.Goods;
import com.ldh.model.OrderDetails;
import com.ldh.model.OrderInfo;
import com.ldh.model.Users;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/orderInfo")
public class OrderInfoAction {
	
	private IOrderInfoDao orderInfoDao;
	private IUsersDao userDao;
	private IAddressDao addressDao;
	private IGoodsDao goodsDao;
	private IOrderDetailsDao orderDetailsDao;
	
	public IOrderInfoDao getOrderInfoDao() {
		return orderInfoDao;
	}
	@Resource(name="OrderInfoDao")
	public void setOrderInfoDao(IOrderInfoDao orderInfoDao) {
		this.orderInfoDao = orderInfoDao;
	}
	
	public IAddressDao getAddressDao() {
		return addressDao;
	}
	@Resource(name="AddressDao")
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public IUsersDao getUserDao() {
		return userDao;
	}
	@Resource(name="UsersDao")
	public void setUserDao(IUsersDao userDao) {
		this.userDao = userDao;
	}
	public IOrderDetailsDao getOrderDetailsDao() {
		return orderDetailsDao;
	}
	@Resource(name="OrderDetailsDao")
	public void setOrderDetailsDao(IOrderDetailsDao orderDetailsDao) {
		this.orderDetailsDao = orderDetailsDao;
	}
	/**
	 * 保存订单信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String oTotal = ServletActionContext.getRequest().getParameter("oTotal");
		String params = ServletActionContext.getRequest().getParameter("info");
		JSONArray obj = JSONArray.fromObject(params);//详情
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		String oComplete = ServletActionContext.getRequest().getParameter("oComplete");
//		int oSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("oSign"));
		String oUId = ServletActionContext.getRequest().getParameter("oUId");
//		String oAId = ServletActionContext.getRequest().getParameter("oAId");
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setoTotal(oTotal);
		orderInfo.setoDetermine(df.format(new Date()));//下单时间
//		orderInfo.setoComplete(oComplete);
		orderInfo.setoSign(1);
		orderInfo.setoUId(userDao.getById(oUId));
//		orderInfo.setoAId(addressDao.getById(oAId));
		JSONObject jobj = new JSONObject();
		String saveOId = orderInfoDao.save(orderInfo);
		boolean flag = false;
		if(!"0".equals(saveOId) && null != saveOId){
			//save success
			for(int i = 0 ; i < obj.size() ; i++){
				OrderDetails tempDetail = new OrderDetails();
				JSONObject json = JSONObject.fromObject(obj.get(i));
				tempDetail.setdGId(goodsDao.getById(json.getString("gId")));
				tempDetail.setdNumber(json.getString("gNum"));
				tempDetail.setdOId(orderInfoDao.getById(saveOId));
				tempDetail.setdSubTotal(json.getString("gSubTotal"));
				tempDetail.setdUId(userDao.getById(goodsDao.getById(json.getString("gId")).getgUId().getuId()));
				flag = orderDetailsDao.save(tempDetail);
			}
			if(flag){
				jobj.put("mes", "保存成功!");
				jobj.put("status", "success");
			}else{
				//save failed
				jobj.put("mes", "详情保存失败!");
				jobj.put("status", "error");
			}
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
	
	/**
	 * 根据条件查询订单
	 * @return
	 * @throws IOException
	 */
	@Action(value="listByConds")
	public String listByConds() throws IOException{
		String jsonConds = ServletActionContext.getRequest().getParameter("conds");
		JSONObject jsonObj = JSONObject.fromObject(jsonConds);
		String hql = "from OrderInfo where uSign !=0 and ";
		Iterator<String> sIterator = jsonObj.keys();  
		while(sIterator.hasNext()){  
		    // 获得key  
		    String key = sIterator.next();  
		    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
		    String value = jsonObj.getString(key);
		    hql+=key+"='"+value+"'and ";
//		    System.out.println("key: "+key+",value"+value);  
		} 
		//分页
//		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
//		int pageNum = 1;
//		if(pageNumStr!=null && !"".equals(pageNumStr)){
//			pageNum = Integer.parseInt(pageNumStr);
//		}
//		List<Object> list = new ArrayList<Object>();
		if(hql.lastIndexOf("and ") != -1){
			hql = hql.substring(0, hql.lastIndexOf("and "));
		}
		List<Object> orderlist = orderInfoDao.getAllByConds(hql);//获取所有用户数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = usersDao.getByConds(hql,page);//带分页
//		}
		if(orderlist.size() > 0){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJsonByListObj(orderlist)+"}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'无符合条件的用户!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}

}
