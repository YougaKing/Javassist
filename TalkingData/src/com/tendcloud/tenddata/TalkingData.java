package com.tendcloud.tenddata;

import javassist.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Youga on 2018/8/6.
 */
public class TalkingData {
    private final ConcurrentMap b = new ConcurrentHashMap();

    public static CtClass register(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass gi = classPool.get("com.tendcloud.tenddata.gi");
        CtClass[] params = new CtClass[]{
                classPool.get(Object.class.getName()),
        };
        CtMethod register = gi.getDeclaredMethod("register", params);
//
//        CtClass throwable = classPool.get(Throwable.class.getName());
//        register.addCatch("{ $e.printStackTrace() ; throw $e; }", throwable);

        CtField b = gi.getField("b");
        System.out.println(b.toString());

//        register.instrument(new ExprEditor() {
//            public void edit(MethodCall m) throws CannotCompileException {
//                if (m.getClassName().equals("Point")
//                        && m.getMethodName().equals("move"))
//                    m.replace("{ $1 = 0; $_ = $proceed($$); }");
//            }
//        });
        register.setBody("        if ($1 != null) {\n" +
                "            java.util.Map map = com.tendcloud.tenddata.gl.a($1);\n" +
                "            java.util.Iterator iterator = map.keySet().iterator();\n" +
                "\n" +
                "            java.util.Set object;\n" +
                "            java.util.Set set = null;\n" +
                "            do {\n" +
                "                if (!iterator.hasNext()) {\n" +
                "                    return;\n" +
                "                }\n" +
                "                Class key = (Class) iterator.next();\n" +
                "                Object value = b.get(key);\n" +
                "                if (value instanceof java.util.Set) {\n" +
                "                    object = (Set) value;\n" +
                "                } else {\n" +
                "                    java.util.concurrent.CopyOnWriteArraySet arraySet = new CopyOnWriteArraySet();\n" +
                "                    value = b.putIfAbsent(key, arraySet);\n" +
                "                    if (value instanceof java.util.Set) {\n" +
                "                        object = (Set) value;\n" +
                "                    } else {\n" +
                "                        object = arraySet;\n" +
                "                    }\n" +
                "                }\n" +
                "                value = map.get(key);\n" +
                "                if (value instanceof java.util.Set) {\n" +
                "                    set = (Set) value;\n" +
                "                }\n" +
                "                if (set == null) {\n" +
                "                    return;\n" +
                "                }\n" +
                "            } while ((object).addAll(set));\n" +
                "        }");
        return gi;
    }

    public void register(Object $1) {
        if ($1 != null) {
            java.util.Map map = com.tendcloud.tenddata.gl.a($1);
            java.util.Iterator iterator = map.keySet().iterator();

            Set object;
            Set set = null;
            do {
                if (!iterator.hasNext()) {
                    return;
                }
                Class key = (Class) iterator.next();
                Object value = b.get(key);
                if (value instanceof Set) {
                    object = (Set) value;
                } else {
                    CopyOnWriteArraySet arraySet = new CopyOnWriteArraySet();
                    value = b.putIfAbsent(key, arraySet);
                    if (value instanceof Set) {
                        object = (Set) value;
                    } else {
                        object = arraySet;
                    }
                }
                value = map.get(key);
                if (value instanceof Set) {
                    set = (Set) value;
                }
                if (set == null) {
                    return;
                }
            } while ((object).addAll(set));
        }
    }
}
