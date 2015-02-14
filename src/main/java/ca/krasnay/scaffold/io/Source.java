package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Internal interface representing a source of an input stream.
 *
 * @author <a href="mailto:john@krasnay.ca">John Krasnay</a>
 */
public interface Source {

    /**
     * Returns a new input stream on the source.
     */
    public InputStream getInputStream() throws IOException;

    /**
     * Closes the given input stream.
     *
     * @param in
     *            Stream returned by {@link #getInputStream()}. May be null.
     */
    public void close(InputStream in);
}