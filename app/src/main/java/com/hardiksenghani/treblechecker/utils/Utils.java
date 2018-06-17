package com.hardiksenghani.treblechecker.utils;

import android.os.Build;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

    private static final String TREBLE_COMMAND = "getprop ro.treble.enabled";
    private static final String AB_UPDATE_COMMAND = "getprop ro.build.ab_update";

    private static boolean isTrebleRomCheck = false;
    private static boolean isSeamlessUpdateCheck = false;

    private static boolean isTrebleRom;
    private static boolean isSeamlessUpdate;

    public static boolean isTrebleRom() {
        if (!isTrebleRomCheck) {
            isTrebleRomCheck = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String result = shellCommandExecute(TREBLE_COMMAND);
                isTrebleRom = parseBooleanResult(result);
            } else {
                isTrebleRom = false;
            }
        }
        return isTrebleRom;
    }

    public static boolean isSeamlessUpdatesSupported() {
        if (!isSeamlessUpdateCheck) {
            isSeamlessUpdateCheck = true;
            String result = shellCommandExecute(AB_UPDATE_COMMAND);
            isSeamlessUpdate = parseBooleanResult(result);
        }
        return isSeamlessUpdate;
    }

    @NonNull
    public static String shellCommandExecute(String command) {

        StringBuffer output = new StringBuffer();
        try {

            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            BufferedReader bufferedReader
                    = new BufferedReader(new InputStreamReader(process.getInputStream()));

            int data = -1;
            while ((data = bufferedReader.read()) != -1) {
                output.append((char)data);
            }

            int outputLength = output.length();

            output.deleteCharAt(outputLength-1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    private static boolean parseBooleanResult(String value) {
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception e) {
            return false;
        }
    }

}
