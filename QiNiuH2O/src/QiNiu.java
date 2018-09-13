import javassist.*;

public class QiNiu {


    public static CtClass jkLibLoader(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass jkMediaPlayer = classPool.get("tv.danmaku.ijk.media.player.IjkMediaPlayer$1");

        CtClass[] params = new CtClass[]{
                classPool.get(String.class.getName())
        };
        CtMethod loadLibrary = jkMediaPlayer.getDeclaredMethod("loadLibrary", params);

        loadLibrary.setBody("        try {\n" +
                "            System.loadLibrary($1);\n" +
                "        } catch (Throwable t) {\n" +
                "            t.printStackTrace();\n" +
                "        }");

        return jkMediaPlayer;
    }


    public static CtClass sharedLibraryNameHelper(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass sharedLibraryNameHelper = classPool.get("com.pili.pldroid.player.SharedLibraryNameHelper");

        CtMethod a = sharedLibraryNameHelper.getDeclaredMethod("a");

        a.setBody("       try{\n" +
                "           if ($0.a.contains(\"/\")) {\n" +
                "               System.load(this.a);\n" +
                "           } else {\n" +
                "               System.loadLibrary(this.a);\n" +
                "           }\n" +
                "       }catch (Throwable t){\n" +
                "           t.printStackTrace();\n" +
                "       }");

        return sharedLibraryNameHelper;
    }

//
//    public void a() {
//       try{
//           if ($0.a.contains("/")) {
//               System.load(this.a);
//           } else {
//               System.loadLibrary(this.a);
//           }
//       }catch (Throwable t){
//           t.printStackTrace();
//       }
//    }


//
//
//    public void loadLibrary(String var1) throws UnsatisfiedLinkError, SecurityException {
//        try {
//            System.loadLibrary(var1);
//        } catch (Throwable t) {
//            t.printStackTrace();
//        }
//    }
}
