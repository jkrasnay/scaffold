package ca.krasnay.scaffold.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringSource implements Source {

    private String charsetName;

    private String s;

    public StringSource(String s) {
        this(s, IOUtils.UTF_8);
    }

    public StringSource(String s, String charsetName) {
        this.s = s;
        this.charsetName = charsetName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(s.getBytes(charsetName));
    }

    @Override
    public void close(InputStream in) {
    }

}
