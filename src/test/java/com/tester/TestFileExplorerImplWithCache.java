package com.tester;


import com.pack.FileExplorerImpl;
import com.pack.FileExplorerImplWithCache;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFileExplorerImplWithCache {
    public static class FileExplorerImplWithCacheSpy extends FileExplorerImplWithCache {
        File content;

        public FileExplorerImplWithCacheSpy(File file) {
            super(new FileExplorerImpl(file), 1024);
            this.content = file;
        }

        @Override
        protected InputStream openStream() throws IOException {
            return new FileInputStream(content);
        }
    }

    @Test
    void firstTest() throws IOException {

        var obj = new FileExplorerImplWithCacheSpy(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"));
        String expected = "4,R380,Clay Rozendal,483,1198.97,195.99,3.99,Nunavut,Telephones and Communication,0.58";
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
    }
}