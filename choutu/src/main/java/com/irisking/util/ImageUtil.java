package com.irisking.util;


import java.util.Base64;


/**
 * Created by zkhb on 2018/3/11.
 * 根据图像的标识位判断虹膜图像的类型，获取图像数据
 */
public class ImageUtil {
    public static byte[] getImageData(byte[] imageInfo) throws Exception{
        byte[] imageData = null;
        if(imageInfo.length <4){
            return imageInfo;
        }
        //读取文件的前几个字节来判断图片格式
        byte[] headerFlag = new byte[4];
            System.arraycopy(imageInfo,0,headerFlag,0,headerFlag.length);
            String type = bytesToHexString(headerFlag).toUpperCase();
            if (type.contains(ImageTypeEnum.TYPE_JPG.getValue())) {
                //return TYPE_JPG;
            } else if (type.contains(ImageTypeEnum.TYPE_PNG.getValue())) {
               // return TYPE_PNG;
            } else if (type.contains(ImageTypeEnum.TYPE_GIF.getValue())) {
               // return TYPE_GIF;
            } else if (type.contains(ImageTypeEnum.TYPE_BMP.getValue())) {
                //return TYPE_BMP;
                imageData = new byte[imageInfo.length-1078];
                System.arraycopy(imageInfo,1078,imageData,0,imageInfo.length - 1078);
            }else{
                imageData = imageInfo;
            }

        return imageData;
    }


    /**
     * byte数组转换成16进制字符串
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] arge){

        byte[] imageData=null;

        imageData =  Base64.getDecoder().decode("Qk02tAQAAAAAADYEAAAoAAAAgAIAAOABAAABAAgAAAAAAACwBAAAAAAAAAAAAAABAAAAAAAAAAAAAAEBAQACAgIAAwMDAAQEBAAFBQUABgYGAAcHBwAICAgACQkJAAoKCgALCwsADAwMAA0NDQAODg4ADw8PABAQEAAREREAEhISABMTEwAUFBQAFRUVABYWFgAXFxcAGBgYABkZGQAaGhoAGxsbABwcHAAdHR0AHh4eAB8fHwAgICAAISEhACIiIgAjIyMAJCQkACUlJQAmJiYAJycnACgoKAApKSkAKioqACsrKwAsLCwALS0tAC4uLgAvLy8AMDAwADExMQAyMjIAMzMzADQ0NAA1NTUANjY2ADc3NwA4ODgAOTk5ADo6OgA7OzsAPDw8AD09PQA+Pj4APz8/AEBAQABBQUEAQkJCAENDQwBEREQARUVFAEZGRgBHR0cASEhIAElJSQBKSkoAS0tLAExMTABNTU0ATk5OAE9PTwBQUFAAUVFRAFJSUgBTU1MAVFRUAFVVVQBWVlYAV1dXAFhYWABZWVkAWlpaAFtbWwBcXFwAXV1dAF5eXgBfX18AYGBgAGFhYQBiYmIAY2NjAGRkZABlZWUAZmZmAGdnZwBoaGgAaWlpAGpqagBra2sAbGxsAG1tbQBubm4Ab29vAHBwcABxcXEAcnJyAHNzcwB0dHQAdXV1AHZ2dgB3d3cAeHh4AHl5eQB6enoAe3t7AHx8fAB9fX0Afn5+AH9/fwCAgIAAgYGBAIKCggCDg4MAhISEAIWFhQCGhoYAh4eHAIiIiACJiYkAioqKAIuLiwCMjIwAjY2NAI6OjgCPj48AkJCQAJGRkQCSkpIAk5OTAJSUlACVlZUAlpaWAJeXlwCYmJgAmZmZAJqamgCbm5sAnJycAJ2dnQCenp4An5+fAKCgoAChoaEAoqKiAKOjowCkpKQA");
        try {
            imageData = getImageData(imageData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }

}
