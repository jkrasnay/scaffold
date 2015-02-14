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
