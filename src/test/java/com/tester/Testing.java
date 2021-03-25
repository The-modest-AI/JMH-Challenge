package com.tester;

import com.pack.AbstractFileExplorer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testing {
    public static class AbstractFileExplorerSpy extends AbstractFileExplorer {
        byte[] content;

        AbstractFileExplorerSpy(String content) {
            this.content = content.getBytes();
        }

        @Override
        protected InputStream openStream() throws IOException {
            return new ByteArrayInputStream(content);
        }
    }

    @Test
    void firstTest() {
        String actual = """
                Somebody to love
                Good Old Fashioned lover boy
                Seaside Rendezvous
                Miracle""";
        String expected = "Good Old Fashioned lover boy";
        var obj = new AbstractFileExplorerSpy(actual);
        try {
            assertEquals(expected, obj.getLine(2));
        } catch (Exception ignored) {
        }
    }
}