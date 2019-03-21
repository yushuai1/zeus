package com.irisking;

/**
 * 
 * @author zlb
 *
 */
public class IKAExpandServer {
	
	 /**
     * 按指定文件夹路径取文件，然后绘制ROC曲线
     * @param folderPath  待评测的图片文件所在目录，要求待评测的图片都放在这个目录下
     * @param clsLabelLen 分离长度，如“0001-01-001-” 共12个字符，传固定值12
     * @param deviceType  设备类型编码，目前传固定值 2
     * @param flag        标志指示值，目前传固定值 0
     * @return 返回可以Json化的String
     *          {
     *              "length"(int): // 指定数据点的长度
     *              "pointPair"(array):[ //二维坐标点的数组
     *                              [double, double], // 一个坐标点里第一个double值是横坐标，第二个double值是纵坐标
     *                              [double, double],
     *                              [double, double]
     *              ]
     *          }
     */
    public native String drawROC(String folderPath, int clsLabelLen, int deviceType, int flag);
    
	/**
	 *获取图像的注册特征和识别特征
	 *@param imageData    图像的Byte数据
	 *@param deviceType   设备类型，默认为2
	 *@ return            特征数组，数组从前往后组成为：注册特征标志(0成功，1失败) 1byte + 注册特征 512 bytes + 识别特征标志（(0成功，1失败)） 1byte  + 识别特征 1024 bytes
	*/
	public native byte[] extractEnrAndRecFeat(byte[] imageData,int deviceType);
	
	/**
	 * 从虹膜图片中抽取特征
	 * @param srcIm			输入虹膜图像，默认bmp文件
	 * @param imageH		图像的高,默认480
	 * @param imageW		图像的宽,默认640
	 * @param pIrisImage	虹膜输出结构体
	 * @param workMode		工作模式,默认0
	 * @return 0 成功；非0 失败
	 */
	public native int extractIrisFeature(byte[] srcIm, int imageH, int imageW, IrisImageInfoCloudStruct pIrisImage, int workMode);

	
	/**
	 * 获取libIKDeviceD算法库的版本号
	 * @return
	 */
	public native String getAlgoVersion();
	
	
    
}
