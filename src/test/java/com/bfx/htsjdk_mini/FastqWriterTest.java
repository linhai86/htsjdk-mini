package com.bfx.htsjdk_mini;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.Assert.*;

public class FastqWriterTest {
  private Path readerPath;
  private Path writerPath;

  @Before
  public void setUp() throws Exception {
    readerPath = Paths.get("src","test","resources", "input", "illumina64.fastq");
    writerPath = Paths.get("src","test","resources", "output", "illumina64.fastq");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void write() {
    try (FastqReader reader = new FastqReader(readerPath);
         FastqWriter writer = new FastqWriter(writerPath)) {
      Iterator<FastqRecord> iterator = reader.iterator();
      assertTrue(iterator.hasNext());
      while (iterator.hasNext()) {
        FastqRecord record = iterator.next();
        assertEquals(95, record.getReadLength());
        writer.write(record);
      }
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }
}