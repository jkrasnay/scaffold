/* Copyright 2015 John Krasnay
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.krasnay.scaffold;


/**
 * Exception thrown by a TemplateProcessor indicating a template syntax
 * error.
 */
public class TemplateParseException extends RuntimeException {

    private String template;
    private int line;
    private int column;
    private String message;

    public TemplateParseException(String template, int line, int column, String message) {
        this.template = template;
        this.line = line;
        this.column = column;
        this.message = message;
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

    @Override
    public String getMessage() {
        return String.format("line %d, col %d: %s", line, column, message);
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
