package jsu.sif.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsu.sif.domain.Novel;
import jsu.sif.service.NovelService;


@WebServlet("/ReadAllNovelServlet")
public class ReadAllNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ReadAllNovelServlet");
		NovelService novelService = new NovelService();
		List<Novel> allNovels = novelService.readAllNovel();
		Iterator iterator = allNovels.iterator();
		while(iterator.hasNext()){
			Novel novel = (Novel)iterator.next();
			System.out.println(novel);
		}
		
		request.setAttribute("allNovels", allNovels);
		request.getRequestDispatcher("/novel.jsp").forward(request, response);
	}
}
