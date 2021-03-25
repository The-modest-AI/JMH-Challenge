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

        public FileExplorerImplWithCacheSpy(File file, int cacheSize) {
            super(new FileExplorerImpl(file), cacheSize);
            this.content = file;
        }

        @Override
        protected InputStream openStream() throws IOException {
            return new FileInputStream(content);
        }
    }

    @Test
    void secondTest() throws IOException {   //This test.
        var obj = new FileExplorerImplWithCacheSpy(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"), 1);
        String expected = "4,R380,Clay Rozendal,483,1198.97,195.99,3.99,Nunavut,Telephones and Communication,0.58";
        assertEquals(expected, obj.getLine(4));
        String expected2 = "12,Advantus Map Pennant Flags and Round Head Tacks,Neola Schneider,807,-14.33,3.95,2,Nunavut,Rubber Bands,0.53";
        assertEquals(expected2, obj.getLine(12));
    }

    @Test
    void firstTest() throws IOException {

        var obj = new FileExplorerImplWithCacheSpy(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"), 1024);
        String expected = "4,R380,Clay Rozendal,483,1198.97,195.99,3.99,Nunavut,Telephones and Communication,0.58";
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
        assertEquals(expected, obj.getLine(4));
    }
}