import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9$_]+$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.length() != 5) {
            request.setAttribute("message", "Username must be exactly 5 characters long.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; 
        }

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            request.setAttribute("message", "Password must only contain letters, numbers, $, or _.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return; 
        }

        boolean success = false;
        String query = "SELECT * FROM tbluser WHERE username='" + username + "' AND password='" + password + "'"; 

        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Radha@123");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                success = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (success) {
            request.setAttribute("message", "Login successful");
        } else {
            request.setAttribute("message", "Login failed. Please try again.");
        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
