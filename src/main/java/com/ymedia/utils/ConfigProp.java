package com.ymedia.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigProp {
    private static Properties prop = null;
    private static String propFile = "config.properties";
    private static boolean isInitialized = false;

    public ConfigProp() {
    }

    public static Properties getProp() {
        return prop != null ? prop : (prop = new Properties());
    }

    private static String getPropertyFileName() {
        if (System.getProperty("baseDir") == null || System.getProperty("baseDir").trim().equals("")) {
            System.setProperty("baseDir", (new File("")).getAbsolutePath());
        }

        String baseDir = System.getProperty("baseDir");
        return baseDir + "/config/" + propFile;
    }

    public static synchronized void init() {
        if (!isInitialized) {
            try {
                File configFile = new File(getPropertyFileName());
                FileInputStream configStream = new FileInputStream(configFile);
                getProp().load(configStream);
                configStream.close();
                isInitialized = true;
            } catch (Exception var2) {
//                Log.log(var2);
            }

        }
    }

    public static String getProperty(String name) {
        init();
        return prop.getProperty(name);
    }

    public static String getStringProperty(String name) {
        String result = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(getPropertyFileName()))));
            String line = null;

            while((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.indexOf("#") != 0 && !line.equals("")) {
                    int index = line.indexOf("=");
                    if (index > 0) {
                        String temp = line.substring(0, index);
                        if (temp.trim().equalsIgnoreCase("comm.server.name")) {
                            result = line.substring(index + 1, line.length());
                            break;
                        }
                    }
                }
            }

            reader.close();
        } catch (Exception var6) {
            ;
        }

        return result;
    }
}


