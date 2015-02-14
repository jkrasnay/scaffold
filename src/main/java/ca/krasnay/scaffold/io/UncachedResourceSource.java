package ca.krasnay.scaffold.io;

import java.io.IOException;
import java.io.InputStream;

public class UncachedResourceSource implements Source {

    private Class<?> clazz;

    private String resourceName;

    public UncachedResourceSource(Class<?> clazz, String resourceName) {
        this.clazz = clazz;
        this.resourceName = resourceName;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return clazz.getResource(resourceName).openStream();
    }

    @Override
    public void close(InputStream in) {
        IOUtils.close(in);
    }
}