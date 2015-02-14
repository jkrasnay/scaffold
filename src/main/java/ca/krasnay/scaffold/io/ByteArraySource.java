package ca.krasnay.scaffold.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArraySource implements Source {

    private byte[] bytes;

    public ByteArraySource(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void close(InputStream in) {
        IOUtils.close(in);
    }

}
