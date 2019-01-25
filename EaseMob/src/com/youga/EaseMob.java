package com.youga;


import android.content.Context;
import android.content.Intent;
import javassist.*;
import javassist.bytecode.ExceptionTable;


public class EaseMob {


    public static CtClass eMClientLoadLibrary(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMClient = classPool.get("com.hyphenate.chat.EMClient");
        CtMethod loadLibrary = eMClient.getDeclaredMethod("loadLibrary");
        loadLibrary.setBody("        if (!libraryLoaded) {\n" +
                "            _loadLibrary(\"sqlite\");\n" +
                "            _loadLibrary(\"hyphenate_av\");\n" +
                "            _loadLibrary(\"hyphenate_av_recorder\");\n" +
                "            _loadLibrary(\"hyphenate\");\n" +
                "            libraryLoaded = true;\n" +
                "        }\n");
        return eMClient;
    }


    public static CtClass eMChatService$1Run(ClassPool classPool) throws NotFoundException {
        CtClass runnable = classPool.get("com.hyphenate.chat.EMChatService$1");
        CtMethod run = runnable.getDeclaredMethod("run");
        ExceptionTable exceptionTable = run.getMethodInfo().getCodeAttribute().getExceptionTable();
        exceptionTable.setCatchType(0, 0);
        return runnable;
    }


    public static CtClass eMAConnectionListener(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAConnectionListener = classPool.get("com.hyphenate.chat.adapter.EMAConnectionListener");
        CtConstructor constructor = eMAConnectionListener.getDeclaredConstructor(null);
        constructor.insertBeforeBody("        try {\n" +
                "            this.nativeInit();\n" +
                "        } catch (Throwable var2) {\n" +
                "            var2.printStackTrace();\n" +
                "        }\n" +
                "        return;");
        return eMAConnectionListener;
    }

    public static CtClass eMAMultiDeviceListener(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAMultiDeviceListener = classPool.get("com.hyphenate.chat.adapter.EMAMultiDeviceListener");
        CtConstructor constructor = eMAMultiDeviceListener.getDeclaredConstructor(null);
        constructor.insertBeforeBody("        try {\n" +
                "            this.nativeInit();\n" +
                "        } catch (Throwable var2) {\n" +
                "            var2.printStackTrace();\n" +
                "        }\n" +
                "        return;");
        return eMAMultiDeviceListener;
    }

    public static CtClass eMAChatClient(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAChatClient = classPool.get("com.hyphenate.chat.adapter.EMAChatClient");
        CtMethod login = eMAChatClient.getDeclaredMethod("login");
        login.setBody("        try {\n" +
                "            this.native_login($1, $2, $3, $4, $5);\n" +
                "        } catch (Throwable e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n");

        CtMethod setPresence = eMAChatClient.getDeclaredMethod("setPresence");
        setPresence.setBody("        try {\n" +
                "            this.native_setPresence($1);\n" +
                "        } catch (Throwable e) {\n" +
                "            e.printStackTrace();\n" +
                "        }");

        CtMethod isConnected = eMAChatClient.getDeclaredMethod("isConnected");
        isConnected.setBody("        try {\n" +
                "            return this.native_isConnected();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }");

        CtMethod onNetworkChanged = eMAChatClient.getDeclaredMethod("onNetworkChanged");
        onNetworkChanged.setBody("        try{\n" +
                "            this.native_onNetworkChanged($1.ordinal());\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "        }");
        return eMAChatClient;
    }

    public static CtClass eMAChatManager(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAChatManager = classPool.get("com.hyphenate.chat.adapter.EMAChatManager");
        CtMethod loadAllConversationsFromDB = eMAChatManager.getDeclaredMethod("loadAllConversationsFromDB");
        loadAllConversationsFromDB.setBody("        try{\n" +
                "            return this.nativeLoadAllConversationsFromDB();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return null;\n" +
                "        }");
        return eMAChatManager;
    }

    public static CtClass eMAConversation(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAConversation = classPool.get("com.hyphenate.chat.adapter.EMAConversation");
        CtConstructor constructor = eMAConversation.getDeclaredConstructor(null);
        constructor.insertBeforeBody("        try {\n" +
                "            this.nativeInit();\n" +
                "        } catch (Throwable var2) {\n" +
                "            var2.printStackTrace();\n" +
                "        }\n" +
                "        return;");
        return eMAConversation;
    }

