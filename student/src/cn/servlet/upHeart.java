package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dao.BaseDao;

/**
 * Servlet implementation class upHeart
 */
@WebServlet("/upHeart")
public class upHeart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int heartbeat;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upHeart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		heartbeat = Integer.parseInt(request.getParameter("heartbeat"));
		System.out.println(heartbeat);
		response.getWriter().print(heartbeat);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().print(heartbeat);
	}

}
