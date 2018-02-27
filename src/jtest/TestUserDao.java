package jtest;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldh.dao.impl.UsersDaoImpl;
import com.ldh.model.Users;
import com.ldh.util.JsonUtil;
import com.ldh.util.PageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestUserDao extends AbstractJUnit4SpringContextTests {
	
	@Resource
    private UsersDaoImpl userDao;

	@Test
	public void testSave() {
		Users user = new Users();
		user.setuId(UUID.randomUUID().toString());
		user.setuName("admin2");
		user.setuPassword("admin2");
		System.out.println(userDao.save(user));
	}

	@Test
	public void testDelete() {
		Users user = userDao.getById("402881ed61d7a4a80161d7a4ac160000");
		System.out.println(userDao.delete(user));
	}

	@Test
	public void testUpdate() {
		Users user = new Users();
		user.setuId("402881ed61d7ab130161d7ab16e60000");
		user.setuName("admin1");
		user.setuPassword("admin1");
		System.out.println(userDao.update(user));
	}

	@Test
	public void testList() {
		List<Object> list = userDao.list();
		for(Object temp : list){
			System.out.println(JsonUtil.objectToJson(temp));
		}
	}

	@Test
	public void testListAll() {
		List<Object> userlist = userDao.list();
		PageBean page = new PageBean(userlist.size(),1,1);
		List<Object> list = userDao.listAll(page);
		for(Object temp : list){
			System.out.println(JsonUtil.objectToJson(temp));
		}
	}

	@Test
	public void testGetById() {
		System.out.println(JsonUtil.objectToJson(userDao.getById("402881ed61d7b0290161d7b02e1c0000")));
	}

	@Test
	public void testGetByConds() {
		String hql = "from Users where uSign=0";
		List<Object> userlist = userDao.getAllByConds(hql);
		PageBean page = new PageBean(userlist.size(),1,2);
		List<Object> templist = userDao.getByConds(hql,page);//带分页
		for(Object temp : templist){
			System.out.println(JsonUtil.objectToJson(temp));
		}
	}

	@Test
	public void testGetAllByConds() {
		String hql = "from Users where uName='admin1'";
		List<Object> userlist = userDao.getAllByConds(hql);
		for(Object temp : userlist){
			System.out.println(JsonUtil.objectToJson(temp));
		}
	}

}
