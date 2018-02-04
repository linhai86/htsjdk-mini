package com.bfx.htsjdk_mini;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.junit.Assert.*;

public class FastqReaderTest {
  private Path path;

  @Before
  public void setUp() throws Exception {
    path = Paths.get("src","test","resources", "input", "illumina64.fastq");
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void foreach() {
    try (FastqReader reader = new FastqReader(path)) {
      int i = 0;
      for (FastqRecord record : reader) {
        i++;
        assertEquals(95, record.getReadLength());
      }
      assertEquals(20, i);
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }

  @Test
  public void iterator() {
    try (FastqReader reader = new FastqReader(path)) {
      Iterator<FastqRecord> iterator = reader.iterator();
      assertTrue(iterator.hasNext());
      int i = 0;
      while (iterator.hasNext()) {
        i++;
        FastqRecord record = iterator.next();
        assertEquals(95, record.getReadLength());
      }
      assertEquals(20, i);
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
  }
}