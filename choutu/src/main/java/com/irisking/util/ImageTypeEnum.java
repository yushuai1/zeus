package com.irisking.util;

/**
 * 图片类型标识
 * Created by zkhb on 2018/3/11.
 */
public enum ImageTypeEnum {

    TYPE_BMP(0,"424D"),
    TYPE_JPG(1,"FFD8FF"),
    TYPE_PNG(2,"89504E47"),
    TYPE_GIF(3,"47494638"),
    TYPE_UNKNOWN(4,"unknown");
    ImageTypeEnum(int type, String value){
        this.type = type;
        this.value = value;
    }



    private final int type;

    private final String value;

    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