    public static CtClass eMMonitor(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMMonitor = classPool.get("com.hyphenate.chat.EMMonitor");
        CtMethod start = eMMonitor.getDeclaredMethod("start");
        start.setBody("        try {\n" +
                "            if (!this.nativeServiceStarted) {\n" +
                "                this.startMonitor($2);\n" +
                "                this.nativeServiceStarted = true;\n" +
                "            }\n" +
                "        } catch (Throwable e) {\n" +
                "            e.printStackTrace();\n" +
                "        }");
        return eMMonitor;
    }

    public static CtClass eMAError(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAError = classPool.get("com.hyphenate.chat.adapter.EMAError");
        CtConstructor constructor = eMAError.getDeclaredConstructor(null);
        constructor.insertBeforeBody("        try {\n" +
                "            this.nativeInit();\n" +
                "        } catch (Throwable var2) {\n" +
                "            var2.printStackTrace();\n" +
                "        }\n" +
                "        return;");

        CtMethod errCode = eMAError.getDeclaredMethod("errCode");
        errCode.setBody("        try {\n" +
                "            return this.nativeErrCode();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return 0;\n" +
                "        }");

        CtMethod errMsg = eMAError.getDeclaredMethod("errMsg");
        errMsg.setBody("        try {\n" +
                "            return this.nativeErrMsg();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return \"\";\n" +
                "        }");
        return eMAError;
    }

    public static CtClass eMAChatConfig(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAChatConfig = classPool.get("com.hyphenate.chat.adapter.EMAChatConfig");
        CtClass[] params = new CtClass[]{
                CtClass.booleanType,
        };
        CtMethod getAccessToken = eMAChatConfig.getDeclaredMethod("getAccessToken", params);
        getAccessToken.setBody("        try {\n" +
                "            return this.nativegetAccessToken($1);\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return \"\";\n" +
                "        }");

        CtMethod hasHeartBeatCustomizedParams = eMAChatConfig.getDeclaredMethod("hasHeartBeatCustomizedParams");
        hasHeartBeatCustomizedParams.setBody("        try {\n" +
                "            return this.nativeHasHeartBeatCustomizedParams();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }");

        CtMethod getAppKey = eMAChatConfig.getDeclaredMethod("getAppKey");
        getAppKey.setBody("        try {\n" +
                "            return this.nativegetAppKey();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return \"\";\n" +
                "        }");

        CtMethod getBaseUrl = eMAChatConfig.getDeclaredMethod("getBaseUrl");
        getBaseUrl.setBody("        try {\n" +
                "            return this.nativegetBaseUrl();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return \"\";\n" +
                "        }");

        CtMethod isGcmEnabled = eMAChatConfig.getDeclaredMethod("isGcmEnabled");
        isGcmEnabled.setBody("        try {\n" +
                "            return this.nativeIsGcmEnabled();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }");

        CtMethod getUsingSQLCipher = eMAChatConfig.getDeclaredMethod("getUsingSQLCipher");
        getUsingSQLCipher.setBody("        try {\n" +
                "            return this.nativeGetUsingSQLCipher();\n" +
                "        }catch (Throwable e){\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }");

        CtMethod openDatabase = eMAChatConfig.getDeclaredMethod("openDatabase");
        openDatabase.setBody("        try {\n" +
                "            return this.nativeOpenDatabase($1);\n" +
                "        } catch (Throwable e) {\n" +
                "            e.printStackTrace();\n" +
                "            return false;\n" +
                "        }");
        return eMAChatConfig;
    }

    public static CtClass eMAGroupManager(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMAGroupManager = classPool.get("com.hyphenate.chat.adapter.EMAGroupManager");
        CtMethod loadAllMyGroupsFromDB = eMAGroupManager.getDeclaredMethod("loadAllMyGroupsFromDB");
        loadAllMyGroupsFromDB.setBody("            try{\n" +
                "                this.nativeLoadAllMyGroupsFromDB();\n" +
                "            }catch (Throwable e){\n" +
                "                e.printStackTrace();\n" +
                "            }");
        return eMAGroupManager;
    }

    public static CtClass netUtils(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass netUtils = classPool.get("com.hyphenate.util.NetUtils");
        CtMethod getWiFiSSID = netUtils.getDeclaredMethod("getWiFiSSID");
        getWiFiSSID.insertBefore("        if($1==null)return \"\";");
        return netUtils;
    }

