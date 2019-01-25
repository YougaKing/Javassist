package com.youga;

import javassist.*;
import tools.JARDecompressionTool;

import java.io.File;
import java.io.IOException;

public class Youga {


    public static void main(String[] args) {

        File file = new File("EaseMob/libs/hyphenatechat_3.3.7.jar");
        JARDecompressionTool.decompress(file, "EaseMob/output");

        ClassPool classPool = ClassPool.getDefault();
        try {
            classPool.insertClassPath("EaseMob/libs/hyphenatechat_3.3.7.jar");

            CtClass eMClient = EaseMob.eMClientLoadLibrary(classPool);
            CtClass runnable = EaseMob.eMChatService$1Run(classPool);
            CtClass eMAConnectionListener = EaseMob.eMAConnectionListener(classPool);
            CtClass eMAMultiDeviceListener = EaseMob.eMAMultiDeviceListener(classPool);
            CtClass eMAChatClient = EaseMob.eMAChatClient(classPool);
            CtClass eMAChatManager = EaseMob.eMAChatManager(classPool);
            CtClass eMAConversation = EaseMob.eMAConversation(classPool);
            CtClass eMMonitor = EaseMob.eMMonitor(classPool);
            CtClass eMAError = EaseMob.eMAError(classPool);
            CtClass eMAChatConfig = EaseMob.eMAChatConfig(classPool);
            CtClass eMAGroupManager = EaseMob.eMAGroupManager(classPool);
            CtClass netUtils = EaseMob.netUtils(classPool);
            CtClass eMPushHelper = EaseMob.eMPushHelper(classPool);
            CtClass eMSessionManager = EaseMob.eMSessionManager(classPool);
            CtClass eMMonitorReceiver = EaseMob.eMMonitorReceiver(classPool);

            eMClient.writeFile("EaseMob/output");
            runnable.writeFile("EaseMob/output");
            eMAConnectionListener.writeFile("EaseMob/output");
            eMAMultiDeviceListener.writeFile("EaseMob/output");
            eMAChatClient.writeFile("EaseMob/output");
            eMAChatManager.writeFile("EaseMob/output");
            eMAConversation.writeFile("EaseMob/output");
            eMMonitor.writeFile("EaseMob/output");
            eMAError.writeFile("EaseMob/output");
            eMAChatConfig.writeFile("EaseMob/output");
            eMAGroupManager.writeFile("EaseMob/output");
            netUtils.writeFile("EaseMob/output");
            eMPushHelper.writeFile("EaseMob/output");
            eMSessionManager.writeFile("EaseMob/output");
            eMMonitorReceiver.writeFile("EaseMob/output");

            File jarFile = new File("EaseMob/libs/hyphenatechat_3.3.7_new.jar");
            File sourceDir = new File("EaseMob/output");
            JARDecompressionTool.compress(sourceDir, jarFile);

        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }
    }


}
