package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlSource implements Source {

    private URL url;

    public UrlSource(URL url) {
        this.url = url;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }

    @Override
    public void close(InputStream in) {
        IOUtils.close(in);
    }

}
