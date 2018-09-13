import com.tendcloud.tenddata.TalkingData;
import javassist.ClassPool;
import javassist.CtClass;
import tools.JARDecompressionTool;

import java.io.File;
import java.util.Map;

public class Youga {


    public static void main(String[] args) {

        File file = new File("TalkingData/libs/TalkingData_Analytics_Android_SDK_V2.2.30.jar");
        JARDecompressionTool.decompress(file, "TalkingData/output");

        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.importPackage("java.util");
            classPool.importPackage("java.util.concurrent");
            classPool.insertClassPath("TalkingData/libs/TalkingData_Analytics_Android_SDK_V2.2.30.jar");
            classPool.insertClassPath(Map.class.getName());

            CtClass gi = TalkingData.register(classPool);

            gi.writeFile("TalkingData/output");

            File jarFile = new File("TalkingData/libs/TalkingData_Analytics_Android_SDK_V2.2.30_new.jar");
            File sourceDir = new File("TalkingData/output");
            JARDecompressionTool.compress(sourceDir, jarFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
