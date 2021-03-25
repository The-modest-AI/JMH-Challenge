package com.pack;

import java.io.IOException;
import java.io.InputStream;

public abstract class FileExplorerImplWithCache implements FileExplorer {

    protected abstract InputStream openStream() throws IOException;

    static class Pair {
        int lineIndex;
        String line;
    }

    private final Pair[] cache;
    private int cacheCursor;
    private final FileExplorer backedExplorer;

    public FileExplorerImplWithCache(FileExplorer fileExplorer, int cacheSize) {
        assert cacheSize >= 1 : "Error";
        this.backedExplorer = fileExplorer;
        this.cache = new Pair[cacheSize];
        this.cacheCursor = 0;
    }

    @Override
    public final String getLine(int lineIndex) throws IOException {
        if (cacheCursor == cache.length) {
            cacheCursor = 0;
        }
        for (var c : cache) {
            if (c != null && c.lineIndex == lineIndex) {
                return c.line;
            }
        }
        var insert = new Pair();
        insert.line = backedExplorer.getLine(lineIndex);
        insert.lineIndex = lineIndex;
        cache[cacheCursor] = insert;
        cacheCursor++;
        return cache[cacheCursor - 1].line;
    }
}


