package com.ldh.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.aspectj.util.FileUtil;
import org.springframework.context.annotation.Scope;

import net.sf.json.JSONObject;

@Scope("prototype")
@ParentPackage("struts-default")
//表示继承的父包
@Namespace(value = "/upload")
//表示当前Action所在命名空间
public class UploadAction {
	private File file;
	private String fileContentType;
	private String fileFileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * 上传
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws IllegalStateException 
	 */
	@Action(value="uploadImg",interceptorRefs={@InterceptorRef(value="fileUpload",params={"allowTypes","image/bmp,image/png,image/gif,image/jpeg,image/pjpeg",
		     "maximumSize","5242880"}),@InterceptorRef("defaultStack")})
	public String uploadImg() throws IOException, IllegalStateException, ServletException{
		System.out.println("fileName:"+this.getFileFileName());
        System.out.println("contentType:"+this.getFileContentType());
        System.out.println("File:"+this.getFile());
        
        //获取要保存文件夹的物理路径(绝对路径)
        String realPath=ServletActionContext.getServletContext().getRealPath("/");
        String temp = realPath.substring(0, realPath.length()-1);
        String savePath = temp.substring(0, temp.lastIndexOf("\\"))+"\\upload_files";
        File tempfile = new File(savePath);
        
        //测试此抽象路径名表示的文件或目录是否存在。若不存在，创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
        if(!tempfile.exists())tempfile.mkdirs();
        
        try {
            //保存文件
            FileUtils.copyFile(file, new File(tempfile,fileFileName));
            String resultPath = savePath + "\\" + fileFileName;
            JSONObject jobj = JSONObject.fromObject("{mes:\'上传成功!\',status:\'success\'}");
            jobj.put("data", resultPath);
            jobj.put("imgName", fileFileName);
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject jobj = JSONObject.fromObject("{mes:\'上传失败!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
        }
		return null;
	}
	
	@Action(value="readImg")
	public String readImg() throws IOException{
		String name = ServletActionContext.getRequest().getParameter("name");
		String type = ServletActionContext.getRequest().getParameter("type");
		String realPath=ServletActionContext.getServletContext().getRealPath("/");
        String temp = realPath.substring(0, realPath.length()-1);
        String filepath = temp.substring(0, temp.lastIndexOf("\\"))+"\\upload_files";
		try {
//			tempfile = tempfile.replace(/\\/g,'/');
			String imgPath = filepath + "\\" + name;
			FileInputStream in = new FileInputStream("F:/liuxiaolong/apache-tomcat-7.0.82/webapps/upload_files/D_head.jpg"); 
			byte data[] = new byte[in.available()];
			in.read(data);
			OutputStream toClient = ServletActionContext.getResponse().getOutputStream(); // 得到向客户端输出二进制数据的对象
			toClient.write(data); // 输出数据
			toClient.flush();
			toClient.close();
			in.close();
			
		} catch (FileNotFoundException e) {
			// 要找的文件不存在
			JSONObject jobj = JSONObject.fromObject("{mes:\'文件不存在!\',status:\'error\'}");
			ServletActionContext.getResponse().setHeader("content-type", "text/html;charset=UTF-8");
			ServletActionContext.getResponse().getWriter().write(jobj.toString());
		} // 以byte流的方式打开文件 d:\1.gif
		return null;
	}
	
	
}
