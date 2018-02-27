package com.ldh.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IBrandDao;
import com.ldh.model.Brand;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/brand")
public class BrandAction {
	
	private IBrandDao brandDao;
	
	public IBrandDao getUserDao() {
		return brandDao;
	}
	@Resource(name="BrandDao")
	public void setUserDao(IBrandDao brandDao) {
		this.brandDao = brandDao;
	}
	
	/**
	 * 保存品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String bDescribe = ServletActionContext.getRequest().getParameter("bDescribe");
		Brand brand = new Brand();
		brand.setbDescribe(bDescribe);
		if(brandDao.save(brand)){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'保存成功!\',status:\'success\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'保存失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}
	/**
	 * 删除品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		String goodsTypeId = ServletActionContext.getRequest().getParameter("id");
		Brand brand = brandDao.getById(goodsTypeId);
		if(brandDao.delete(brand)){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'删除成功!\',status:\'success\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'删除失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}
	/**
	 * 修改品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		String brandId = ServletActionContext.getRequest().getParameter("id");
		String brandName = ServletActionContext.getRequest().getParameter("typeName");
		Brand brand = new Brand();
		brand.setbId(brandId);
		brand.setbDescribe(brandName);
		if(brandDao.update(brand)){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'更新成功!\',status:\'success\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'更新失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}
	
	/**
	 * 根据id品牌(类型)信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="getById")
	public String getById() throws IOException{
		String goodsTypeId = ServletActionContext.getRequest().getParameter("id");
		Brand brand = brandDao.getById(goodsTypeId);
		if(brand != null){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJson(brand)+"}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
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
		List<Object> goodsTypelist = brandDao.list();//获取所有类型数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = userDao.listAll(page);//带分页
//		}
		if(goodsTypelist.size() > 0){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJsonByListObj(goodsTypelist)+"}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//save failed
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}

}
