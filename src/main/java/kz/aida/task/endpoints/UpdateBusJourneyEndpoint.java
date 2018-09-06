package kz.aida.task.endpoints;

import kz.aida.task.database.BusJourney;
import kz.aida.task.database.DatabaseManager;
import kz.aida.task.utils.JSONUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/bus-journey/update/", "/bus-journey/update"})
public class UpdateBusJourneyEndpoint extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // чтение тела запроса
        BufferedReader reader = req.getReader();

        /// собрать тело запроса в одну строку
        String jsonBody = reader.lines().collect(Collectors.joining());

        if (jsonBody == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        BusJourney busJourney = JSONUtils.toBusJourney(jsonBody);

        if (busJourney == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        DatabaseManager.updateBusJourney(busJourney);
    }
}
