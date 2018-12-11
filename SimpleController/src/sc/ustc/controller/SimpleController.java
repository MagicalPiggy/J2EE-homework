
package sc.ustc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author SA18225534
 *
 */
public class SimpleController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 实现doGet方法，调用doPost方法
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 实现doPost方法，输出一个欢迎页面，显示文字
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>SimpleController</title>");
		out.print("</head>");
		out.print("<body>欢迎使用SimpleController！</body>");
		out.print("</html>");

	}

}
