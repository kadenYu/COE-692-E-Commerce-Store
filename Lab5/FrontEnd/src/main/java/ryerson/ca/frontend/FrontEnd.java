package ryerson.ca.frontend;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ryerson.ca.business.Business;
import ryerson.ca.helper.ItemXML;
import ryerson.ca.persistence.User_CRUD;
import ryerson.ca.helper.UserInfo;
import ryerson.ca.frontend.Authenticate;
import java.util.Map.Entry;
import javax.servlet.http.HttpSession;



@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {

    private final Authenticate autho;
    private final String authenticationCookieName = "login_token";

    public FrontEnd() {
        this.autho = new Authenticate();
    }

    private Map.Entry<String, String> isAuthenticated(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String token = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(authenticationCookieName)) {
                    token = cookie.getValue();
                }
            }
        }

        if (!token.isEmpty()) {
            try {
                Map.Entry<Boolean, String> verifyResult = autho.verify(token);
                if (verifyResult.getKey()) {
                    return new AbstractMap.SimpleEntry<>(token, verifyResult.getValue());
                }
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return new AbstractMap.SimpleEntry<>("", "");
    }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Extract token and username
    Map.Entry<String, String> authenticationResult = isAuthenticated(request);
    String token = authenticationResult.getKey();
    String uname = authenticationResult.getValue();
    String hiddenParam = request.getParameter("pageName");

    // Ensure hiddenParam is not null or empty
    if (hiddenParam == null || hiddenParam.isEmpty()) {
        // Handle the case where hiddenParam is not provided
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "pageName parameter is missing");
        return;
    }

    // Log the hiddenParam value
    System.out.println("hiddenParam: " + hiddenParam);

    switch (hiddenParam) {
        case "login":
            handleLogin(request, response);
            break;
        case "search":
            handleSearch(request, response, token, uname);
            break;
        case "hold":
            handleHold(request, response, token, uname);
            break;
        default:
            // Handle other cases or log an error
            System.out.println("Unknown pageName value: " + hiddenParam);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown pageName value");
            break;
    }
}




  private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String UserName = request.getParameter("username");
    String Password = request.getParameter("password");

    // Check user credentials using User_CRUD
    UserInfo userInfo = User_CRUD.read(UserName, Password);

    if (userInfo != null) {
        // If user exists, create JWT token
        String token = autho.createJWT("issuer", UserName, 3600000); // 1 hour expiry

        // Store username and token in session
        request.getSession().setAttribute("uname", UserName);
        request.getSession().setAttribute("token", token);

        // Forward to frontpageWithLogin.jsp
        request.setAttribute("username", UserName);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");
        requestDispatcher.forward(request, response);
    } else {
        // If user does not exist, redirect to login.jsp
        response.sendRedirect("frontpageWithoutLogin.jsp");
    }
}


    
  private void handleSearch(HttpServletRequest request, HttpServletResponse response, String token, String uname) throws ServletException, IOException {
    ItemXML result;
    String query = request.getParameter("query");

    if (token == null || token.isEmpty()) {
        token = (String) request.getSession().getAttribute("token");
    }
    if (uname == null || uname.isEmpty()) {
        uname = (String) request.getSession().getAttribute("uname");
    }

    if (token == null || token.isEmpty()) {
        response.sendRedirect("login.jsp");
        return;
    }

    Entry<Boolean, String> verifyResult = autho.verify(token);
    if (!verifyResult.getKey()) {
        response.sendRedirect("login.jsp");
        return;
    }

    request.setAttribute("username", uname);
    result = retrieveItemsFromBackend(query, token);
    request.setAttribute("itemResults", result);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("frontpageWithLogin.jsp");
    requestDispatcher.forward(request, response);
}




 private void handleHold(HttpServletRequest request, HttpServletResponse response, String token, String uname) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (token == null || token.isEmpty()) {
        token = (String) session.getAttribute("token");
    }
    if (uname == null || uname.isEmpty()) {
        uname = (String) session.getAttribute("uname");
    }

    if (token == null || token.isEmpty()) {
        response.sendRedirect("frontpageWithoutLogin.jsp");
        return;
    }

    int itemId = Integer.parseInt(request.getParameter("itemId"));
    boolean isHeld = Business.holdItem(itemId, token);

    if (isHeld) {
        request.setAttribute("message", "Item held successfully");
      System.out.println("Item is held");
    } else {
        request.setAttribute("message", "Failed to hold item");
    //    response.sendRedirect("frontpagewithLogin.jsp");
     System.out.println("Item is held");
    }

    handleSearch(request, response, token, uname);
}




    private ItemXML retrieveItemsFromBackend(String query, String token) {
        try {
            return Business.getServices(query, token);
        } catch (IOException ex) {
            Logger.getLogger(FrontEnd.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
