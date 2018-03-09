package com.ldh.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.ICartDao;
import com.ldh.dao.IGoodsDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.Brand;
import com.ldh.model.Cart;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/cart")
public class CartAction {
	
	private ICartDao cartDao;
	private IGoodsDao goodsDao;
	private IUsersDao userDao;
	
	public ICartDao getCartDao() {
		return cartDao;
	}
	@Resource(name="CartDao")
	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}
	
	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	
	public IUsersDao getUsersDao() {
		return userDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao userDao) {
		this.userDao = userDao;
	}
	
	
	/**
	 * 保存购物车信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		int cNumber = Integer.parseInt(ServletActionContext.getRequest().getParameter("cNumber"));
		String cSubTotal = ServletActionContext.getRequest().getParameter("cSubTotal");
		String cGId = ServletActionContext.getRequest().getParameter("cGId");
		String cUId = ServletActionContext.getRequest().getParameter("cUId");
		
		Cart cart = new Cart();
		cart.setcNumber(cNumber);
		cart.setcSubTotal(cSubTotal);
		cart.setcGId(goodsDao.getById(cGId));
		cart.setcUId(userDao.getById(cUId));
		
		JSONObject jobj = new JSONObject();
		if(cartDao.save(cart)){
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
	 * 删除购物车信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		String cId = ServletActionContext.getRequest().getParameter("cId");
		Cart cart = cartDao.getById(cId);
		
		JSONObject jobj = new JSONObject();
		if(cartDao.delete(cart)){
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
	 * 修改购物车信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		
		
		String cId = ServletActionContext.getRequest().getParameter("cId");
		int cNumber = Integer.parseInt(ServletActionContext.getRequest().getParameter("cNumber"));
		String cSubTotal = ServletActionContext.getRequest().getParameter("cSubTotal");
		
		Cart cart = cartDao.getById(cId);
		cart.setcNumber(cNumber);;
		cart.setcSubTotal(cSubTotal);
		JSONObject jobj = new JSONObject();
		if(cartDao.update(cart)){
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
		String cId = ServletActionContext.getRequest().getParameter("cId");
		Cart cart = cartDao.getById(cId);
		JSONObject jobj = new JSONObject();
		if(cart != null){
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
		List<Object> goodsTypelist = cartDao.list();//获取所有类型数据，不带分页
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
