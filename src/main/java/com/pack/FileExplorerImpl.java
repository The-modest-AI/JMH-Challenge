package com.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileExplorerImpl extends AbstractFileExplorer {
    private final File file;

    public FileExplorerImpl(File file) {
        this.file = file;
    }

    @Override
    protected InputStream openStream() throws IOException {
        return new FileInputStream(file);
    }
}


