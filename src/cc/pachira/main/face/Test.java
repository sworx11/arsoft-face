package cc.pachira.main.face;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        FaceUtils faceUtils = FaceUtils.getInstance();
        File image1 = new File("C:\\Users\\john\\Desktop\\images\\man1.jpg");
        File image2 = new File("C:\\Users\\john\\Desktop\\images\\man2.jpg");
        long start = System.currentTimeMillis();
        Face face1 = faceUtils.execute(image1);
        Face face2 = faceUtils.execute(image2);
        long end = System.currentTimeMillis();
        System.out.println("spent:" + (end - start) + "millis");
        System.out.println("face1----------------------------");
        System.out.println("特征值大小：" + face1.getFaceFeature().getFeatureData().length);
        System.out.println("性别：" + face1.getGenderInfoArray().get(0).getGender());
        System.out.println("年龄：" + face1.getAgeInfoArray().get(0).getAge());
        System.out.println("3D角度：" + face1.getFaceAngleArray().get(0).getPitch() + "," + face1.getFaceAngleArray().get(0).getRoll() + "," + face1.getFaceAngleArray().get(0).getYaw());
        System.out.println("活体：" + face1.getLivenessInfoArray().get(0).getLiveness());
        System.out.println("IR活体：" + face1.getIrLivenessInfoArray().get(0).getLiveness());
        System.out.println("face2----------------------------");
        System.out.println("特征值大小：" + face2.getFaceFeature().getFeatureData().length);
        System.out.println("性别：" + face2.getGenderInfoArray().get(0).getGender());
        System.out.println("年龄：" + face2.getAgeInfoArray().get(0).getAge());
        System.out.println("3D角度：" + face2.getFaceAngleArray().get(0).getPitch() + "," + face2.getFaceAngleArray().get(0).getRoll() + "," + face2.getFaceAngleArray().get(0).getYaw());
        System.out.println("活体：" + face2.getLivenessInfoArray().get(0).getLiveness());
        System.out.println("IR活体：" + face2.getIrLivenessInfoArray().get(0).getLiveness());
        System.out.println("similar--------------------------");
        System.out.println(faceUtils.similar(face1, face2));

    }
}
