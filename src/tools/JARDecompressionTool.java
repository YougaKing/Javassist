package tools;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class JARDecompressionTool {


    private static String mDestJarName;

//    public static void main(String[] args) {
//        File file = new File("libs/hyphenatechat_3.3.7.jar");
//        decompress(file, "output");
//
//
//        File jarFile = new File("libs/hyphenatechat_3.3.7_new.jar");
//        File sourceDir = new File("output");
//
//        compress(sourceDir, jarFile);
//    }

    public static void decompress(File file, String outputDir) {
        JarFile jarFile;
        try {
            jarFile = new JarFile(file);
            for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements(); ) {
                JarEntry je = e.nextElement();
                File f = new File(outputDir, je.getName());
                if (je.isDirectory()) {
                    if (!f.exists()) {
                        f.mkdirs();
                    }
                } else {
                    File pf = f.getParentFile();
                    if (!pf.exists()) {
                        pf.mkdirs();
                    }
                    InputStream in = jarFile.getInputStream(je);
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                    byte[] buffer = new byte[2048];
                    int nBytes = 0;
                    while ((nBytes = in.read(buffer)) > 0) {
                        out.write(buffer, 0, nBytes);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void compress(File sourceDir, File jarFile) {
        JarOutputStream jarStream = null;
        FileOutputStream outputStream = null;
        try {
            mDestJarName = jarFile.getCanonicalPath();
            outputStream = new FileOutputStream(jarFile);
            jarStream = new JarOutputStream(outputStream);
            compressSource(sourceDir, jarStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (jarStream != null) jarStream.close();
                if (outputStream != null) outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void compressSource(File sourceDir, JarOutputStream jarStream, String path) throws IOException {
        if (sourceDir.isDirectory()) {
            System.out.println("JarDir:" + sourceDir.getPath());
            String[] dirList = sourceDir.list();
            String subPath = (path == null) ? "" : (path + sourceDir.getName() + '/');
            if (path != null) {
                JarEntry je = new JarEntry(subPath);
                je.setTime(sourceDir.lastModified());
                jarStream.putNextEntry(je);
                jarStream.flush();
                jarStream.closeEntry();
            }
            for (int i = 0; i < dirList.length; i++) {
                File f = new File(sourceDir, dirList[i]);
                compressSource(f, jarStream, subPath);
            }
        } else {
            if (sourceDir.getCanonicalPath().equals(mDestJarName)) {
                return;
            }
            System.out.println("JarEntry:" + sourceDir.getPath());
            FileInputStream fis = new FileInputStream(sourceDir);
            JarEntry entry = new JarEntry(path + sourceDir.getName());
            entry.setTime(sourceDir.lastModified());
            jarStream.putNextEntry(entry);
            int count;
            byte[] buffer = new byte[2156];
            while ((count = fis.read(buffer)) != -1) {
                jarStream.write(buffer, 0, count);
            }
            jarStream.flush();
            jarStream.closeEntry();
        }
    }
}
