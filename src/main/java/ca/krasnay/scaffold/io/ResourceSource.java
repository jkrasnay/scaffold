package ca.krasnay.scaffold.io;

import java.io.InputStream;

public class ResourceSource implements Source {

    private Class<?> clazz;

    private String resourceName;

    public ResourceSource(Class<?> clazz, String resourceName) {
        this.clazz = clazz;
        this.resourceName = resourceName;
    }

    @Override
    public InputStream getInputStream() {
        return clazz.getResourceAsStream(resourceName);
    }

    @Override
    public void close(InputStream in) {
        IOUtils.close(in);
    }
}