    public static CtClass eMPushHelper(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMPushHelper = classPool.get("com.hyphenate.chat.EMPushHelper");
        CtMethod sendDeviceTokenToServer = eMPushHelper.getDeclaredMethod("sendDeviceTokenToServer");

        CtClass throwable = classPool.get(Throwable.class.getName());

        sendDeviceTokenToServer.addCatch("{ $e.printStackTrace(); return ; }", throwable);


        CtClass[] params = new CtClass[]{
                classPool.get(String.class.getName()),
        };
        sendDeviceTokenToServer = eMPushHelper.getDeclaredMethod("sendDeviceTokenToServer", params);

        sendDeviceTokenToServer.addCatch("{ $e.printStackTrace(); return ; }", throwable);

        params = new CtClass[]{
                classPool.get("com.hyphenate.chat.EMPushHelper$EMPushType"),
        };
        CtMethod setPushType = eMPushHelper.getDeclaredMethod("setPushType", params);

        setPushType.setBody("        if ($1 == com.hyphenate.chat.EMPushHelper$EMPushType.HUAWEIPUSH) {\n" +
                "            this.pushType = com.hyphenate.chat.EMPushHelper$EMPushType.NORMAL;\n" +
                "        } else {\n" +
                "            this.pushType = $1;\n" +
                "        }");
        return eMPushHelper;
    }

    public static CtClass eMSessionManager(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMSessionManager = classPool.get("com.hyphenate.chat.EMSessionManager");

        CtClass[] params = new CtClass[]{
                classPool.get(String.class.getName()),
        };
        CtMethod setLastLoginUser = eMSessionManager.getDeclaredMethod("setLastLoginUser", params);

        setLastLoginUser.setBody("        try {\n" +
                "            if ($1 != null) {\n" +
                "                this.currentUser = new com.hyphenate.chat.EMContact($1);\n" +
                "                this.lastLoginUser = $1;\n" +
                "                android.content.SharedPreferences preferences = android.preference.PreferenceManager.getDefaultSharedPreferences(this.appContext);\n" +
                "                android.content.SharedPreferences.Editor editor = preferences.edit();\n" +
                "                editor.putString(\"easemob.chat.loginuser\", $1);\n" +
                "                editor.commit();\n" +
                "            }\n" +
                "        } catch (Throwable t) {\n" +
                "            t.printStackTrace();\n" +
                "        }");
        return eMSessionManager;
    }

    public static CtClass eMMonitorReceiver(ClassPool classPool) throws NotFoundException, CannotCompileException {
        CtClass eMMonitorReceiver = classPool.get("com.hyphenate.chat.EMMonitorReceiver");
        CtClass[] params = new CtClass[]{
                classPool.get(Context.class.getName()),
                classPool.get(Intent.class.getName()),
        };
        CtMethod onReceive = eMMonitorReceiver.getDeclaredMethod("onReceive", params);
        onReceive.setBody("        {\n" +
                "            String action = $2.getAction();\n" +
                "            if (\"android.intent.action.PACKAGE_REMOVED\".equals(action)) {\n" +
                "                if ($2.getBooleanExtra(\"android.intent.extra.REPLACING\", false)) {\n" +
                "                    return;\n" +
                "                }\n" +
                "\n" +
                "                com.hyphenate.util.EMLog.d(\"EMMonitorReceiver\", $2.getData().getSchemeSpecificPart() + \" be removed\");\n" +
                "                com.hyphenate.chat.EMMonitor.getInstance().getMonitorDB().b($2.getData().getSchemeSpecificPart());\n" +
                "            } else {\n" +
                "                try {\n" +
                "                    $1.startService(new android.content.Intent($1, com.hyphenate.chat.EMChatService.class));\n" +
                "                } catch (Throwable t) {\n" +
                "                    t.printStackTrace();\n" +
                "                }\n" +
                "            }\n" +
                "        }");
        return eMMonitorReceiver;
    }


//    public void onReceive(Context $1, Intent $2) {
//        {
//            String action = $2.getAction();
//            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
//                if ($2.getBooleanExtra("android.intent.extra.REPLACING", false)) {
//                    return;
//                }
//
//                com.hyphenate.util.EMLog.d("EMMonitorReceiver", $2.getData().getSchemeSpecificPart() + " be removed");
//                com.hyphenate.chat.EMMonitor.getInstance().getMonitorDB().b($2.getData().getSchemeSpecificPart());
//            } else {
//                try {
//                    $1.startService(new Intent($1, com.hyphenate.chat.EMChatService.class));
//                } catch (Throwable t) {
//                    t.printStackTrace();
//                }
//            }
//        }
//    }

}
