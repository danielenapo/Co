package it.unimore.fum.iot.model;

/**
 * @author Marco Picone, Ph.D. - picone.m@gmail.com
 * @project laboratory-http-api
 * @created 12/10/2021 - 21:06
 */
public class DeviceDescriptor {

    private String uuid;
    private String protocol;
    private String ip;
    private int port;
    private String image; //in base 64
    private String displayName;

    public DeviceDescriptor() {
    }

    public DeviceDescriptor(String uuid, String protocol, String ip, int port, String image, String displayName) {
        this.uuid = uuid;
        this.protocol = protocol;
        this.ip = ip;
        this.port = port;
        this.image = image;
        this.displayName=displayName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "DeviceDescriptor{" +
                "uuid='" + uuid + '\'' +
                ", protocol='" + protocol + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", image='" + image + '\'' +
                ", title='" + displayName + '\'' +
                '}';
    }
}
