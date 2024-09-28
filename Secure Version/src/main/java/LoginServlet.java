import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String query = "SELECT * FROM tbluser WHERE username = ? AND password = ?"; 

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "Radha@123");
            System.out.println("Connected to the database.");

            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username); 
            pstmt.setString(2, password); 

            System.out.println("Executing a secure query.");

            
            ResultSet rs = pstmt.executeQuery();

            
            if (rs.next()) {
                success = true;
                System.out.println("Login successful for user: " + username);
            } else {
                System.out.println("Login failed for user: " + username);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database connection or query execution failed.");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
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
