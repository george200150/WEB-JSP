package mvcIntelliJIdea;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CalendarCustom extends SimpleTagSupport {
    private int an, luna, zi;
    private String culoare;
    private String style;
    public int getAn() { return an; } // the index.jsp calls these methods when filtering
    public void setAn(int an) { this.an = an; }
    public int getLuna() { return luna; }
    public void setLuna(int luna) { this.luna = luna; }
    public int getZi() { return zi; }
    public void setZi(int zi) { this.zi = zi; }
    public String getCuloare() { return culoare; }
    public void setCuloare(String culoare) { this.culoare = culoare; }
    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }

    @Override
    public String toString() {
        return "mvcIntelliJIdea.CalendarCustom [an=" + an + ", luna=" + luna + ", zi=" + zi + ", culoare=" + culoare + ", style="
                + style + "]";
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (culoare == null) {
            culoare = "#ccc";
        }
        if (style == null) {
            style = "";
        }
        out.println(calendarHTML());
        System.out.println(toString());
    }


    private String zileGoale() { // fill the calendar board with empty slots starting from Monday, for the days in the previous month
        StringBuilder total = new StringBuilder();
        for (int i = 1; i < startingWeekDay(); i++) { // startingWeekDay() returneaza ziua in care incepe luna
            total.append("<li></li>");
        }
        return total.toString();
    }

    private int numarZilele() {
        Calendar mycal = new GregorianCalendar(an, luna - 1, zi); // Java callendar starts from 0
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // return the days of the given month
    }

    private String zilele() { // fill the slots starting from the current day the month starts on
        int daysCount = numarZilele();

        StringBuilder total = new StringBuilder(); // string builder for some efficiency (does not rebuild the string every time a new string is appended)
        for (int i = 1; i <= daysCount; i++) {
            if (i != zi) {
                total.append("<li>").append(i).append("</li>");
            } else {
                total.append("<li><span class=\"active\" style=\"background: ").append(culoare).append(";").append(style).append("\">").append(i).append("</span></li>");
            }
        }
        return total.toString();
    }

    private int startingWeekDay() { // returneaza ziua in care incepe luna
        Calendar c = new GregorianCalendar(an, luna - 1, 0); // Java callendar starts from 0
        return c.get(Calendar.DAY_OF_WEEK);
    }


    private String calendarHTML() {
        return "<div class=\"month\" style=\"background: " + culoare + ";" + style + "\">      " +
                "  <ul>" +
                "    <li>" +
                "      " + new DateFormatSymbols().getMonths()[luna - 1] + "<br>" +
                "      <span style=\"font-size:18px\">" + an + "</span>" +
                "    </li>" +
                "  </ul>" +
                "</div>" +
                "<ul class=\"weekdays\">" + /*create the table header containing all the week days*/
                "  <li>Mo</li>" +
                "  <li>Tu</li>" +
                "  <li>We</li>" +
                "  <li>Th</li>" +
                "  <li>Fr</li>" +
                "  <li>Sa</li>" +
                "  <li>Su</li>" +
                "</ul>" +
                "<ul class=\"days\"> " +
                zileGoale() +
                zilele() +
                "</ul>";
    }
}