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

@WebServlet("/findContentServlet")
public class FindContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        PoemService service = new PoemServiceImpl();

        Poem poem = service.findById(id);

        //将poems对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(poem);
        //将json写回浏览器
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
