package com.mobile.lab.receiver;

/**
 * Demonstrates Android BroadcastReceiver for network connectivity state changes.
 */
public class NetworkStatusReceiver {
    private static boolean isConnected = true;

    public static boolean isNetworkConnected() {
        return isConnected;
    }

    public static void setNetworkConnected(boolean connected) {
        isConnected = connected;
        System.out.println("[Android BroadcastReceiver] Network status changed: " + (connected ? "CONNECTED" : "DISCONNECTED"));
    }
}
