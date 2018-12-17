package jsu.sif.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.sql.visitor.functions.Length;
import com.sun.org.apache.bcel.internal.generic.NEW;

import jsu.sif.domain.Novel;
import jsu.sif.domain.NovelContent;
import jsu.sif.service.ChapterService;
import jsu.sif.service.NovelService;

@WebServlet("/ReadContentServlet")
public class ReadContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid = request.getParameter("nid");
		String cid = request.getParameter("cid");
		
		//读取小说
		NovelService novelService = new NovelService();
		Novel novel;
		novel = novelService.readOneNovel(Integer.parseInt(nid));
		
		//读取章节
		ChapterService chapterService = new ChapterService();
		NovelContent novelContent;
		novelContent = chapterService.readOneChapter(Integer.parseInt(nid),Integer.parseInt(cid));
		request.setAttribute("novel", novel);
		request.setAttribute("novelContent", novelContent);
		
		byte b[] = new byte[1024];
		StringBuilder content = null;
		FileInputStream fis = new FileInputStream("E://brother/novel/content"+novel.getNid()+"/chapter"+novelContent.getCid());
		int len = 0;
		while((len = fis.read(b))!=-1){
			content.append(new String(b,0,len));
		}
		
		if(novel!=null && novelContent!=null){
		request.getRequestDispatcher("/content.jsp").forward(request, response);
		}else{
			System.out.println("操作错误，已经读到底了");
		}
		
	}

}
