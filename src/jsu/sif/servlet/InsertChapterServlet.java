package jsu.sif.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mysql.fabric.xmlrpc.base.Value;
import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import jdk.management.resource.internal.inst.FileChannelImplRMHooks;
import jsu.sif.domain.NovelContent;
import jsu.sif.service.ChapterService;

@WebServlet("/InsertChapterServlet")
public class InsertChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        //获取小说id和章节id
        String nid = request.getParameter("nid");
        String scid = request.getParameter("cid");/*cid是小说原有的章节*/
        System.out.println("nid:"+nid+"  cid:"+scid);
        if(scid=="") scid = "0";
        int cid = Integer.parseInt(scid);
		//获取章节名
		String param = request.getParameter("chapter"); 
		String param1 = request.getParameter("content");

		//创建小说章节类
		NovelContent novelContent = new NovelContent();
		//获取章节名，章节内容路径
		novelContent.setChapter(new String(param.getBytes("ISO-8859-1"), "UTF-8")); 
		String path = "content"+nid+"/chapter"+(cid+1)+".txt";
		novelContent.setContent(path); 

		//获取内容
        String content = new String(param1.getBytes("ISO-8859-1"), "UTF-8");
        /*把内容存到文件夹里
         * nid代表某本小说
         */
        String filePath = "e:/brother/novel/"+path;
        System.out.println(filePath);
        File file = new File(filePath);
        if(!file.exists()){
        	file.createNewFile();
        }
        FileOutputStream fos= new FileOutputStream(file);
        byte[] b = content.getBytes();
        //输出到文件夹
        fos.write(b);
        fos.close();
        
        System.out.println(nid);
        
        //把数据保存到数据库
        ChapterService chapterService = new ChapterService();
        try {
        	chapterService.insertChapter(novelContent,nid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
		System.out.println(novelContent.toString());
		
		request.getRequestDispatcher("/ReadNovelServlet").forward(request, response);
	}

}
