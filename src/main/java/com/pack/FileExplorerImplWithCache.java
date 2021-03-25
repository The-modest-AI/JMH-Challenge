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
        boolean isNull = false;
        for (var c : cache) {
            if (c == null) {
                isNull = true;
                break;
            }
        }
        if (!isNull) {
            for (var c : cache) {
                if (c.lineIndex == lineIndex) {
                    return c.line;
                }
            }
        } else {
            var insert = new Pair();
            insert.line = backedExplorer.getLine(lineIndex);
            insert.lineIndex = lineIndex;
            cache[cacheCursor] = insert;
            if (cacheCursor < cache.length) {
                cacheCursor++;
            } else {
                cacheCursor = 0;
            }
        }
        return cache[cacheCursor - 1].line;
    }
}


