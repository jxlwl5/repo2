package it.cast.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.cast.domain.Poem;
import it.cast.service.PoemService;
import it.cast.service.impl.PoemServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findPoemServlet")
public class FindPoemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取数据
        String category = request.getParameter("category");
        //调用service方法 查询
        PoemService service = new PoemServiceImpl();
        String json = service.findByCategory(category);
        //将json写回浏览器
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
