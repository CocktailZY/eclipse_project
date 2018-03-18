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
import com.ldh.dao.IPictureDao;
import com.ldh.dao.IUsersDao;
import com.ldh.model.Brand;
import com.ldh.model.Degree;
import com.ldh.model.Goods;
import com.ldh.model.Picture;
import com.ldh.model.Users;
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
	private IPictureDao pictureDao;

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
	@Resource(name="GoodsDao")
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}
	
	public IPictureDao getPictureDao() {
		return pictureDao;
	}
	@Resource(name="PictureDao")
	public void setPictureDao(IPictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}
	@Action(value="save")
	public String save() throws IOException{
		String params = ServletActionContext.getRequest().getParameter("param");
		JSONObject obj = JSONObject.fromObject(params);
		String gName = obj.getString("gName");
		String gPrice = obj.getString("gPrice");
		String gDexcribe = obj.getString("gDexcribe");
		String gBId = obj.getString("gBId");
		String gDeId = obj.getString("gDeId");
		String gUId = obj.getString("gUId");
		String imgs = obj.getString("gPicture");
		
		
		Goods good = new Goods();
		good.setgName(gName);
		good.setgPrice(gPrice);
		good.setgDescribe(gDexcribe);
		good.setgBId(brandDao.getById(gBId));
		good.setgDeId(degreeDao.getById(gDeId));
		good.setgUId(usersDao.getById(gUId));
		
		JSONObject jobj = new JSONObject();
		String saveId = goodsDao.save(good);
		if(!"0".equals(saveId)){
			//save success
			String[] temp = imgs.substring(1, imgs.length()).substring(0, imgs.length()-1).replace("[", "").replace("]", "").split(",");
			for(int img = 0 ; img < temp.length; img++){
				String tempPath = temp[img].substring(1, temp[img].length());
				String imgPath = tempPath.substring(0, tempPath.length()-1);
				if(imgPath.indexOf("default.png") == -1){
					Picture tempPic = new Picture();
					tempPic.setpGId(goodsDao.getById(saveId));
					tempPic.setpUrl(imgPath);
					pictureDao.save(tempPic);
				}
			}
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
		String gDescribe = ServletActionContext.getRequest().getParameter("gDescribe");
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
		if(gDescribe != null && !"".equals(gDescribe)){
			good.setgDescribe(gDescribe);
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
		List<Object> picList = pictureDao.getAllByConds("from Picture where pGId='"+gId+"'");
		good.setgUId(usersDao.getById(good.getgUId().getuId()));
		good.setgDeId(degreeDao.getById(good.getgDeId().getDeId()));
		JSONObject jobj = new JSONObject();
		if(good != null){
			//save success
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("good", good);
			jobj.put("pic", picList.get(0));
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
		List<Object> imgObj = new ArrayList();
		if(goodlist.size()>0){
			page = new PageBean(goodlist.size(),pageNum,5);
			list = goodsDao.listAll(page);//带分页
			
			for(int p = 0 ; p < list.size() ; p++){
				Goods obj = (Goods)list.get(p);
				Brand bObj = brandDao.getById(obj.getgBId().getbId());
				Degree deObj = degreeDao.getById(obj.getgDeId().getDeId());
				List<Object> picList = pictureDao.getAllByConds("from Picture where pGId='"+obj.getgId()+"'");
				imgObj.add(picList);
				obj.setgBId(bObj);
				obj.setgDeId(deObj);
			}
			
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("data", JsonUtil.toJsonByListObj(list));
			jobj.put("picList", imgObj);
			jobj.put("pageTotal", page.getPageCount());
			jobj.put("pageNum", page.getPageNum());
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
		String uId = "";
		String hql = "from Goods where 1=1 and ";
		
		Iterator<String> sIterator = jsonObj.keys();  
		while(sIterator.hasNext()){  
		    // 获得key  
		    String key = sIterator.next();
		    String value = jsonObj.getString(key);
		    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
		    if("gUId".equals(key)){
				List<Object> ulist = usersDao.getAllByConds("from Users where uName='"+value+"'");
				if(ulist.size() > 0){
					Users obj = (Users) ulist.get(0);
					if(obj != null){
						value = obj.getuId();
						hql+=key+"='"+value+"'and ";
					}else{
						//没有查出用户
						continue;
					}
				}
				
		    }else if("gName".equals(key) || "gId".equals(key) || "gBId".equals(key)){
		    	hql+=key+"='"+value+"'and ";
		    }
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
		JSONObject jobj = new JSONObject();
		if(!"from Goods where 1=1 ".equals(hql)){
			List<Object> goodlist = goodsDao.getAllByConds(hql);//获取所有用户数据，不带分页
			List<Object> imgObj = new ArrayList();
			for(int p = 0 ; p < goodlist.size() ; p++){
				Goods obj = (Goods)goodlist.get(p);
				Brand bObj = brandDao.getById(obj.getgBId().getbId());
				Degree deObj = degreeDao.getById(obj.getgDeId().getDeId());
				List<Object> picList = pictureDao.getAllByConds("from Picture where pGId='"+obj.getgId()+"'");
				imgObj.add(picList);
				obj.setgBId(bObj);
				obj.setgDeId(deObj);
			}
			if(goodlist.size() > 0){
				//save success
				jobj.put("mes", "获取成功!");
				jobj.put("status", "success");
				jobj.put("data", JsonUtil.toJsonByListObj(goodlist));
				jobj.put("picList", imgObj);
			}else{
				//save failed
				jobj.put("mes", "获取失败!");
				jobj.put("status", "error");
			}
		}else{
			jobj.put("mes", "该用户不存在或没有发布商品!");
			jobj.put("status", "error");
		}
		
//		PageBean page=null;
//		if(userlist.size()>0){
//			page = new PageBean(userlist.size(),pageNum,5);
//			list = usersDao.getByConds(hql,page);//带分页
//		}
		
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="listByCondsWithPage")
	public String listByCondsWithPage() throws IOException{
		String jsonConds = ServletActionContext.getRequest().getParameter("conds");
		JSONObject jsonObj = JSONObject.fromObject(jsonConds);
		String uId = "";
		String hql = "from Goods where 1=1 and ";
		
		Iterator<String> sIterator = jsonObj.keys();  
		while(sIterator.hasNext()){  
		    // 获得key  
		    String key = sIterator.next();
		    String value = jsonObj.getString(key);
		    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
		    if("gUId".equals(key)){
				List<Object> ulist = usersDao.getAllByConds("from Users where uName='"+value+"'");
				if(ulist.size() > 0){
					Users obj = (Users) ulist.get(0);
					if(obj != null){
						value = obj.getuId();
						hql+=key+"='"+value+"'and ";
					}else{
						//没有查出用户
						continue;
					}
				}
				
		    }else if("gName".equals(key) || "gId".equals(key) || "gBId".equals(key)){
		    	hql+=key+"='"+value+"'and ";
		    }
//		    System.out.println("key: "+key+",value"+value);  
		} 
		//分页
		String pageNumStr = ServletActionContext.getRequest().getParameter("pageNum");
		int pageNum = 1;
		if(pageNumStr!=null && !"".equals(pageNumStr)){
			pageNum = Integer.parseInt(pageNumStr);
		}
		List<Object> list = new ArrayList<Object>();
		if(hql.lastIndexOf("and ") != -1){
			hql = hql.substring(0, hql.lastIndexOf("and "));
		}
		JSONObject jobj = new JSONObject();
		PageBean page=null;
		if(!"from Goods where 1=1 ".equals(hql)){
			List<Object> goodlist = goodsDao.list();//获取所有用户数据，不带分页
			page = new PageBean(goodlist.size(),pageNum,10);
			list = goodsDao.getByConds(hql,page);//带分页
			List<Object> imgObj = new ArrayList();
			for(int p = 0 ; p < goodlist.size() ; p++){
				Goods obj = (Goods)goodlist.get(p);
				Brand bObj = brandDao.getById(obj.getgBId().getbId());
				Degree deObj = degreeDao.getById(obj.getgDeId().getDeId());
				List<Object> picList = pictureDao.getAllByConds("from Picture where pGId='"+obj.getgId()+"'");
				imgObj.add(picList);
				obj.setgBId(bObj);
				obj.setgDeId(deObj);
			}
			if(goodlist.size() > 0){
				//save success
				jobj.put("mes", "获取成功!");
				jobj.put("status", "success");
				jobj.put("data", JsonUtil.toJsonByListObj(goodlist));
				jobj.put("picList", imgObj);
			}else{
				//save failed
				jobj.put("mes", "该类型下暂无商品!");
				jobj.put("status", "error");
			}
		}else{
			jobj.put("mes", "该用户不存在或没有发布商品!");
			jobj.put("status", "error");
		}
		
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	@Action(value="calAvgPrice")
	public String calAvgPrice() throws IOException{
		String bId = ServletActionContext.getRequest().getParameter("bId");
		String deId = ServletActionContext.getRequest().getParameter("deId");
		String hql = "from Goods where gBId='"+bId+"' and gDeId='"+deId+"'";
		List<Object> goodlist = goodsDao.getAllByConds(hql);//获取所有用户数据，不带分页
		JSONObject jobj = new JSONObject();
		if(goodlist.size() > 0){
			int avg = 0;
			int i = 0;
			for(Object good : goodlist){
				i++;
				Goods temp = (Goods) good;
				avg = avg + Integer.parseInt(temp.getgPrice());
			}
			int avgprice = avg / i; 
			jobj.put("mes", "获取成功!");
			jobj.put("status", "success");
			jobj.put("showAvg", true);
			jobj.put("avgprice", String.valueOf(avgprice));
		}else{
			//save failed
			jobj.put("mes", "获取失败!");
			jobj.put("showAvg", false);
			jobj.put("status", "error");
		}
		ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().write(jobj.toString());
		return null;
	}
	
	//后台对商品审核使用
	@Action(value="updateToSign")
	public String updateToSign() throws IOException{
		String gId = ServletActionContext.getRequest().getParameter("gId");
		int gSign = Integer.parseInt(ServletActionContext.getRequest().getParameter("gSign"));
		Goods good = goodsDao.getById(gId);
		good.setgSign(gSign);
		
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

}
