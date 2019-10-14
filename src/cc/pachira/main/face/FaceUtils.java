package cc.pachira.main.face;

import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageFactory;
import com.arcsoft.face.toolkit.ImageInfo;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FaceUtils {
    private static final long serialVersionUID = 1L;

    private static final long SIZE = 5L;

    private static volatile FaceUtils instance = null;

    private volatile FaceEngine faceEngine;

    private boolean initStatus = false;

    public static synchronized FaceUtils getInstance() {
        if (null == instance) {
            instance = new FaceUtils();
        }
        return instance;
    }

    private FaceUtils(){
        faceEngine = new FaceEngine(FaceConfig.enginePath);
        //激活引擎
        int activeCode = faceEngine.activeOnline(FaceConfig.appID, FaceConfig.appSecret);

        if (activeCode != ErrorInfo.MOK.getValue() && activeCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }

        //功能配置
        FunctionConfiguration functionConfiguration = FunctionConfiguration
                .builder()
                .supportAge(true)
                .supportFace3dAngle(true)
                .supportFaceDetect(true)
                .supportFaceRecognition(true)
                .supportGender(true)
                .supportLiveness(true)
                .supportIRLiveness(true)
                .build();

        //引擎配置
        EngineConfiguration engineConfiguration = EngineConfiguration
                .builder()
                .detectMode(DetectMode.ASF_DETECT_MODE_IMAGE)
                .detectFaceOrientPriority(DetectOrient.ASF_OP_0_ONLY)
                .functionConfiguration(functionConfiguration)
                .build();

        //初始化引擎
        int initCode = faceEngine.init(engineConfiguration);

        if (initCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        } else {
            this.initStatus = true;
        }
    }

    public Face execute(File image) {
        ImageInfo rgbData = ImageFactory.getRGBData(image);
        ImageInfo grayData = ImageFactory.getGrayData(image);
        return execute(rgbData, grayData);
    }

    public Face execute(byte[] image) {
        ImageInfo rgbData = ImageFactory.getRGBData(image);
        ImageInfo grayData = ImageFactory.getGrayData(image);
        return execute(rgbData, grayData);
    }

    public Face execute(InputStream image) {
        ImageInfo rgbData = ImageFactory.getRGBData(image);
        ImageInfo grayData = ImageFactory.getGrayData(image);
        return execute(rgbData, grayData);
    }

    public synchronized Face execute(ImageInfo rgbData, ImageInfo grayData) {
        if (!this.initStatus) {
            System.out.println("引擎未初始化");
            return null;
        }

        Face face = new Face();

        FunctionConfiguration configuration = FunctionConfiguration
                .builder()
                .supportAge(true)
                .supportFace3dAngle(true)
                .supportGender(true)
                .supportLiveness(true)
                .build();

        FunctionConfiguration irConfiguration = FunctionConfiguration
                .builder()
                .supportIRLiveness(true)
                .build();

        faceEngine.detectFaces(rgbData.getImageData(), rgbData.getWidth(), rgbData.getHeight(), rgbData.getImageFormat(), face.getFaceInfoArray());
        faceEngine.extractFaceFeature(rgbData.getImageData(), rgbData.getWidth(), rgbData.getHeight(), rgbData.getImageFormat(), face.getFaceInfoArray().get(0), face.getFaceFeature());
        faceEngine.process(rgbData.getImageData(), rgbData.getWidth(), rgbData.getHeight(), rgbData.getImageFormat(), face.getFaceInfoArray(), configuration);
        faceEngine.getAge(face.getAgeInfoArray());
        faceEngine.getGender(face.getGenderInfoArray());
        faceEngine.getFace3DAngle(face.getFaceAngleArray());
        faceEngine.getLiveness(face.getLivenessInfoArray());

        faceEngine.detectFaces(grayData.getImageData(), grayData.getWidth(), grayData.getHeight(), grayData.getImageFormat(), face.getFaceInfoArrayGray());
        faceEngine.processIr(grayData.getImageData(), grayData.getWidth(), grayData.getHeight(), grayData.getImageFormat(), face.getFaceInfoArrayGray(), irConfiguration);
        faceEngine.getLivenessIr(face.getIrLivenessInfoArray());

        return face;
    }

    public float similar(Face f1, Face f2) {
        if (null == f1.getFaceFeature() || null == f2.getFaceFeature() )
            return 0f;

        FaceSimilar similar = new FaceSimilar();
        faceEngine.compareFaceFeature(f1.getFaceFeature(), f2.getFaceFeature(), similar);
        return similar.getScore();
    }
}
