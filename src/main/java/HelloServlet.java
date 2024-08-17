import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/HelloServlet"}, description = "add two numbers")
public class HelloServlet extends HttpServlet {

    enum Gender {
        male, female
    }

    void proccessRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();

            String first_name="", last_name="",plan="";
            Gender gender=null;
            LocalDate date=null;
            boolean agree = false;
            if (ServletFileUpload.isMultipartContent(request)) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List items = upload.parseRequest(request);
                Iterator itr = items.iterator();

                while (itr.hasNext()) {
                    FileItem item = (FileItem) itr.next();
                    if (item.isFormField()) {

                        if (item.getFieldName().equals("first_name")) {
                            first_name = item.getString();
                        } else if (item.getFieldName().equals("last_name")) {
                            last_name = item.getString();
                        } else if (item.getFieldName().equals("gender")) {
                            gender = Gender.valueOf(item.getString());
                        } else if (item.getFieldName().equals("date")) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            date = LocalDate.parse(item.getString(), formatter);
                        }else if (item.getFieldName().equals("plan")) {
                            plan = item.getString();
                        }else if (item.getFieldName().equals("agree")) {
                            if (item.getString().equals("0"))
                                agree = false;
                        }else{
                            agree = true;

                        }

                        } else {
                            item.write(new File(item.getName()));
                        }
                    }
                }
            out.println("<h1>You entered:</h1>");
            out.println("<p>Your first name:" + first_name + "</p>");
            out.println("<p>Your last name:" + last_name + "</p>");
            out.println("<p>Your gender:" + gender.toString() + "</p>");
            out.println("<p>Ypur plan:" + plan + "</p>");
            out.println("<p>Ypur date:" + date.toString() + "</p>");

            if(agree){
                out.println("<p>You agreed.</p>");
            }else{
                out.println("<p>Ypu have not agreed.</p>");
            }


            } catch(IOException | FileUploadException e){
            e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }

        }


        @Override
        protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            proccessRequest(req, resp);
        }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            proccessRequest(req, resp);
        }

        @Override
        public String getServletInfo () {
            return "hello";
        }
    }

