package com.bench;

import com.pack.FileExplorerImpl;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.RunnerException;

import java.io.File;
import java.io.IOException;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

public class MeasureTest {

    @Fork(value = 1, warmups = 1)
    @Benchmark
    @OutputTimeUnit(MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 3, timeUnit = MICROSECONDS)
    public void benchExec(Blackhole blackhole) throws IOException {
        var fileExplorer = new FileExplorerImpl(new File("/home/the-ai/IdeaProjects/VanillaJava/src/resources/SampleCSVFile.csv"));
        for (int i = 0; i < 100; i += 5) {
            blackhole.consume(fileExplorer.getLine(i % 20));
        }
    }


    public static void main(String[] args) throws RunnerException, IOException {
        Main.main(args);
    }
}