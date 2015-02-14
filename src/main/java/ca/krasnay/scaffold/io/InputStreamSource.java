package ca.krasnay.scaffold.io;

import java.io.InputStream;

public class InputStreamSource implements Source {

    private InputStream in;

    public InputStreamSource(InputStream in) {
        this.in = in;
    }

    @Override
    public InputStream getInputStream() {
        return in;
    }

    @Override
    public void close(InputStream in) {
    }

}