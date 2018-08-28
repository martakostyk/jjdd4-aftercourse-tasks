package martak.jjdd4.aftercourse.servlets;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

@WebServlet(urlPatterns = "/get-html-code")
public class GetHtmlCodeServlet extends HttpServlet {

    private final Logger LOG = LoggerFactory.getLogger(GetHtmlCodeServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        URL url = null;

        try {
            url = new URL(req.getParameter("url"));
        } catch (Exception e) {
            resp.getWriter().println("Given website hasn't been found");
            LOG.info("Given website hasn't been found");
        }

        if (url != null) {

            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    resp.getWriter().println(inputLine);
                }

                in.close();

            } catch (NullPointerException e) {
                resp.getWriter().println("Given website does not contain html code to display");
                LOG.info("Given website does not contain html code to display");
            }
        }

    }
}
