package jsu.sif.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import jsu.sif.domain.Novel;
import jsu.sif.domain.User;
import jsu.sif.service.NovelService;

@WebServlet("/CreateNovelServlet")
public class CreateNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fName = "";
		String suffix = "";
		// 设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 接收表单里传的信息
		Map<String, String[]> parameterMap = (Map<String, String[]>) request.getParameterMap();
		// 查看信息
		System.out.println(parameterMap.keySet());
		// 创建一个novel对象
		Novel novel = new Novel();
		// 查看novel的内容
		System.out.println(novel.toString());
		// 存储到数据库中（图片另存本地某个地方)

		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取图片需要上传到的路径
		String path = "E:/brother/novel-img/";
		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> iter;
		try {
			// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
			iter = upload.parseRequest(request);
			// 获取表单的属性名字
			for(Iterator iterator = iter.iterator(); iterator.hasNext();) {
				FileItem item = (FileItem) iterator.next();
				if(item.isFormField()){
					if ("nname".equals(item.getFieldName())) {
                        novel.setNname(new String(item.getString("UTF-8")));
                    } else if ("nstatic".equals(item.getFieldName())) {
                    	novel.setNstatic(Integer.parseInt(item.getString()));
                    }else if ("nclass".equals(item.getFieldName())) {
                    	novel.setNclass(Integer.parseInt(item.getString()));
                    }else if ("nlable".equals(item.getFieldName())) {
                    	novel.setNlable(new String(item.getString("UTF-8")));
                    }else if ("nintro".equals(item.getFieldName())) {
                    	novel.setNintro(new String(item.getString("UTF-8")));
                    }
				}else{
					InputStream stream = item.getInputStream();
					System.out.println("---------------文件表单项");
					String fieldName = item.getFieldName();
					System.out.println("FieldName: " + fieldName);
					// 获取路径名
					String value = item.getName();
					System.out.println("value: " + value);
					/**
					 * 以下三步，主要获取 上传文件的名字
					 */
					// 获取路径名
					// 索引到最后一个反斜杠
					int start = value.lastIndexOf("\\");
					// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					String filename = value.substring(start + 1);
					// 真正写到磁盘上
					// 它抛出的异常 用exception 捕捉
					// item.write( new File(path,filename) );//第三方提供的
					// 手动写入的
					// 如果有文件名
					if (filename.indexOf(".") >= 0) {
						// 就截取.之前的字符串
						int indexdot = filename.indexOf(".");

						suffix = filename.substring(indexdot);
						System.out.println(filename);
						novel.setNlogo(filename);
						fName=path+filename;
//						fName = "e:/brotherDay/img/novel-img/" + filename;
						System.out.println(fName);
						OutputStream os = new FileOutputStream(new File(fName));
						IOUtils.copy(stream, os);
						IOUtils.closeQuietly(stream);
						IOUtils.closeQuietly(os);
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		//读取作者信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		novel.setNauthor(user.getUsername());
		System.out.println(novel.toString());
		if(novel.getNlogo()==null){
			novel.setNlogo("novel1.jpg");
		}
		//把novel存到数据库
		NovelService novelService = new NovelService();
		//返回的是小说的编号
		int nid = novelService.createNovel(novel);
		//创建该小说的路径
		String path1="e:/brother/novel/content"+nid;
		System.out.println(path1);
		File file = new File(path1);
		if(!file.exists()){
			file.mkdir();
		}
		request.getRequestDispatcher("/ReadNovelServlet").forward(request, response);

	}
}
