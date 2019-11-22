package cn.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dao.BaseDao;
import cn.service.DealService;
import cn.service.impl.DealServiceimp;
import cn.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;

/**
 * Servlet implementation class doJson3
 */
@WebServlet("/selectTop10")
public class selectTop10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public selectTop10() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=utf-8;");
		DealService dealService = new DealServiceimp();
		ResultSet rs = dealService.selectTop10();
		JsonUtil json = new JsonUtil();
		JSONArray jo = new JSONArray();
            try {
				jo = json.resultSetToJson(rs);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(jo.toString());//Json读不到带括号的键名，转成String去掉括号，再转成Json
		    String s = jo.toString();
		    String s1 = s.replace("sum(cal)","sum");
			System.out.println(s1);
			JSONArray jo1 = JSONArray.fromObject(s1);
			response.getWriter().print(jo1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
