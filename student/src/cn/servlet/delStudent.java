package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Student;
import cn.entity.deal;
import cn.service.StudentService;
import cn.service.impl.StudentServiceimp;

/**
 * Servlet implementation class delStudent
 */
@WebServlet("/delStudent")
public class delStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public delStudent() {
        super();
        // TODO Auto-generated constructor stub
    }
	public void destroy() {
		super.destroy(); 
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentService studentService= new StudentServiceimp();
		int id = Integer.parseInt(request.getParameter("id"));
		deal de = new deal();
		de.setId(id);
		studentService.delStudent(de);
		response.sendRedirect("/student/pages/stuList.jsp");
	}

}
