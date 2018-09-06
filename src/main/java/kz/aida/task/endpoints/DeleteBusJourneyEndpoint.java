package kz.aida.task.endpoints;


import kz.aida.task.database.DatabaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = {"/bus-journey/delete/", "/bus-journey/delete"})
public class DeleteBusJourneyEndpoint extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idFromRequest = req.getParameter("id");

        if (idFromRequest == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int id;

        try {
            id = Integer.valueOf(idFromRequest);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DatabaseManager.deleteBusJourney(id);
    }
}
