package ca.krasnay.scaffold.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSink implements Sink {

    private File file;

    public FileSink(File file) {
        this.file = file;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(file);
    }

    @Override
    public void close(OutputStream in) {
        IOUtils.close(in);
    }

}