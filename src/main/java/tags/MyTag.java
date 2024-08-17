package tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class MyTag extends SimpleTagSupport {

    private String color;
    private int font_size;
    private boolean is_uppercase;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public boolean isIs_uppercase() {
        return is_uppercase;
    }

    public void setIs_uppercase(boolean is_uppercase) {
        this.is_uppercase = is_uppercase;
    }


    @Override
    public void doTag() throws JspException {

        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        String style = "";

        if (is_uppercase) {
            style = "style=\"text-transform: uppercase; font-size:" + font_size + "px; color: " + color + ";\"";
        } else {
            style = "style=\"font-size:" + font_size + "px; color: " + color + ";\"";
        }

        try {
            out.print("<p " + style + ">");

            getJspBody().invoke(out);

            out.print("</p>");
        } catch (IOException e) {
            throw new JspException("Error in TextFormatHandler " + e);
        }
    }
}
