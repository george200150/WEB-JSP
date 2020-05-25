package webapp.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;

public class EscapeUtils {
    public static final HashMap m = new HashMap();
    static { // static block works as a constructor
        m.put(34, "&quot;"); // <- quotation-mark
        m.put(60, "&lt;");   // <- less-than
        m.put(62, "&gt;");   // >- greater-than
        // User needs to map all html entities with their corresponding decimal values.
        // Please refer to below table for mapping of entities and integer value of a char
    }

    public static String escapeHtml(String str) {
        // String str = "<script>alert(\"abc\")</script>";
        try {
            StringWriter writer = new StringWriter((int) (str.length() * 1.5)); // avoid memory location overflow
            escape(writer, str);
            System.out.println("encoded string is " + writer.toString() );
            return writer.toString();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

    public static void escape(Writer writer, String str) throws IOException { // sanitize text to avoid XSS
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            String entityName = (String) m.get((int) c);
            if (entityName == null) {
                if (c > 0x7F) { // this is about the same way the HTTP requests transform " " into %20
                    writer.write("&#");
                    writer.write(Integer.toString(c, 10)); // correct any special charater type overflow
                    writer.write(';');
                } else {
                    writer.write(c);
                }
            } else {
                writer.write(entityName);
            }
        }
    }
}
