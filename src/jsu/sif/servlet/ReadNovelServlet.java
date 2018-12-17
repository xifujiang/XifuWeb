package jsu.sif.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsu.sif.domain.Novel;
import jsu.sif.domain.NovelContent;
import jsu.sif.domain.NovelType;
import jsu.sif.domain.User;
import jsu.sif.service.NovelService;
import jsu.sif.service.NovelTypeService;

@WebServlet("/ReadNovelServlet")
public class ReadNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		/*读取该作者写的小说的信息*/
		NovelService novelService = new NovelService();
		List<Novel> novels = novelService.readNovel(user.getUsername());
//		List<Novel> novels = novelService.readNovel("xifu");
		
		/*读取小说类型*/
		List<NovelType> novelTypes = new ArrayList<>();
		novelTypes = new NovelTypeService().readType();
		
		//输出获取到的数据
		Iterator iterator =novels.iterator();
		while(iterator.hasNext()){
			Novel novel = (Novel) iterator.next();
			System.out.println(novel.toString());
			if(novel.getChapters()==null){
				novel.getChapters().add(new NovelContent(0));
			}
		}
		
		
		//把数据写到request域
//		HttpSession session = request.getSession();
//		session.setAttribute("authorNovels", novels);
		request.setAttribute("authorNovels", novels);
		request.setAttribute("novelTypes", novelTypes);
		//转发
		request.getRequestDispatcher("/author-novel-manage.jsp").forward(request, response);
	}
}
