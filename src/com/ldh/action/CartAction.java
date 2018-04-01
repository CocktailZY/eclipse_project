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
import com.ldh.model.Cart;
import com.ldh.model.Goods;
import com.ldh.model.Users;
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
		String good_id = ServletActionContext.getRequest().getParameter("good_id");
		String user_id = ServletActionContext.getRequest().getParameter("user_id");
		String good_total = ServletActionContext.getRequest().getParameter("good_total");
		String good_num = ServletActionContext.getRequest().getParameter("good_num");
		Cart cart = new Cart();
		cart.setcNumber(Integer.parseInt(good_num));
		cart.setcSubTotal(good_total);
		cart.setcGId(goodsDao.getById(good_id));
		cart.setcUId(userDao.getById(user_id));
		String hql = "from Cart where cUId='" + user_id +"' and cGId='" + good_id +"'";
		List<Object> cartlist = cartDao.getAllByConds(hql);
		JSONObject jobj = new JSONObject();
		if(cartlist.size() > 0){
			Cart temp = (Cart) cartlist.get(0);
			cart.setcId(temp.getcId());
			cart.setcNumber(temp.getcNumber()+cart.getcNumber());
			cart.setcSubTotal(String.valueOf((Double.valueOf(temp.getcSubTotal())+Double.valueOf(cart.getcSubTotal()))));
			if(cartDao.update(cart)){
				//save success
				jobj.put("mes", "添加成功!");
				jobj.put("status", "success");
			}else{
				//save failed
				jobj.put("mes", "添加失败!");
				jobj.put("status", "error");
			}
		}else{
			if(cartDao.save(cart)){
				//save success
				jobj.put("mes", "添加成功!");
				jobj.put("status", "success");
				
			}else{
				//save failed
				jobj.put("mes", "添加失败!");
				jobj.put("status", "error");
			}
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
		Users loginuser = (Users) ServletActionContext.getRequest().getSession().getAttribute("login_user");
		//分页
//		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
//		int pageNum = 1;
//		if(pageNumStr!=null && !"".equals(pageNumStr)){
//			pageNum = Integer.parseInt(pageNumStr);
//		}
//		List<Object> list = new ArrayList<Object>();
		JSONObject jobj = new JSONObject();
		String hql = "from Cart where cUId='"+loginuser.getuId()+"'";
		if(loginuser != null){
			List<Object> goodsTypelist = cartDao.getAllByConds(hql);//获取所有类型数据，不带分页
//			PageBean page=null;
//			if(userlist.size()>0){
//				page = new PageBean(userlist.size(),pageNum,5);
//				list = userDao.listAll(page);//带分页
//			}
			if(goodsTypelist.size() > 0){
//				for(int i = 0 ; i < goodsTypelist.size(); i++){
//					Cart item = (Cart) goodsTypelist.get(i);
//					Goods good = goodsDao.getById(item.getcGId())
//				}
				//save success
				jobj.put("mes", "获取成功!");
				jobj.put("status", "success");
				jobj.put("data", JsonUtil.toJsonByListObj(goodsTypelist));
			}else{
				//save failed
				jobj.put("mes", "获取失败!");
				jobj.put("status", "error");
			}
		}else{
			jobj.put("mes", "请先登录!");
			jobj.put("status", "error");
		}
		
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}

}
