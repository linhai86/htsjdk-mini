package com.bfx.htsjdk_mini;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FastqReader implements Iterable<FastqRecord>, Closeable {

  private final BufferedReader reader;

  public FastqReader(final Path path) throws IOException {
    this.reader = Files.newBufferedReader(path);
  }

  private FastqRecord readNextRecord() {
    FastqRecord record = null;
    try {
      final String seqHeader = reader.readLine();
      final String seqLine = reader.readLine();
      final String qualHeader = reader.readLine();
      final String qualLine = reader.readLine();

      if (seqHeader != null && seqLine != null && qualHeader != null && qualLine != null) {
        record = new FastqRecord(seqHeader, seqLine, qualHeader, qualLine);
      }
    } catch (IOException e) {
      System.err.format("IOException: %s%n", e);
    }
    return record;
  }

  public class FastqIterator implements Iterator<FastqRecord> {
    private FastqRecord nextRecord;

    FastqIterator() {
      this.nextRecord = readNextRecord();
    }
    @Override
    public boolean hasNext() {
      return nextRecord != null;
    }

    @Override
    public FastqRecord next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      final FastqRecord record = nextRecord;
      nextRecord = readNextRecord();
      return record;
    }
  }

  @Override
  public Iterator<FastqRecord> iterator() {
    return new FastqIterator();
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }
}
