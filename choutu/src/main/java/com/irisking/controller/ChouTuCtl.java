package com.irisking.controller;


import com.alibaba.fastjson.JSON;
import com.irisking.IKAExpandServer;
import com.irisking.IrisImageInfoCloudStruct;
import com.irisking.config.WorkParams;
import com.irisking.entry.ChouTuBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.Base64;

import static com.irisking.util.ImageUtil.getImageData;

@RestController
public class ChouTuCtl {

    private static final Logger log = LoggerFactory.getLogger(ChouTuCtl.class);
    /**
     * post 示例
     * @return
     */
    @RequestMapping(value = "/ctPost", method= RequestMethod.POST)
    public ChouTuBean pstPeople(@RequestBody ChouTuBean chouTuBean){

        ChouTuBean result = new ChouTuBean();
        try {
            String picinfo = chouTuBean.getPicInfo();

            byte[] imageData = Base64.getDecoder().decode(picinfo);
            imageData = getImageData(imageData);
            /** 抽取特征 **/
            IrisImageInfoCloudStruct irisImageInfo = new IrisImageInfoCloudStruct();

            IKAExpandServer ikaExpandServer = new IKAExpandServer();
            int extractResult = ikaExpandServer.extractIrisFeature(imageData, WorkParams.IMAGE_HEIGHT,  WorkParams.IMAGE_WIDTH,irisImageInfo,WorkParams.WORK_MODE);
            log.info("result:*****************************************  "+extractResult);
            if(extractResult == WorkParams.RETURN_SUCCESS){

                Base64.Encoder encoder= Base64.getEncoder();
                String irisEnrFeatBase64 = encoder.encodeToString(irisImageInfo.getIrisEnrTemplate());
                result.setShibie(irisEnrFeatBase64);
                String irisRecFeatBase64 = encoder.encodeToString(irisImageInfo.getIrisRecTemplate());
                result.setZhuce(irisRecFeatBase64);
                result.setFlag(true);
            }else {
                result.setFlag(false);
            }

        }catch (Exception e){
            result.setFlag(false);
            log.error("error",e);
        }
        log.info("code:"+result.isFlag());
        log.info("zhuce:"+result.getZhuce());
        log.info("shibie:"+result.getShibie());
        return result;
    }




    public static void main(String[] asd){
        ChouTuBean result = new ChouTuBean();
        result.setFlag(true);
        result.setZhuce("asdas");
        result.setPicInfo("asdasda");

        System.out.println(JSON.toJSONString(result));
    }
}
