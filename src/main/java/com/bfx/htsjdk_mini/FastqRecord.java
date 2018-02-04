package com.bfx.htsjdk_mini;

public class FastqRecord {
  private final String readName;
  private final String readString;
  private final String qualityHeader;
  private final String baseQualityString;

  public FastqRecord(final String readName, final String readBases,
                     final String qualityHeader, final String baseQualities) {
    this.readName = readName;
    this.readString = readBases;
    this.qualityHeader = qualityHeader;
    this.baseQualityString = baseQualities;
  }

  public String getReadName() {
    return readName;
  }

  public String getReadString() {
    return readString;
  }

  public String getBaseQualityHeader() {
    return qualityHeader;
  }

  public String getBaseQualityString() {
    return baseQualityString;
  }

  public int getReadLength() {
    return (readString == null) ? 0 : readString.length();
  }

  @Override
  public String toString() {
    return "FastqRecord{\n" +
        "readName='" + readName + '\'' +
        ",\n readString='" + readString + '\'' +
        ",\n qualityHeader='" + qualityHeader + '\'' +
        ",\n baseQualityString='" + baseQualityString + '\'' +
        "\n}";
  }
}
