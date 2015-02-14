package ca.krasnay.scaffold.io;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;

/**
 * Utility methods for dealing with IO.
 *
 * None of these methods throw checked exceptions. Any IOException caught during
 * their operation is wrapped in a RuntimeException and re-thrown.
 *
 * @author John Krasnay <john@krasnay.ca>
 */
public class IOUtils {

    public static final String UTF_8 = "UTF-8";

    public static final int BYTE_BUFFER_SIZE = 4096;

    public static final int CHAR_BUFFER_SIZE = 4096;

    /**
     * Quietly loses the given closeable. Ignores null if passed, and catches
     * and ignores any IOException thrown by the close method.
     *
     * @param c
     *            Closeable (e.g. InputStream, Reader) to be closed. May be null.
     */
    public static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Copies the given resource to an output stream.
     *
     * @param clazz
     *            Class associated with the resource.
     * @param resourceName
     *            Name of the resource relative to the given class.
     * @param out
     *            OutputStream to receive the copy.
     */
    public static void copy(Class<?> clazz, String resourceName, OutputStream out) {
        copy(new ResourceSource(clazz, resourceName), new OutputStreamSink(out));
    }

    /**
     * Copies the entire contents of a file to an output stream.
     *
     * @param in
     *            File to be copied.
     * @param out
     *            OutputStream to receive the copy.
     */
    public static void copy(File in, OutputStream out) {
        copy(new FileSource(in), new OutputStreamSink(out));
    }


    /**
     * Copies the entire contents of an input stream to a file.
     *
     * @param in
     *            InputStream to be copied.
     * @param out
     *            File to receive the copy.
     */
    public static void copy(InputStream in, File out) {
        copy(new InputStreamSource(in), new FileSink(out));
    }


    /**
     * Copies a file.
     *
     * @param in
     *            File to be copied.
     * @param out
     *            File to receive the copy.
     */
    public static void copy(File in, File out) {
        copy(new FileSource(in), new FileSink(out));
    }

    /**
     * Copies the entire contents of an input stream to an output stream.
     *
     * @param in
     *            InputStream to be copied.
     * @param out
     *            OutputStream to receive the copy.
     */
    public static void copy(InputStream in, OutputStream out) {
        copy(in, out, BYTE_BUFFER_SIZE);
    }

    /**
     * Copies the entire contents of an input stream to an output stream.
     *
     * @param in
     *            InputStream to be copied.
     * @param out
     *            OutputStream to receive the copy.
     * @param bufferSize
     *            Size of buffer used for transfers.
     */
    public static void copy(InputStream in, OutputStream out, int bufferSize) {
        copy(new InputStreamSource(in), new OutputStreamSink(out), bufferSize);
    }

    /**
     * Copies the contents of a source to a sink using the default buffer size.
     */
    public static void copy(Source source, Sink sink) {
        copy(source, sink, BYTE_BUFFER_SIZE);
    }

