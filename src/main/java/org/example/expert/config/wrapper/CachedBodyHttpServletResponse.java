package org.example.expert.config.wrapper;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class CachedBodyHttpServletResponse extends HttpServletResponseWrapper {
    // 언제나 body의 같은 값을 조회할 수 있도록 byte[] 형식으로 저장
    private final ByteArrayOutputStream cachedOutputStream = new ByteArrayOutputStream();
    private PrintWriter writer;

    public CachedBodyHttpServletResponse(HttpServletResponse response) {
        super(response);
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new CachedBodyServletOutputStream(cachedOutputStream, super.getOutputStream());
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer == null) {
            writer = new PrintWriter(cachedOutputStream);
        }
        return writer;
    }

    public String getBody() {
        return cachedOutputStream.toString();
    }
}
