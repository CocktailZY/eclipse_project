package com.ldh.action;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IUsersDao;
import com.ldh.model.Users;
import com.ldh.util.JsonUtil;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/users")
//表示当前Action所在命名空间
public class UsersAction{
	
	private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UsersDao")
	public void setUsersDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	/**
	 * 保存用户信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="save")
	public String save() throws IOException{
		String uName = ServletActionContext.getRequest().getParameter("uName");
		String uPassword = ServletActionContext.getRequest().getParameter("uPassword");
		String uPhone = ServletActionContext.getRequest().getParameter("uPhone");
		String uMail = ServletActionContext.getRequest().getParameter("uMail");
		String uFraction = ServletActionContext.getRequest().getParameter("uFraction");
		String uMoney = ServletActionContext.getRequest().getParameter("uMoney");
		
//		String hql = "from Users where uName="+uName+" and uPassword="+uPassword;
//		List<Object> list = usersDao.getAllByConds(hql);
//		if(list.size() == 0){
			Users user = new Users();
			user.setuName(uName);
			user.setuPassword(uPassword);
			user.setuPhone(uPhone);
			user.setuMail(uMail);
			user.setuSign(1);
			user.setuFraction(uFraction);
			user.setuMoney(uMoney);
			if(usersDao.save(user)){
				//save success
				JSONObject jobj = JSONObject.fromObject("{mes:\'保存成功!\',status:\'success\'}");
				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			}else{
				//save failed
				JSONObject jobj = JSONObject.fromObject("{mes:\'保存失败!\',status:\'error\'}");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			}
//		}else{
//			Users user = (Users) list.get(0);
//			if(usersDao.update(user)){
//				//save success
//				JSONObject jobj = JSONObject.fromObject("{mes:\'更新成功!\',status:\'success\'}");
//				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
//				ServletActionContext.getResponse().getWriter().write(jobj.toString());
//			}else{
//				//save failed
//				JSONObject jobj = JSONObject.fromObject("{mes:\'更新失败!\',status:\'error\'}");
//				ServletActionContext.getResponse().getWriter().write(jobj.toString());
//			}
//		}
		return null;
	}
	/**
	 * 删除用户信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="delete")
	public String delete() throws IOException{
		String userId = ServletActionContext.getRequest().getParameter("id");
		Users user = usersDao.getById(userId);
		if(usersDao.delete(user)){
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
	 * 修改用户信息
	 * @return
	 * @throws IOException 
	 */
	@Action(value="update")
	public String update() throws IOException{
		String userId = ServletActionContext.getRequest().getParameter("uId");
		String uName = ServletActionContext.getRequest().getParameter("uName");
		String uPassword = ServletActionContext.getRequest().getParameter("uPassword");
		String uPhone = ServletActionContext.getRequest().getParameter("uPhone");
		String uMail = ServletActionContext.getRequest().getParameter("uMail");
		String uFraction = ServletActionContext.getRequest().getParameter("uFraction");
		String uMoney = ServletActionContext.getRequest().getParameter("uMoney");
		String uSign = ServletActionContext.getRequest().getParameter("uSign");
		String uPicture = ServletActionContext.getRequest().getParameter("uploadHeadImg");
		Users user = usersDao.getById(userId);
		if(uName != null && "".equals(uName)){
			user.setuName(uName);
		}
		if(uPassword != null && !"".equals(uPassword)){
			user.setuPassword(uPassword);
		}
		if(uPhone != null && !"".equals(uPhone)){
			user.setuPhone(uPhone);
		}
		if(uMail != null && !"".equals(uMail)){
			user.setuMail(uMail);
		}
		if(uFraction != null && !"".equals(uFraction)){
			user.setuFraction(uFraction);
		}
		if(uMoney != null && !"".equals(uMoney)){
			user.setuMoney(uMoney);
		}
		if(uPicture != null && !"".equals(uPicture)){
			//uPicture = uPicture.substring(0, uPicture.lastIndexOf("/"));
			user.setuPicture(uPicture);
		}
		if(uSign != null && !"".equals(uSign)){
			user.setuSign(Integer.parseInt(uSign));
		}
		if(usersDao.update(user)){
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
	 * 根据id用户信息
	 * @return
	 * @throws IOException
	 */
	@Action(value="getById")
	public String getById() throws IOException{
		String userId = ServletActionContext.getRequest().getParameter("id");
		Users user = usersDao.getById(userId);
		if(user != null){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJson(user)+"}");
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
	 * 获取用户列表
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
		List<Object> userlist = usersDao.list();//获取所有用户数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = usersDao.listAll(page);//带分页
//		}
		if(userlist.size() > 0){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJsonByListObj(userlist)+"}");
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
	 * 获取用户列表
	 * @return
	 * @throws IOException
	 */
	@Action(value="listByConds")
	public String listByConds() throws IOException{
		String jsonConds = ServletActionContext.getRequest().getParameter("conds");
		JSONObject jsonObj = JSONObject.fromObject(jsonConds);
		String hql = "from Users where uSign !=2 and ";
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
		List<Object> userlist = usersDao.getAllByConds(hql);//获取所有用户数据，不带分页
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = usersDao.getByConds(hql,page);//带分页
//		}
		if(userlist.size() > 0){
			//save success
			JSONObject jobj = JSONObject.fromObject("{mes:\'获取成功!\',status:\'success\',data:"+JsonUtil.toJsonByListObj(userlist)+"}");
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
