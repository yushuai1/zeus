package pro.add;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import djni.nativec.DevicedCore;
import djni.nativec.ImageUtil;
import djni.nativec.IrisImage;
import pro.MytOuterClass;
import pro.Tcp;

public class Test {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        String path = "C:\\Users\\yushuai\\Desktop\\chengdu\\bmp头文件解析\\biHeight为负\\1.bmp";
        System.out.println(path);
        byte[] nn = ImageUtil.readLocationImage(path);
        IrisImage irisImageInfo = new IrisImage();
        int extractResult = DevicedCore.GetFeature(irisImageInfo, nn);
        System.out.println(extractResult);


        FeatureAdd.TaskData.Builder myt = FeatureAdd.TaskData.newBuilder();
        myt.setPersonId("yushaui");
        myt.setCmd("TJ");
        myt.setWater("water");
        myt.setGroupId(100);
        FeatureAdd.AddFeature.Builder a = FeatureAdd.AddFeature.newBuilder();
        a.setEyeFlag(1);
        a.setFeatureId("featureida");
        a.setIrisEnrLength(512);
        byte[] enr0 = irisImageInfo.getIrisEnrTemplate();
        a.setIrisEnrTemplate(ByteString.copyFrom(enr0));
        a.setIrisRecLength(1024);
        byte[] rec0 = irisImageInfo.getIrisRecTemplate();
        a.setIrisRecTemplate(ByteString.copyFrom(rec0));

        FeatureAdd.AddFeature.Builder b = FeatureAdd.AddFeature.newBuilder();
        b.setEyeFlag(1);
        b.setFeatureId("featureid");
        b.setIrisEnrLength(512);
        byte[] enr = irisImageInfo.getIrisEnrTemplate();
        b.setIrisEnrTemplate(ByteString.copyFrom(enr));
        b.setIrisRecLength(1024);
        byte[] rec = irisImageInfo.getIrisRecTemplate();
        b.setIrisRecTemplate(ByteString.copyFrom(rec));
        myt.addFeatureList(a).addFeatureList(b);
        FeatureAdd.TaskData bytes = myt.build();
        byte[] data = bytes.toByteArray();
        Tcp tcp = new Tcp();
        byte[] fanhui = tcp.sentTcp(data);
        FeatureAdd.TaskData obj =  FeatureAdd.TaskData.parseFrom(fanhui);

        System.out.println(obj.getPersonId());
    }
}
