package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import result.Result;

/**
 * 登陆请求的servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置字符编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//获取用户电话、密码(电话唯一   用户名不一定唯一)
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");

		// 请求结果封装到result中
		UserDao dao = new UserDao();
		Result rs = new Result();
		Boolean c = dao.login(tel, password);
		Gson gson = new Gson();
		//三步运算符
//		rs.isSuccess = dao.login(tel, password);
//		rs.msg = rs.isSuccess?"登陆成功":"登陆失败请重新登陆";
//		response.getWriter().append(gson.toJson(rs));

		rs.isSuccess = c;
		if (c) {
			rs.msg = "登陆成功！";
		} else {
			rs.msg = "登陆失败请重新登陆！";
		}
		String result = gson.toJson(rs);

		response.getWriter().append(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}
