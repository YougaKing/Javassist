import javassist.*;
import tools.JARDecompressionTool;

import java.io.File;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        File file = new File("FacebookAudience/libs/AudienceNetwork.jar");
        JARDecompressionTool.decompress(file, "FacebookAudience/output");

        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath("FacebookAudience/libs/AudienceNetwork.jar");

            CtClass q = q(classPool);

            q.writeFile("FacebookAudience/output");


            File jarFile = new File("FacebookAudience/libs/AudienceNetwork_new.jar");
            File sourceDir = new File("FacebookAudience/output");
            JARDecompressionTool.compress(sourceDir, jarFile);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static CtClass q(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMClient = classPool.get("com.facebook.ads.internal.util.q$1");
//        CtClass[] params = new CtClass[]{
//                CtClass.intType,
//                classPool.get(String.class.getName()),
//        };
        CtMethod loadLibrary = eMClient.getDeclaredMethod("checkServerTrusted");

        loadLibrary.setBody("            try {\n" +
                "                $1[0].checkValidity();\n" +
                "            } catch (Exception e) {\n" +
                "                throw new java.security.cert.CertificateException(\"Certificate not valid or trusted.\");\n" +
                "            }");
        return eMClient;
    }


}
