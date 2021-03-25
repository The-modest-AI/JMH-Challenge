package com.pack;

import java.io.IOException;

public interface FileExplorer {
    String getLine(int lineIndex) throws IOException;
}