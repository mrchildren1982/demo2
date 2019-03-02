package com.example.demo.domain.enums;

public class Range {
	 public long start;
     public long end;
     public long length;
     public long total;

     /**
      * Construct a byte range.
      * @param start Start of the byte range.
      * @param end End of the byte range.
      * @param total Total length of the byte source.
      */
     public Range(long start, long end, long total) {
         this.start = start;
         this.end = end;
         this.length = end - start + 1;
         this.total = total;
     }

}
