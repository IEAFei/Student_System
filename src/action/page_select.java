package action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import bean.Student;
import bean.StudentDao;

import java.util.List;

@WebServlet(name = "page_select")
public class page_select extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int currPage = 1;
            if (request.getParameter("page") != null) {
                currPage = Integer.parseInt(request.getParameter("page"));
            }
            StudentDao Dao= new StudentDao();
            List<Student> list = null;
            list = Dao.find(currPage);
            request.setAttribute("list", list);
            int pages;  //��ҳ��
            int count = Dao.findCount(); //��ѯ�ܼ�¼��
            if (count % Student.PAGE_SIZE == 0) {
                pages = count / Student.PAGE_SIZE;
            } else {
                pages = count / Student.PAGE_SIZE + 1;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= pages; i++) {
                if (i == currPage) {   //�ж��Ƿ�Ϊ��ǰҳ
                    sb.append("��" + i + "��");  //������ҳ��
                } else {
                    sb.append("<a href='page_select?page=" + i + "'>" + i + "</a>"); //������ҳ��
                }
                sb.append(" ");
            }
            request.setAttribute("bar", sb.toString());
            request.getRequestDispatcher("LookStudent_all.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
