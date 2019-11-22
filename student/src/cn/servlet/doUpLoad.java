package cn.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dao.BaseDao;
import cn.dao.DealDao;
import cn.dao.impl.DealDaoimp;
import cn.entity.User;
import cn.service.UserService;
import cn.service.impl.UserServiceimp;

/**
 * Servlet implementation class doUpLoad
 */
@WebServlet("/doUpLoad")
public class doUpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doUpLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.getWriter().print("get方法没写 别尝试了");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String project = request.getParameter("project"); 
		String heart = request.getParameter("heart");
		String maxheart = request.getParameter("maxheart");
		String limheart = request.getParameter("limheart");
		String aveheart = request.getParameter("aveheart");
		String deal = request.getParameter("deal");
		String cal = request.getParameter("cal");
		DealDao dealDao = new DealDaoimp();		
		dealDao.doUpLoad(name, project, heart, maxheart, limheart, aveheart, deal, cal);

		response.getWriter().print("插入成功");
	}

}
