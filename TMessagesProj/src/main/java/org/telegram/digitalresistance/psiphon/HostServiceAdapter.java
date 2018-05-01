package org.telegram.digitalresistance.psiphon;

import android.content.Context;

import java.util.List;

import ca.psiphon.PsiphonTunnel;

public abstract class HostServiceAdapter implements PsiphonTunnel.HostService {

    @Override
    public String getAppName() {
        return "";
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public Object getVpnService() {
        return null;
    }

    @Override
    public Object newVpnServiceBuilder() {
        return null;
    }

    @Override
    public String getPsiphonConfig() {
        return "";
    }

    @Override
    public void onDiagnosticMessage(String s) {

    }

    @Override
    public void onAvailableEgressRegions(List<String> list) {

    }

    @Override
    public void onSocksProxyPortInUse(int i) {

    }

    @Override
    public void onHttpProxyPortInUse(int i) {

    }

    @Override
    public void onListeningSocksProxyPort(int i) {

    }

    @Override
    public void onListeningHttpProxyPort(int i) {

    }

    @Override
    public void onUpstreamProxyError(String s) {

    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void onConnected() {

    }

    @Override
    public void onHomepage(String s) {

    }

    @Override
    public void onClientRegion(String s) {

    }

    @Override
    public void onClientUpgradeDownloaded(String s) {

    }

    @Override
    public void onClientIsLatestVersion() {

    }

    @Override
    public void onSplitTunnelRegion(String s) {

    }

    @Override
    public void onUntunneledAddress(String s) {

    }

    @Override
    public void onBytesTransferred(long l, long l1) {

    }

    @Override
    public void onStartedWaitingForNetworkConnectivity() {

    }

    @Override
    public void onClientVerificationRequired(String s, int i, boolean b) {

    }

    @Override
    public void onActiveAuthorizationIDs(List<String> list) {

    }

    @Override
    public void onExiting() {

    }
}
