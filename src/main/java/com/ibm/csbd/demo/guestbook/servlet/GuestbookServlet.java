package com.ibm.csbd.demo.guestbook.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.csbd.demo.guestbook.dao.BookEntryDao;
import com.ibm.csbd.demo.guestbook.model.BookEntry;



@WebServlet(urlPatterns = "/Guestbook" )
public class GuestbookServlet extends HttpServlet {

    @Inject
    private BookEntryDao dao;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("in servlet");
      
        try {
            List<BookEntry> allEntries = dao.getAllEntries();

            request.setAttribute("allEntries", allEntries);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/home.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsps/error.jsp");
            dispatcher.forward(request, response);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String comment = request.getParameter("comment");
        String name = request.getParameter("name");

        if(comment != null && name != null) {
            BookEntry entry = new BookEntry(name, comment);
            try {
                dao.createBookEntry(entry);
                response.sendRedirect("Guestbook");
            }
            catch(Exception e) {
                e.printStackTrace();
                response.sendRedirect("Guestbook" + "?error=" + URLEncoder.encode(e.getMessage(), "UTF-8"));

            }

            return;
        }
        else {
            response.sendRedirect("Guestbook?error=1");
        }



    }
    
}
