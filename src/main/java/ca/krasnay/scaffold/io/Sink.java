package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Represents a place to which bytes can be sent.
 *
 * @author <a href="mailto:john@krasnay.ca">John Krasnay</a>
 */
public interface Sink {

    /**
     * Returns a new output stream on the sink.
     */
    public OutputStream getOutputStream() throws IOException;

    /**
     * Closes the given stream.
     *
     * @param out Stream returned by {@link #getOutputStream()}. May be null.
     */
    public void close(OutputStream out);
}