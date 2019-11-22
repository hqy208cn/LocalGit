package cn.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.entity.User;
import cn.service.UserService;
import cn.service.impl.UserServiceimp;

/**
 * Servlet implementation class dologin
 */
@WebServlet("/doLogin")
public class doLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void destory(){
    	super.destroy();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null && !username.equals("")){
			UserService uerService = new UserServiceimp();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			if(uerService.findUser(user)){//存在这个用户，可以正常访问学生信息
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/student/pages/stuList.jsp");
				
			}else{//不存在这个用户，给出提示，转回登录页面
				String message = "用户名或密码错误";
				request.getSession().setAttribute("msg", message);
				response.sendRedirect("/student/login.jsp");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username!=null && !username.equals("")){
			UserService uerService = new UserServiceimp();
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			if(uerService.findUser(user)){//存在这个用户，可以正常访问学生信息
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/student/pages/stuList.jsp");
				
			}else{//不存在这个用户，给出提示，转回登录页面
				String message = "用户名或密码错误";
				request.getSession().setAttribute("msg", message);
				response.sendRedirect("/student/login.jsp");
			}
		}
	}
	public void init() throws ServletException {
		// Put your code here
	}

}
