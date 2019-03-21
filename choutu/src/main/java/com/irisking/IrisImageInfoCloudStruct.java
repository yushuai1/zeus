package com.irisking;
/**
 * output iris feature and image
 * @author zlb
 *
 */
public class IrisImageInfoCloudStruct {
	static final int IR_IMAGE_SISE = 480 * 640;
	static final int IR_ENR_FEATURE_LENGTH = 512;
	static final int IR_REC_FEATURE_LENGTH = 1024;
	static final int IR_UnknownInfoLen = 100; // 算法内部参数交换区域大小，用户可见，但是用不不允许改变该区域的值

	//////////////////////////////////////////////////////////////////////////
	// Input Parameters
	//////////////////////////////////////////////////////////////////////////

	// Height of the iris image in pixels. It must be fixed to 480 currently.
	int imageH;

	// Width of the iris image in pixels. It must be fixed to 640 currently.
	int imageW;

	// Type of Image
	// IR_IM_VGA_LEFF: VGA,Left
	// IR_IM_VGA_RIGHT: VGA,right
	// IR_IM_VGA_UNDEF: Unkown left or right
	// IR_IM_NONE: there is no valid image
	int imType;

	// Code of iris localization method, defined as follows:
	// IR_LOC_DEFAULT: Default technique determined by CASIA_GEN algorithm.
	// IR_LOC_CIRCLE : Use circle fitting strategy to localize the iris edges.
	// IR_LOC_SNAKE : Use edge fitting strategy (e.g. active contour) to localize
	// the iris edges.
	int locMethod; // 20151213 下一版本修改中, 将此变量删除,替换为 DeviceType

	// Specify whether to enable spoof eye detection
	// IR_NO_SPOOF_DETECT : Do not perform spoof eye detection
	// IR_DO_SPOOF_DETECT : Perform spoof eye detection
	int ifSpoofDetect;

	// In which status the iris image is processed
	int processStatus;

	// The row index of pupil center in iris image, ranging from 1 to
	// IR_IMAGE_HEIGHT.
	float pupilRow;

	// The column index of pupil center in iris image,ranging from 1
	// toIR_IMAGE_WIDTH
	float pupilCol;

	// The radius of pupil in iris image
	float pupilRadius;

	//// The confidence of pupillary boundary localization, ranging from 0-100, the
	//// larger
	//// the confidence, the better the pupillary boundary localization is.
	// int pupilBdConf;

	// The row index of iris center in iris image, ranging from 1 to IR_IMAGE_HEIGHT
	float irisRow;

	// The column index of iris center in iris image, ranging from 1 to
	// IR_IMAGE_WIDTH
	float irisCol;

	// The radius of iris in iris image
	float irisRadius;

	// The position that the pupillary boundary cross the image boundaries.
	// ranging from 0-9, see the micro-definitions whose name begin with
	// IR_POS_*******
	int pupilPos;

	// THe percentage of visible part of the pupillary boundary.
	int pupilVisiblePercent;

	// The position that the limbic boundary cross the image boundaries.
	// ranging from 0-9, see the micro-definitions whose name begin with
	// IR_POS_*******
	int irisPos;

	// THe percentage of visible part of the iris boundary.
	int irisVisiblePercent;

	// The overall percentage of visible part of the annular iris region
	int overallVisiblePercent;

	// Indicator of the focus level of the iris image, ranging from 0 to 100. High
	// value of focusScore means clear iris image and low value of focusScore means
	// defocused iris image.
	int focusScore;

	// Percentage of the visible part of the iris., ranging from 0 to 100. 100 means
	// no occlusion while 65 means 65 percent of the iris are occluded.
	int percentVisible;

	// Number of valid iris feature bits of the iris.
	// 20151117:在CASIA_Gen_JudgeImQuality 函数中借用 numValidBits保存第二次计算得到的黑脸判断函数)
	// 20151125:在Code函数中，赋予该变量其实际含义，即有效的bit数
	int numValidBits;

	// Confidence of the input iris image is obtained from spoof eye.
	// 0 means the image is obtained from genuine eye, while 100 means the image is
	// a spoof one.
	int spoofValue;

	// Signal to noise ratio of the iris image.
	float SNR;

	// Saturation level of the iris image.
	float Saturation;

	// Indicator of the how much the image is not interlaced. Higher value indicates
	// the iris image is less interlaced.
	float interlaceValue;

	// The overall quality of the iris image, can be one of the following levels.
	int QualityLevel;
	int QualityScore;

	// 1D array of pixel intensity values of the original iris image
	byte[] imageData;
	// 1D array of pixel intensity values of the demo iris image. Pupil/iris and
	// iris/sclera boundary points are marked with white pixels.
	byte[] locImage;
	// 标记了每个像素是否是遮挡区域的图像
	byte[] maskImage;
	// Iris image feature template enrolled into database, with fixed length 512
	// bytes.
	byte[] irisEnrTemplate;
	// Iris image feature template of recognition image, with fixed length 1024
	// bytes.
	byte[] irisRecTemplate;

	int[] irisInfoUnkonwn;

	public int getImageH() {
		return imageH;
	}

	public int getImageW() {
		return imageW;
	}

	public int getImType() {
		return imType;
	}

	public int getLocMethod() {
		return locMethod;
	}

	public int getIfSpoofDetect() {
		return ifSpoofDetect;
	}

	public int getProcessStatus() {
		return processStatus;
	}

	public float getPupilRow() {
		return pupilRow;
	}

	public float getPupilCol() {
		return pupilCol;
	}

	public float getPupilRadius() {
		return pupilRadius;
	}

	public float getIrisRow() {
		return irisRow;
	}

	public float getIrisCol() {
		return irisCol;
	}

	public float getIrisRadius() {
		return irisRadius;
	}

	public int getPupilPos() {
		return pupilPos;
	}

	public int getPupilVisiblePercent() {
		return pupilVisiblePercent;
	}

	public int getIrisPos() {
		return irisPos;
	}

	public int getIrisVisiblePercent() {
		return irisVisiblePercent;
	}

	public int getOverallVisiblePercent() {
		return overallVisiblePercent;
	}

	public int getFocusScore() {
		return focusScore;
	}

	public int getPercentVisible() {
		return percentVisible;
	}

	public int getNumValidBits() {
		return numValidBits;
	}

	public int getSpoofValue() {
		return spoofValue;
	}

	public float getSNR() {
		return SNR;
	}

	public float getSaturation() {
		return Saturation;
	}

	public float getInterlaceValue() {
		return interlaceValue;
	}

	public int getQualityLevel() {
		return QualityLevel;
	}

	public int getQualityScore() {
		return QualityScore;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public byte[] getLocImage() {
		return locImage;
	}

	public byte[] getMaskImage() {
		return maskImage;
	}

	public byte[] getIrisEnrTemplate() {
		return irisEnrTemplate;
	}

	public byte[] getIrisRecTemplate() {
		return irisRecTemplate;
	}

	public int[] getIrisInfoUnkonwn() {
		return irisInfoUnkonwn;
	}

}

