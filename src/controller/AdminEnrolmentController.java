
package controller;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.ApplicationException;
import model.CourseDTO;
import model.EnrolmentDTO;
import model.StudentDTO;
import service.AdminManager;

/**
 * Servlet implementation class admin_manageenrollment
 */
@WebServlet("/admin_manageenrolment")
public class AdminEnrolmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEnrolmentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

				doProcess(request,response);
			
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)  {
		
		AdminManager adminManager=new AdminManager();
		HttpSession session=request.getSession();
		if(session.getAttribute("role")!=null&&session.getAttribute("role").equals("admin"))
		{
		String action=request.getParameter("action");
		String courseID = request.getParameter("CourseID");
		String studentID=request.getParameter("StudentID");
		if(action!=null&&action.equals("view"))
		{		
			CourseDTO course=adminManager.findCourse(Integer.parseInt(courseID));
			
			ArrayList<EnrolmentDTO> data=adminManager.findEnrolmentByCourse(course);
			if(data!=null)
			{
				
				adminManager.findEnrolmentByCourse(course);
				request.setAttribute("enrolment", data);
				request.setAttribute("CourseID", courseID);
			}
			else
			{
				request.setAttribute("noEnrol", "No Enrolment");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/admin_manageenrolment.jsp?action=");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(action!=null&&action.equals("addEnrolment"))
		{
			
			String path="";
			 try {
				 int id = Integer.parseInt(request.getParameter("StudentID"));
				 StudentDTO student=adminManager.findStudent(Integer.parseInt(request.getParameter("StudentID")));
					CourseDTO course =adminManager.findCourse(Integer.parseInt(request.getParameter("CourseID")));
					
					if(student!=null)
					{
						if(course !=null)
						{
							ArrayList<EnrolmentDTO> data=adminManager.findEnrolmentByCourse(course);
							
								if(adminManager.checkDuplicate(student,course)&&adminManager.checkSize(course))
								{
									
									EnrolmentDTO enrolment=new EnrolmentDTO(student,course);
									int addEnrol=adminManager.createEnrolment(enrolment);
									
									request.setAttribute("message", "success");
									path="admin_manageenrolment?action=view";
								}
								else
								{
									path="admin_manageenrolment?action=view&studentError=duplicate enrolment.";
								}

						}
						else
						{
							path="admin_manageenrolment?action=view&studentError=this course is unavailable.";
						}
						
					}
					else
					{
						path="admin_manageenrolment?action=view&studentError=incorrect";
					}
					
					RequestDispatcher rd = request.getRequestDispatcher(path);
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				 
				  } catch (NumberFormatException e) {
					  path="admin_manageenrolment?action=view&studentError=incorrect";
					  RequestDispatcher rd = request.getRequestDispatcher(path);
						try {
							rd.forward(request, response);
						} catch (ServletException e1) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				  }
			
		}
		else if (action!=null&&action.equals("delete"))
		{
			
			StudentDTO student=adminManager.findStudent(Integer.parseInt(request.getParameter("StudentID")));
			CourseDTO course =adminManager.findCourse(Integer.parseInt(request.getParameter("CourseID")));
			EnrolmentDTO enrol=new EnrolmentDTO(student,course);
			int deleteEnrol=adminManager.deleteEnrolment(enrol);
			request.setAttribute("message", "success");
			RequestDispatcher rd = request.getRequestDispatcher("admin_manageenrolment?action=view");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			ArrayList<CourseDTO> data = adminManager.listAllCourses();
			request.setAttribute("course", data);
			request.setAttribute("usefor", "enrolment");
			RequestDispatcher rd = request.getRequestDispatcher("/admin_managecourses.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else
		{
			RequestDispatcher rd = request.getRequestDispatcher("/admin_login.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doProcess(request,response);

	}

}
