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
 * ��½�����servlet
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
		//�����ַ����뷽ʽ
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//��ȡ�û��绰������(�绰Ψһ   �û�����һ��Ψһ)
		String tel = request.getParameter("tel");
		String password = request.getParameter("password");

		// ��������װ��result��
		UserDao dao = new UserDao();
		Result rs = new Result();
		Boolean c = dao.login(tel, password);
		Gson gson = new Gson();
		//���������
//		rs.isSuccess = dao.login(tel, password);
//		rs.msg = rs.isSuccess?"��½�ɹ�":"��½ʧ�������µ�½";
//		response.getWriter().append(gson.toJson(rs));

		rs.isSuccess = c;
		if (c) {
			rs.msg = "��½�ɹ���";
		} else {
			rs.msg = "��½ʧ�������µ�½��";
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
