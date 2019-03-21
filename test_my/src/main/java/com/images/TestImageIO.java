package com.images;


import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.*;
import javax.imageio.stream.*;



public class TestImageIO {

    public static void main(String[] args) throws Exception {

        FileImageInputStream fiis=new FileImageInputStream(new File("E:\\log\\timg.jpg"));
        FileImageOutputStream fios=new FileImageOutputStream(new File("E:\\log\\time.bmp"));

        ImageReader jpegReader = null;
        Iterator<ImageReader> it1 = ImageIO.getImageReadersByFormatName("jpeg");
        if(it1.hasNext())
        {
            jpegReader = it1.next();
        }
        jpegReader.setInput(fiis);

        ImageWriter bmpWriter = null;
        Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName("bmp");
        if(it2.hasNext())
        {
            bmpWriter = it2.next();
        }
        bmpWriter.setOutput(fios);
        BufferedImage br = jpegReader.read(0);
        bmpWriter.write(br);
        fiis.close();
        fios.close();
        System.out.println("Jpeg到bmp图片转换完成.");
    }

}