    /**
     * Copies the contents of a source to a sink using a given buffer size.
     */
    public static void copy(Source source, Sink sink, int bufferSize) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = source.getInputStream();
            out = sink.getOutputStream();
            byte[] buffer = new byte[bufferSize];
            while (true) {
                int count = in.read(buffer);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    out.write(buffer, 0, count);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            source.close(in);
            sink.close(out);
        }
    }

    /**
     * Copies the entire contents of a reader to a writer.
     *
     * @param reader
     *            Reader to be copied.
     * @param writer
     *            Writer to receive the copy.
     */
    public static void copy(Reader reader, Writer writer) {
        copy(reader, writer, CHAR_BUFFER_SIZE);
    }

    /**
     * Copies the entire contents of a reader to a writer.
     *
     * @param reader
     *            Reader to be copied.
     * @param writer
     *            Writer to receive the copy.
     * @param bufferSize
     *            Size of buffer used for transfers.
     */
    public static void copy(Reader reader, Writer writer, int bufferSize) {

        try {
            char[] buffer = new char[bufferSize];
            while (true) {
                int count = reader.read(buffer);
                if (count < 0) {
                    break;
                } else if (count > 0) {
                    writer.write(buffer, 0, count);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copies the given string to an output stream using UTF-8 encoding.
     */
    public static void copy(String s, OutputStream outputStream) {
        copy(new StringSource(s), new OutputStreamSink(outputStream));
    }

    /**
     * Copy the given input stream into a byte array.
     *
     * @param inputStream
     *            Stream to copy.
     */
    public static byte[] toByteArray(InputStream inputStream) {
        return toByteArray(new InputStreamSource(inputStream));
    }

    /**
     * Loads the contents of the given resource into a byte array.
     */
    public static byte[] toByteArray(Class<?> clazz, String resource) {
        return toByteArray(new ResourceSource(clazz, resource));
    }

    /**
     * Loads the contents of the source into a byte array.
     */
    public static byte[] toByteArray(Source source) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        copy(source, new OutputStreamSink(outputStream));
        return outputStream.toByteArray();
    }

    /**
     * Copy the contents of the given URL into a byte array.
     *
     * @param url
     *            URL to download.
     */
    public static byte[] toByteArray(URL url) {
        return toByteArray(new UrlSource(url));
    }

    /**
     * Loads the given resource as a string using the given encoding.
     * Returns null if the resource does not exist.
     *
     * @param clazz
     *            Class that is the base for the resource.
     * @param resource
     *            Resource name to load.
     * @param encoding
     *            Encoding to use.
     */
    public static String toString(Class<?> clazz, String resource, String encoding) {
        return toString(new ResourceSource(clazz, resource), encoding);
    }

    /**
     * Reads the contents of a file into a string.
     *
     * @param file
     *            File to be loaded.
     * @param encoding
     *            Text encoding of the file, e.g. "UTF-8".
     */
    public static String toString(File file, String encoding) {
        return toString(new FileSource(file), encoding);
    }

    /**
     * Reads the contents of an InputStream into a string.
     *
     * @param in
     *            InputStream to read.
     * @param encoding
     *            Text encoding of the stream, e.g. "UTF-8".
     */
    public static String toString(InputStream in, String encoding) {
        return toString(new InputStreamSource(in), encoding);
    }

    /**
     * Reads the contents of a Source into a string.
     *
     * @param source
     *            Source to read.
     * @param encoding
     *            Text encoding of the stream, e.g. "UTF-8".
     */
    public static String toString(Source source, String encoding) {
        InputStream in = null;
        try {
            in = source.getInputStream();
            return toString(new InputStreamReader(in, encoding));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            source.close(in);
        }
    }

    /**
     * Reads the contents of a Reader into a string.
     *
     * @param reader
     *            Reader to be read.
     */
    public static String toString(Reader reader) {
        CharArrayWriter caw = new CharArrayWriter();
        copy(reader, caw);
        return caw.toString();
    }

    /**
     * Copies the content of the given URL to a String. Note that this is
     * simplistic. A better implementation would read the encoding from the HTTP
     * response.
     *
     * @param url
     *            URL to download.
     * @param encoding
     *            Encoding to use when converting bytes to characters.
     */
    public static String toString(URL url, String encoding) {
        return toString(new UrlSource(url), encoding);
    }

    /**
     * Loads the given resource as a string using UTF-8 encoding.
     *
     * @param clazz
     *            Class that is the base for the resource.
     * @param resource
     *            Resource name to load.
     */
    public static String toStringUtf8(Class<?> clazz, String resource) {
        return toString(clazz, resource, UTF_8);
    }

    /**
     * Reads the contents of a file into a string using UTF-8 character
     * encoding.
     *
     * @param file
     *            File to be read.
     */
    public static String toStringUtf8(File file) {
        return toString(file, UTF_8);
    }

    /**
     * Reads the contents of an input stream into a string using UTF-8 character
     * encoding.
     *
     * @param in
     *            Input stream to be read.
     */
    public static String toStringUtf8(InputStream in) {
        return toString(in, UTF_8);
    }

    /**
     * Downloads the given URL into a string, using UTF-8 encoding.
     *
     * @param url
     *            URL to download.
     */
    public static String toStringUtf8(URL url) {
        return toString(url, UTF_8);
    }

}
