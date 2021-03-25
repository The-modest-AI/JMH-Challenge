package com.pack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class AbstractFileExplorer implements FileExplorer {

    abstract protected InputStream openStream() throws IOException;

    @Override
    public final String getLine(int lineIndex) {
        assert lineIndex > 0 : "Error: Line Index can't be negative";
        String str = "";
        int count = 0;
        try (var scan = new Scanner(openStream(), StandardCharsets.UTF_8)) {
            while (scan.hasNext()) {
                count++;
                scan.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (var reader = new Scanner(openStream(), StandardCharsets.UTF_8)) {
            assert lineIndex <= count : "Error: Invalid Line Index";
            int c = 0;
            while (true) {
                if (!reader.hasNext()) break;
                str = reader.nextLine();
                c++;
                if (c == lineIndex) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}