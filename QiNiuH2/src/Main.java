import javassist.*;
import tools.JARDecompressionTool;

import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        File file = new File("QiNiuH2/libs/pldroid-player-2.1.1.jar");
        JARDecompressionTool.decompress(file, "QiNiuH2/output");

        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath("QiNiuH2/libs/pldroid-player-2.1.1.jar");

            CtClass a$4 = a$4(classPool);
            CtClass a$8 = a$8(classPool);

            a$4.writeFile("QiNiuH2/output");
            a$8.writeFile("QiNiuH2/output");


            File jarFile = new File("QiNiuH2/libs/pldroid-player-2.1.1_new.jar");
            File sourceDir = new File("QiNiuH2/output");
            JARDecompressionTool.compress(sourceDir, jarFile);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static CtClass a$4(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMClient = classPool.get("com.pili.pldroid.player.widget.a$4");
        CtClass[] params = new CtClass[]{
                CtClass.intType,
                CtClass.intType
        };
        CtMethod loadLibrary = eMClient.getDeclaredMethod("onVideoSizeChanged", params);

        loadLibrary.insertBefore("if (com.pili.pldroid.player.widget.a.a(this.a) == null)  return ;");
        return eMClient;
    }


    public static CtClass a$8(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMClient = classPool.get("com.pili.pldroid.player.widget.a$8");
        CtMethod loadLibrary = eMClient.getDeclaredMethod("onCompletion");

        loadLibrary.insertBefore("if (com.pili.pldroid.player.widget.a.a(this.a) == null)  return ;");
        return eMClient;
    }

}
