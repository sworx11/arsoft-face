package cc.pachira.main.face;

import com.arcsoft.face.*;
import java.util.ArrayList;
import java.util.List;


public class Face {
    private List<FaceInfo> faceInfoArray = new ArrayList<>();
    private List<FaceInfo> faceInfoArrayGray = new ArrayList<>();
    private FaceFeature faceFeature = new FaceFeature();
    private List<AgeInfo> ageInfoArray = new ArrayList<>();
    private List<GenderInfo> genderInfoArray = new ArrayList<>();
    private List<Face3DAngle> faceAngleArray = new ArrayList<>();
    private List<LivenessInfo> livenessInfoArray = new ArrayList<>();
    private List<IrLivenessInfo> irLivenessInfoArray = new ArrayList<>();

    public List<FaceInfo> getFaceInfoArray() {
        return faceInfoArray;
    }

    public void setFaceInfoArray(List<FaceInfo> faceInfoArray) {
        this.faceInfoArray = faceInfoArray;
    }

    public List<FaceInfo> getFaceInfoArrayGray() {
        return faceInfoArrayGray;
    }

    public void setFaceInfoArrayGray(List<FaceInfo> faceInfoArrayGray) {
        this.faceInfoArrayGray = faceInfoArrayGray;
    }

    public FaceFeature getFaceFeature() {
        return faceFeature;
    }

    public void setFaceFeature(FaceFeature faceFeature) {
        this.faceFeature = faceFeature;
    }

    public List<AgeInfo> getAgeInfoArray() {
        return ageInfoArray;
    }

    public void setAgeInfoArray(List<AgeInfo> ageInfoArray) {
        this.ageInfoArray = ageInfoArray;
    }

    public List<GenderInfo> getGenderInfoArray() {
        return genderInfoArray;
    }

    public void setGenderInfoArray(List<GenderInfo> genderInfoArray) {
        this.genderInfoArray = genderInfoArray;
    }

    public List<Face3DAngle> getFaceAngleArray() {
        return faceAngleArray;
    }

    public void setFaceAngleArray(List<Face3DAngle> faceAngleArray) {
        this.faceAngleArray = faceAngleArray;
    }

    public List<LivenessInfo> getLivenessInfoArray() {
        return livenessInfoArray;
    }

    public void setLivenessInfoArray(List<LivenessInfo> livenessInfoArray) {
        this.livenessInfoArray = livenessInfoArray;
    }

    public List<IrLivenessInfo> getIrLivenessInfoArray() {
        return irLivenessInfoArray;
    }

    public void setIrLivenessInfoArray(List<IrLivenessInfo> irLivenessInfoArray) {
        this.irLivenessInfoArray = irLivenessInfoArray;
    }
}
