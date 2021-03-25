package com.tester;

import com.pack.FileExplorerImpl;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//com.tester.Testing the class com.pack.FileExplorerImpl[Passed]
public class TestFileExplorerImpl {

    @Test
    void firstTest() throws IOException {
        var impl = new FileExplorerImpl(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"));
        String expected = "154,\"*Staples* vLetter Openers, 2/Pack\",Carlos Soltero,16545,-3.06,3.68,1.32,Northwest Territories,\"Scissors, Rulers and Trimmers\",0.83";
        assertEquals(expected, impl.getLine(154));
    }
}
