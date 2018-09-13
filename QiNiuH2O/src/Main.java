import javassist.ClassPool;
import javassist.CtClass;
import tools.JARDecompressionTool;

import java.io.File;

public class Main {


    public static void main(String[] args) {

        File file = new File("QiNiuH2O/libs/pldroid-player-1.5.1.jar");
        JARDecompressionTool.decompress(file, "QiNiuH2O/output");

        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath("QiNiuH2O/libs/pldroid-player-1.5.1.jar");


            CtClass jkLibLoader = QiNiu.jkLibLoader(classPool);
            CtClass sharedLibraryNameHelper = QiNiu.sharedLibraryNameHelper(classPool);


            jkLibLoader.writeFile("QiNiuH2O/output");
            sharedLibraryNameHelper.writeFile("QiNiuH2O/output");

            File jarFile = new File("QiNiuH2O/libs/pldroid-player-1.5.1_new.jar");
            File sourceDir = new File("QiNiuH2O/output");
            JARDecompressionTool.compress(sourceDir, jarFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
