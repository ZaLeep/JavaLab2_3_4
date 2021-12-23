package controllers.servlets.userServlets;

import core.DBSupport.searchEngine.SearchResult;
import core.entities.service.Ticket;
import core.entities.service.User;
import core.supportClasses.BuyingProcess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BuyTicket", urlPatterns = "/buyTicket")
public class BuyingController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        SearchResult result = (SearchResult) session.getAttribute("selectedResult");

        int carriageNumber = Integer.parseInt(request.getParameter("carriageNumber"));

        Ticket ticket = new Ticket();
        ticket.setUser_ID(user.getUser_ID());
        ticket.setTrain_ID(result.getTrain().getTrain_ID());
        ticket.setCarriageNumber(carriageNumber);
        ticket.setDeptStation_ID(result.getDepartStation().getStation_ID());
        ticket.setDeptTime(result.getDepartTime());
        ticket.setDestStation_ID(result.getDestStation().getStation_ID());
        ticket.setDestTime(result.getDestTime());
        ticket.setPrice(result.getPrice());

        boolean isSuccess = BuyingProcess.buy(ticket);

        if (isSuccess) {
            session.setAttribute("ticket", ticket);
            response.sendRedirect("buyingSuccess.jsp");
        } else {
            response.sendRedirect("errorPage.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
