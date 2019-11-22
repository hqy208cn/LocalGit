package cn.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.Grade;
import cn.entity.Student;
import cn.service.GradeService;
import cn.service.StudentService;
import cn.service.impl.GradeServiceimp;
import cn.service.impl.StudentServiceimp;

/**
 * Servlet implementation class addStudent
 */
@WebServlet("/addStudent")
public class addStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");//防止中文参数乱码
		String name = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("stuAge"));
		String gradeName = request.getParameter("stuGrade");
		String profile = request.getParameter("showProfile");


		GradeService gService =new GradeServiceimp();
		Grade grade = gService.getGrade(gradeName);
		Student stu = new Student();
		stu.setAge(age);
		stu.setName(name);
		stu.setSex(sex);
		stu.setProfile(profile);
		stu.setGradeId(grade.getGradeId());


		StudentService studentService = new StudentServiceimp();
		
		if(studentService.findStudent(stu)){
			response.sendRedirect("/student/pages/stuList.jsp");
		}
		else{
			String opr = request.getParameter("opr");
			int n = 0 ;
			if(opr.equals("addStu")){
				n = studentService.addStudent(stu);
			}
			else if(opr.equals("modifyStu")){
				int id =Integer.parseInt(request.getParameter("stuId"));
				stu.setId(id);
				n = studentService.modifyStudent(stu);
			}
			if(n>0){                                                       //n就是return的row，数据库操作的行数
				response.sendRedirect("/student/pages/stuList.jsp");
			}
			else{
				response.sendRedirect("/student/pages/addStu.jsp");
			}
		}
		
	}
	public void init() throws ServletException {
	}
}
