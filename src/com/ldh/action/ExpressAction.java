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
import com.ldh.model.Express;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/express")
public class ExpressAction {
	
	private IExpressDao expressDao;
	
	public IExpressDao getExpressDao() {
		return expressDao;
	}
	@Resource(name="ExpressDao")
	public void setExpressDao(IExpressDao expressDao) {
		this.expressDao = expressDao;
	}
	
	/**
	 * 保存品牌(类型)信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String eExpress = ServletActionContext.getRequest().getParameter("eExpress");
		Express express = new Express();
		express.seteExpress(eExpress);
		JSONObject jobj = new JSONObject();
		if(expressDao.save(express)){
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
		String eId = ServletActionContext.getRequest().getParameter("eId");
		Express express = expressDao.getById(eId);
		JSONObject jobj = new JSONObject();
		if(expressDao.delete(express)){
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
		String eId = ServletActionContext.getRequest().getParameter("eId");
		String eExpress = ServletActionContext.getRequest().getParameter("eExpress");
		Express express = expressDao.getById(eId);
		if(eExpress != null && !"".equals(eExpress)){
			express.seteExpress(eExpress);
		}
		JSONObject jobj = new JSONObject();
		if(expressDao.update(express)){
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
		String eId = ServletActionContext.getRequest().getParameter("eId");
		Express express = expressDao.getById(eId);
		JSONObject jobj = new JSONObject();
		if(express != null){
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
		List<Object> goodsTypelist = expressDao.list();//获取所有类型数据，不带分页
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
