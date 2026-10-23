package main.java.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Person;

/**
 * [13-5 使用JSON]-使用Gson
 * 此範例示範前/後端皆以JSON格式傳輸資料
 * 前端會傳來JSON格式資料 {"name": "姓名", "age": 年紀}
 * 此Servlet會將年紀加一歲，並同樣以JSON格式回傳給前端
 * @author William
 */
@WebServlet("/useGson")
public class UseGsonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
            Person person = json2Person(req);
            // ↓設定回應的資料格式為JSON
            resp.setContentType("application/json; charset=UTF-8");
            person.setAge(person.getAge() + 1);
            writeJson(resp, person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Person json2Person(HttpServletRequest req) throws IOException {
        BufferedReader br = req.getReader();
        return GSON.fromJson(br, Person.class);
    }

    private void writeJson(HttpServletResponse resp, Person person) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.print(GSON.toJson(person));
    }
}

