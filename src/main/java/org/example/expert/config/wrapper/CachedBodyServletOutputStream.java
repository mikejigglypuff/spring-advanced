package org.example.expert.config.wrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CachedBodyServletOutputStream extends ServletOutputStream {
    private final ByteArrayOutputStream cachedBodyOutputStream;
    private final ServletOutputStream servletOutputStream;

    public CachedBodyServletOutputStream(
        ByteArrayOutputStream cachedOutputStream, ServletOutputStream servletOutputStream
    ) {
        this.cachedBodyOutputStream = cachedOutputStream;
        this.servletOutputStream = servletOutputStream;
    }

    @Override
    public boolean isReady() {
        return servletOutputStream.isReady();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        servletOutputStream.setWriteListener(writeListener);
    }

    @Override
    public void write(int b) throws IOException {
        cachedBodyOutputStream.write(b);
        servletOutputStream.write(b);
    }
}
