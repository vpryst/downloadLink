package org.vpryst.downloadLink;

public interface ConstVariables {
    public final String PROPERTY_LINK = FilePropertyManager.getPropertyString("WriteFile.link");
    public final String CONNECTION_PROXY_KEY = "http.proxyHost";
    public final String CONNECTION_PORT_KEY = "http.proxyPort";
    public final String CONNECTION_PROXY = FilePropertyManager.getPropertyString(CONNECTION_PROXY_KEY);
    public final String CONNECTION_PORT = FilePropertyManager.getPropertyString(CONNECTION_PORT_KEY);
}
