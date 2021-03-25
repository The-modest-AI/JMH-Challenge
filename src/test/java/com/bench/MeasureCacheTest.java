package com.bench;


import com.tester.TestFileExplorerImplWithCache;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.File;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

public class MeasureCacheTest {

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3, timeUnit = MICROSECONDS)
    public void benchExec(Blackhole blackhole) throws IOException {
        var fileExplorer = new TestFileExplorerImplWithCache.FileExplorerImplWithCacheSpy(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"));
        for (int i = 0; i < 100; i += 5) {
            blackhole.consume(fileExplorer.getLine(i % 20));
        }
    }

    public static void main(String[] args) throws IOException {
        Main.main(args);
    }
}