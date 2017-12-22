package com.messageBoard;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public MessageServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		Message ms = new Message();
		ms.setMessage(request.getParameter("text"));
		//System.out.println(ms.getMessage());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		ms.setTime(time);
		HttpSession session = request.getSession();
		try {
			JdbcConn jc = new JdbcConn();
			int r = jc.insertMessage(ms.getMessage(),ms.getTime());
			if(r>0) {
				session.setAttribute("message", "success");
				//request.setAttribute("message", "success");
				//request.getRequestDispatcher("/MessageBoard.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/MessageBoard.jsp");
			}
			else {
				session.setAttribute("message", "fail");
				response.sendRedirect(request.getContextPath()+"/fail.jsp");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
