package jsu.sif.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsu.sif.service.NovelService;

@WebServlet("/DeleteNovelServlet")
public class DeleteNovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteNovelServlet");
		String nid = request.getParameter("nid");
		System.out.println(nid);
		NovelService novelService = new NovelService();
		try {
			novelService.delete(Integer.parseInt(nid));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/author-novel-manage.jsp").forward(request, response);

	}
}
