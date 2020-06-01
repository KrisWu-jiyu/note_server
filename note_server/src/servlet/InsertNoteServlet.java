package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.NoteDao;
import result.Result;

/**
 * Servlet implementation class InsertNoteServlet
 */
@WebServlet("/InsertNoteServlet")
public class InsertNoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertNoteServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");// ���ñ����ʽ

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String tel = request.getParameter("tel");
		String noteTime = request.getParameter("noteTime");// ��ȡ�������

		NoteDao dao = new NoteDao();// dao��
		Gson gson = new Gson(); // Gson�����࣬���ڽ����������JSON���ݷ��ؿͻ���
		Result result = new Result();// ��������
		Boolean b = dao.insert(title, content, noteTime, tel);
		result.isSuccess = b;

		if (b) {
			result.msg = "��¼�ɹ�";
		} else {
			result.msg = "����ʧ��";
		}

		// result.isSuccess = dao.insert(title, content, noteTime,
		// tel);//����һ�����ݣ�����������浽���result������
		// result.msg = result.isSuccess?"��¼�ɹ�":"����ʧ��";
		response.getWriter().append(gson.toJson(result));// �����������JSON����ͨ����Ӧ����
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
