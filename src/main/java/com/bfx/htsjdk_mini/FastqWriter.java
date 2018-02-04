package com.bfx.htsjdk_mini;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FastqWriter implements Closeable, Flushable {

  private final BufferedWriter writer;

  public FastqWriter(final Path path) throws IOException {
    this.writer = Files.newBufferedWriter(path);
  }

  public void write(final FastqRecord record) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(record.getReadName());
    sb.append('\n');
    sb.append(record.getReadString());
    sb.append('\n');
    sb.append(record.getBaseQualityHeader());
    sb.append('\n');
    sb.append(record.getBaseQualityString());
    sb.append('\n');
    writer.write(sb.toString());
  }

  @Override
  public void flush() throws IOException {
    writer.flush();
  }

  @Override
  public void close() throws IOException {
    writer.close();
  }
}
