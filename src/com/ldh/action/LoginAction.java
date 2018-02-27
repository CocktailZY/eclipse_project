package com.ldh.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;

import com.ldh.dao.IUsersDao;
import com.ldh.model.Users;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/login")
//表示当前Action所在命名空间
public class LoginAction {
	
	private IUsersDao usersDao;
	
	public IUsersDao getUsersDao() {
		return usersDao;
	}
	@Resource(name="UserDao")
	public void setUserDao(IUsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	/**
	 * 登录
	 * @return
	 * @throws IOException 
	 */
	@Action(value="login")
	public String login() throws IOException{
		String username = ServletActionContext.getRequest().getParameter("username");
		String password = ServletActionContext.getRequest().getParameter("password");
		String hql = "from Users where username="+username+" and password="+password;
		List<Object> list = usersDao.getAllByConds(hql);
		if(list.size() > 0){
			Users loginUser = (Users) list.get(0);
			//user exist
			ServletActionContext.getRequest().getSession().setAttribute("login_user",loginUser);
			JSONObject jobj = JSONObject.fromObject("{mes:\'用户存在!\',status:\'success\',user:"+loginUser+"}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}else{
			//user not exist or password is not right
			List<Object> templist = usersDao.getAllByConds("from Users where username="+username);
			if(list.size() > 0){
				JSONObject jobj = JSONObject.fromObject("{mes:\'密码不正确!\',status:\'failed\'}");
				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			}else{
				JSONObject jobj = JSONObject.fromObject("{mes:\'用户不存在!\',status:\'failed\'}");
				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			}
		}
		return null;
	}
	
	@Action(value="logout")
	public String logout() throws IOException{
		try{
			ServletActionContext.getRequest().getSession().removeAttribute("login_user");
			JSONObject jobj = JSONObject.fromObject("{mes:\'注销成功!\',status:\'success\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}catch(Exception e){
			JSONObject jobj = JSONObject.fromObject("{mes:\'注销失败!\',status:\'failed\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		}
		return null;
	}
	
	@Action(value="checkName")
	public String checkName(){
		String username = ServletActionContext.getRequest().getParameter("aaa");
		List<Object> templist = usersDao.getAllByConds("from Users where username='"+username+"'");
		if(templist.size() > 0){
			try {
				JSONObject jobj = JSONObject.fromObject("{mes:\'用户名已存在!\',status:\'failed\'}");
				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				JSONObject jobj = JSONObject.fromObject("{mes:\'用户名可以使用!\',status:\'success\'}");
				ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
				ServletActionContext.getResponse().getWriter().write(jobj.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
