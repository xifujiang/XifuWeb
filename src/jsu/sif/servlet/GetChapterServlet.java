package jsu.sif.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import jsu.sif.domain.Novel;
import jsu.sif.domain.NovelContent;
import jsu.sif.service.ChapterService;
import jsu.sif.service.NovelService;

@WebServlet("/GetChapterServlet")
public class GetChapterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nid =  request.getParameter("nid");
		System.out.println(nid);
		
		//读取该小说
		NovelService novelService = new NovelService();
		Novel novel = novelService.readOneNovel(Integer.parseInt(nid));
		System.out.println(novel);
		
		//读取该小说的章节 
		ChapterService chapterService = new ChapterService();
		List<NovelContent> allchapters = new ArrayList<>();
		allchapters = chapterService.readAllChapter(nid);
		System.out.println(allchapters);
		
		//把该小说和该章节内容传到request域
		request.setAttribute("novel", novel);
		request.setAttribute("allchapters", allchapters);
		//转发
		request.getRequestDispatcher("/catalog.jsp").forward(request, response);
	}
}
