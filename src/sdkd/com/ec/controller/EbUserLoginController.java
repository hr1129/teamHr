package sdkd.com.ec.controller;

import sdkd.com.ec.dao.BaseDao;
import sdkd.com.ec.model.EbNews;
import sdkd.com.ec.model.EbUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

public class EbUserLoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //处理中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String name=request.getParameter("userName");
        String password=request.getParameter("passWord");

        int temp=0;
        if(name!=null && !"".equals(name) && password!=null && !"".equals(password)){
            List<String> params = new ArrayList<String>();
            String sql="select eu_user_name,eu_password from easybuy_user";
            ResultSet rs = new BaseDao().executeSearch(sql,null);
            try {
                    while (rs.next()){
                        String username=rs.getString("eu_user_name");
                        String psw=rs.getString("eu_password");
                        if(name.equals(username) && password.equals(psw)){
                            temp=1;
                            request.setAttribute("temp",temp);
                            break;
                        }
                        else {
                           temp=0;
                        }
                     }
                    if(temp==1){
                        request.getRequestDispatcher("/category.do").forward(request,response);
                    }
                    else if(temp==0){
                        request.getRequestDispatcher("/test.jsp").forward(request,response);
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
