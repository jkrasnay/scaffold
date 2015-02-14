package ca.krasnay.scaffold;


/**
 * Exception thrown by a TemplateProcessor indicating a template syntax
 * error.
 */
public class TemplateParseException extends RuntimeException {

    private String template;
    private int line;
    private int column;

    public TemplateParseException(String template, int line, int column) {
        super();
        this.template = template;
        this.line = line;
        this.column = column;
    }

    /**
     * Returns the template that was in error.
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Returns the one-based line number at which the error occurred.
     */
    public int getLine() {
        return line;
    }

    /**
     * Returns the one-based column number at which the error occurred.
     */
    public int getColumn() {
        return column;
    }


    /**
     * Returns a two-line string indicating the error. The returned string
     * consists of the error line, a newline, and some spaces and a caret
     * indicating the location of the error in the returned line.
     */
//    public String getErrorIndicator() {
//        String[] lines = template.split("\n");
//        if (lines.length < line) {
//            return "";
//        }
//        String errorLine = lines[line-1];
//        return String.format("%s\n%s^", errorLine, StringUtils.repeat(" ", column - 1));
//    }
}
