package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.service.Orderservice;
import cn.util.ProjectIntToString;

/**
 * Servlet implementation class upOrder
 */
@WebServlet("/upOrder")
public class upOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String order;
	Orderservice os;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upOrder() {
    	os = new Orderservice();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		order = request.getParameter("order");
		System.out.println(order);	
		os.addOrder(order);
		response.getWriter().print(order);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
