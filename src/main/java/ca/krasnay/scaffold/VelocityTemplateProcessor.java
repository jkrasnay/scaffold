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
                    throw new TemplateParseException(template, line, column, ex.getMessage());
                }
            }

            throw new RuntimeException(ex);
        }

        return writer.toString();
    }

}
