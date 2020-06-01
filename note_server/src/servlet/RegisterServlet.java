package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import bean.UserBean;
import result.Result;

/**
 * 注册请求的servlet
 */
@WebServlet("/regist")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 设置字符编码方式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		UserBean use = new UserBean();
		use.setName(request.getParameter("name"));
		use.setPassword(request.getParameter("password"));
		use.setTel(request.getParameter("tel"));

		UserDao userDao = new UserDao();
		Boolean b = userDao.regist(use);
		// 请求结果封装到result中
		Result r = new Result();
		r.isSuccess = b;

		if (b) {
			r.msg = "恭喜注册成功！";
		} else {
			r.msg = "注册失败，该手机号已被注册！";
		}

		// 请求结果通过Gson转换成字符串(导包)
		Gson gson = new Gson();
		String result = gson.toJson(r);

		response.getWriter().append(result);
	}

}
