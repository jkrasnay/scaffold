package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.OutputStream;

public class OutputStreamSink implements Sink {

    private OutputStream out;

    public OutputStreamSink(OutputStream out) {
        this.out = out;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public void close(OutputStream out) {
    }

}
