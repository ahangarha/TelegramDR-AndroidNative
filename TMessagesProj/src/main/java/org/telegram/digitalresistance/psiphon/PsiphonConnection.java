package org.telegram.digitalresistance.psiphon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.digitalresistance.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicInteger;

import ca.psiphon.PsiphonTunnel;

public class PsiphonConnection extends HostServiceAdapter {

    private final Context context;
    private final PsiphonTunnel psiphonTunnel;
    private final AtomicInteger socksPort = new AtomicInteger();
    private ConnectionListener listener;

    public PsiphonConnection(Context context) {
        this.context = context.getApplicationContext();
        this.psiphonTunnel = PsiphonTunnel.newPsiphonTunnel(this);
    }

    public void connect(@NonNull ConnectionListener listener) {
        this.listener = listener;
        try {
            psiphonTunnel.startTunneling("");
        } catch (PsiphonTunnel.Exception e) {
            listener.onPsiphonError(e);
        }
    }

    public void close() {
        psiphonTunnel.stop();
    }

    @Override
    public void onBytesTransferred(long sent, long received) {
        Log.d("Psiphon", "Bytes sent: " + sent);
        Log.d("Psiphon", "Bytes received: " + received);
    }

    @Override
    public String getAppName() {
        return this.context.getString(R.string.AppName);
    }

    @Override
    public Context getContext() {
        return this.context;
    }

    @Override
    public String getPsiphonConfig() {
        try {
            //Psiphon config file should only be stored locally. Should be added to /res/raw dir if it's missing.
            return new JSONObject(readInputStreamToString(context.getResources().openRawResource(R.raw.psiphon_config))).toString();
        } catch (IOException | JSONException e) {
            listener.onPsiphonError(e);
            return "";
        }
    }

    @Override
    public void onListeningSocksProxyPort(int newPort) {
        this.socksPort.set(newPort);
    }

    private static String readInputStreamToString(InputStream inputStream) throws IOException {
        return new String(readInputStreamToBytes(inputStream), "UTF-8");
    }

    private static byte[] readInputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int readCount;
        byte[] buffer = new byte[16384];
        while ((readCount = inputStream.read(buffer, 0, buffer.length)) != -1) {
            outputStream.write(buffer, 0, readCount);
        }
        outputStream.flush();
        inputStream.close();
        return outputStream.toByteArray();
    }

    @Override
    public void onConnected() {
        listener.onPsiphonConnected(this.socksPort.get());
    }

    public interface ConnectionListener {
        void onPsiphonConnected(int port);

        void onPsiphonError(Exception error);
    }
}

