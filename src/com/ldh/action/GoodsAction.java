package com.ldh.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IBrandDao;
import com.ldh.dao.IDegreeDao;
import com.ldh.dao.IGoodsDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.Goods;
import com.ldh.util.JsonUtil;
import com.ldh.util.PageBean;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/goods")
public class GoodsAction {
	
	private IGoodsDao goodsDao;
	private IBrandDao brandDao;
	private IDegreeDao degreeDao;
	private IUsersDao usersDao;

	public IBrandDao getBrandDao() {
		return brandDao;
	}
	@Resource(name="BrandDao")
	public void setBrandDao(IBrandDao brandDao) {
		this.brandDao = brandDao;
	}

	public IDegreeDao getDegreeDao() {
		return degreeDao;
	}
	@Resource(name="DegreeDao")
	public void setDegreeDao(IDegreeDao degreeDao) {
		this.degreeDao = degreeDao;
	}

	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public IGoodsDao getGoodsDao() {
		return goodsDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	@Action(value="save")
	public String save() throws IOException{
		String gName = ServletActionContext.getRequest().getParameter("gName");
		String gPrice = ServletActionContext.getRequest().getParameter("gPrice");
		String gDexcribe = ServletActionContext.getRequest().getParameter("gDexcribe");
		String gBId = ServletActionContext.getRequest().getParameter("gBId");
		String gDeId = ServletActionContext.getRequest().getParameter("gDeId");
		String gUId = ServletActionContext.getRequest().getParameter("gUId");
		
		Goods good = new Goods();
		good.setgName(gName);
		good.setgPrice(gPrice);
		good.setgDexcribe(gDexcribe);
		good.setgBId(brandDao.getById(gBId));
		good.setgDeId(degreeDao.getById(gDeId));
		good.setgUId(usersDao.getById(gUId));
		
		JSONObject jobj = new JSONObject();
		if(goodsDao.save(good)){
			//save success
			jobj.put("mes", "保存成功!");
			jobj.put("status", "success");
		}else{
			//save failed
			jobj.put("mes", "保存失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="delete")
	public String delete() throws IOException{
		String gId = ServletActionContext.getRequest().getParameter("gId");
		
		Goods good = goodsDao.getById(gId);
		
		JSONObject jobj = new JSONObject();
		if(goodsDao.delete(good)){
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
	
	@Action(value="update")
	public String update() throws IOException{
		String gId = ServletActionContext.getRequest().getParameter("gId");
		String gName = ServletActionContext.getRequest().getParameter("gName");
		String gPrice = ServletActionContext.getRequest().getParameter("gPrice");
		String gDexcribe = ServletActionContext.getRequest().getParameter("gDexcribe");
		String gBId = ServletActionContext.getRequest().getParameter("gBId");
		String gDeId = ServletActionContext.getRequest().getParameter("gDeId");
		String gUId = ServletActionContext.getRequest().getParameter("gUId");
		String gSign = ServletActionContext.getRequest().getParameter("uSign");
		
		Goods good = goodsDao.getById(gUId);
		if(gName != null && !"".equals(gName)){
			good.setgName(gName);
		}
		if(gPrice != null && !"".equals(gPrice)){
			good.setgPrice(gPrice);
		}
		if(gDexcribe != null && !"".equals(gDexcribe)){
			good.setgDexcribe(gDexcribe);
		}
		if(gBId != null && !"".equals(gBId)){
			good.setgBId(brandDao.getById(gBId));
		}
		if(gDeId != null && !"".equals(gDeId)){
			good.setgDeId(degreeDao.getById(gDeId));
		}
		if(gUId != null && !"".equals(gUId)){
			good.setgUId(usersDao.getById(gUId));
		}
		if(gSign != null && !"".equals(gSign)){
			good.setgSign(Integer.parseInt(gSign));
		}
		
		JSONObject jobj = new JSONObject();
		if(goodsDao.update(good)){
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
	
	@Action(value="getById")
	public String getById() throws IOException{
		String gId = ServletActionContext.getRequest().getParameter("gId");
		Goods good = goodsDao.getById(gId);
		JSONObject jobj = new JSONObject();
		if(good != null){
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
	
	@Action(value="list")
	public String list() throws IOException{
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		List<Object> goodlist = goodsDao.list();//获取所有用户数据，不带分页
		PageBean page=null;
		
		JSONObject jobj = new JSONObject();
		if(goodlist.size()>0){
			page = new PageBean(goodlist.size(),pageNum,5);
			list = goodsDao.listAll(page);//带分页
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(list));
		}else{
			jobj.put("mes", "获取失败!");
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	/**
	 * 获取用户列表
	 * @return
	 * @throws IOException
	 */
	@Action(value="listByConds")
	public String listByConds() throws IOException{
		String jsonConds = ServletActionContext.getRequest().getParameter("conds");
		JSONObject jsonObj = JSONObject.fromObject(jsonConds);
		String hql = "from Goods where";
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
		List<Object> goodlist = goodsDao.getAllByConds(hql);//获取所有用户数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = usersDao.getByConds(hql,page);//带分页
//		}
		JSONObject jobj = new JSONObject();
		if(goodlist.size() > 0){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(goodlist));
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
