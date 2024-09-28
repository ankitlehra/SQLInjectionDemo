# SQL Injection Demo

This project demonstrates a simple web application with two versions: a vulnerable version and a secure version. The application includes a login functionality and is designed to illustrate the risks of SQL Injection and how to mitigate them.

## Project Structure

### Main `README.md` (Root Folder):
The main `README.md` file should provide an overview of the entire project structure and briefly explain what each folder contains. It should link to the detailed README files within the subfolders for more information.

### `README.md` in Root Folder:
SQLInjectionDemo/
│
├── vulnerable-code/
│   ├── src/
│   │   ├── LoginServlet.java   # Servlet code vulnerable to SQL Injection
│   │   └── login.jsp           # JSP file for login page
│   ├── screenshots/            # Screenshots showing SQL Injection in action
│ 
│
├── secure-version/
│   ├── src/
│   │   ├── LoginServlet.java   # Secure Servlet code using PreparedStatement
│   │   └── login.jsp           # JSP file for login page
│   ├── screenshots/            # Screenshots showing the secure implementation


## Versions Included

### 1. Vulnerable Version
- This version contains code that is prone to SQL Injection due to unsafe handling of user inputs.
- Location: `vulnerable-code/src`

### 2. Secure Version
- This version implements secure coding practices to prevent SQL Injection attacks.
- Location: `secure-version/src`

## Requirements
- Java EE
- Apache Tomcat Server
- MySQL Database





