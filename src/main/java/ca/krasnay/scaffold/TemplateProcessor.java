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

import java.util.Map;

/**
 * Performs textual substitution into a template using a particular
 * template technology.
 */
public interface TemplateProcessor {

    /**
     * Substitutes a set of values into a template and returns the result.
     *
     * @param template
     *            Template into which to substitute. The particular syntax for
     *            specifying where data should be substituted is dependent on
     *            the particular template technology being used.
     * @param map
     *            Map of values to substitute into the template. Non-string
     *            values are converted to strings by calling their toString()
     *            method.
     * @param isHtml
     *            If true, values being substituted are escaped according to
     *            HTML/XML rules, unless the values are instances of
     *            {@link RawString}. If false, no escaping is performed.
     * @throws TemplateParseException
     *             if the template engine is unable to parse the given template.
     */
    public String process(String template, Map<String, Object> map, boolean isHtml) throws TemplateParseException;

}
