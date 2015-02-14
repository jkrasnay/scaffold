package ca.krasnay.scaffold;

import java.io.CharArrayWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

public class VelocityTemplateProcessor implements TemplateProcessor {

    private static final Pattern LINE_COL_PATTERN = Pattern.compile("line (\\d+).*column (\\d+)");

    public String process(String template, Map<String, Object> map, final boolean isHtml) throws TemplateParseException {

        if (template == null) {
            return null;
        }

        VelocityContext ctx = new VelocityContext(map);

        CharArrayWriter writer = new CharArrayWriter();
        try {
            Velocity.evaluate(ctx, writer, "template", template);
        } catch (Exception ex) {

            if (ex.getMessage() != null) {
                Matcher m = LINE_COL_PATTERN.matcher(ex.getMessage());
                if (m.find()) {
                    int line = Integer.parseInt(m.group(1));
                    int column = Integer.parseInt(m.group(2));
                    throw new TemplateParseException(template, line, column);
                }
            }

            throw new RuntimeException(ex);
        }

        return writer.toString();
    }

}
