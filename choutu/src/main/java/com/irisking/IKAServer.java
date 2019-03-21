
package com.irisking;

import org.springframework.stereotype.Component;

/**
 * 胜云1.0 JNI接口说明
 * @author zlb 
 */
@Component
public class IKAServer {
	/**
	 * 向算法服务添加特征值
	 * 
	 * @author
	 * @param feature 注册特征一个
	 * @param appId
	 *            应用id
	 * @param eyeMode
	 *            眼睛模式 1 左眼 2 右眼
	 * @return 特征值在算法服务中的序号(大于等于0), 返回负数,参见错误码
	 */
	public native long enrollFeature(byte[] feature, long appId, int eyeMode,long blockIndex);

	/**
	 * 从算法服务中删除指定序号的特征值
	 * @author 
	 * @param appId
	 *            应用id
	 * @param eyeMode
	 *            眼睛模式 1 左眼 2 右眼
	 * @param targetIndex
	 * @return 用来补位的特征值原来的index(大于等于0),  返回负数,参见错误码
	 */
	public native long[] removeFeature(long appId, int eyeMode, long[] targetIndex);
	
    

    /**
     * 特征比对（返回多个结果）
     * @param feature 识别特征值结构的字节数组
     * @param appId 应用id
     * @param eyeMode 眼睛模式 1 左眼 2 右眼 0左右眼
     * @param blockIndexArray 特征块索引
     * @param blockCount   	特征块数,如果为-1，表示比较appId下的所有块
     * @return 匹配到的特征索引结合,以字符串拼接的形式返回，格式为code[index:score,index:score...]
     */
    public native String irisMatch(byte[]feature, long appId, int eyeMode,long[] blockIndexArray,int blockCount);
    
    /**
     * （通用）
     * 获取特征堆尺寸（特征个数），
     * @param appId 应用id
     * @param eyeMode 眼睛模式 1 左眼 2 右眼 
     * @return
     */
    public native long getFeatureHeapSize(long appId, int eyeMode);
    
    /**
     * （通用）
     * 释放appContainer中特征堆的空间，主要是为重新加载特征数据
     * @return 返回0,表示成功
     */
    public native int release();
	
	/**
     *  用enrollFeatureBytes更新内存中targetedIndex对应的特征
     * @param appId
     * @param eyeMode
     * @param targetedIndex
     * @param enrollFeatureBytes
     * @return 返回更新的最后一个索引，如果为负数，表示失败
     */
    public native long updateFeature(long appId,int eyeMode,long targetedIndex,byte[] enrollFeatureBytes);
    
    /**
     * 分配特征块
     * @param appId
     * @param eyeMode
     * @param blockSize 特征块的大小,单位:1个注册特征
     * @return 分配的块的索引
     */
    public native long allocateBlock(long appId,int eyeMode,int blockSize);
    /**
     * 获取特征块的容量
     * @param appId
     * @param eyeMode
     * @param blockIndex 特征块的索引
     * @return 块容量（单位:一个注册特征）
     */
    public native int getBlockCapacity(long appId,int eyeMode,long blockIndex);
    /**
     * 获取该特征块的已使用量
     * @param appId
     * @param eyeMode
     * @param blockIndex
     * @return 块使用量（单位:一个注册特征）
     */
    public native int getUsageInBlock(long appId,int eyeMode,long blockIndex);
    /**
     * 获取该特征块的剩余容量
     * @param appId
     * @param eyeMode
     * @param blockIndex
     * @return 块剩余量（单位:一个注册特征）
     */
    public native int getRemainInBlock(long appId,int eyeMode,long blockIndex);
    /**
     * 获取特征块的数量
     * @param appId
     * @param eyeMode
     * @return 特征块数
     */
    public native long getBlockQuantity(long appId,int eyeMode);
    
    /** 
	 * 1:1 比对接口，返回匹配到最高分（全比对）
	 * @author 
	 * @param enrollFeatBytes  一组注册特征
	 * @param recogFeatBytes   一组识别特征
	 * @return >=0,表示比对成功，返回最高分数，<0 ，表示比对失败
	 */
	public native int bestMatch(byte[] enrollFeatBytes, byte[] recogFeatBytes);

	/**
	 * 1:1 比对接口（匹配到就返回）
	 * @author 
	 * @param enrollFeatBytes
	 * @param recogFeatBytes
	 * @return =0，表示比对成功，<0，表示比对失败
	 */
	public native int match(byte[] enrollFeatBytes, byte[] recogFeatBytes);
	
	/**
	 * 获取libIKAlgoIden算法库的版本号
	 * @return
	 */
	public native String getAlgoVersion();
	
}
