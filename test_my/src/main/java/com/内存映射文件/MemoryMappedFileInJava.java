package com.内存映射文件;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class MemoryMappedFileInJava {


  private static int count = 1024; // 10 MB


  public static void main(String[] args) throws Exception {


    RandomAccessFile memoryMappedFile = new RandomAccessFile("largeFile.txt", "rw");


    // Mapping a file into memory
    MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, count);

    // Writing into Memory Mapped File
//    for (int i = 0; i < count; i++) {
//      out.put((byte) 'A');
//      if (i==20){
//          System.exit(0);
//      }
//    }

    System.out.println("Writing to Memory Mapped File is completed");


    // reading from memory file in Java
    for (int i = 0; i < 10; i++) {
      System.out.println((char) out.get(i));
    }
    System.out.println("Reading from Memory Mapped File is completed");


    memoryMappedFile.close();
  }


}
