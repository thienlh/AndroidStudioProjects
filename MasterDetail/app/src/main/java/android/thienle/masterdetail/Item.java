package android.thienle.masterdetail;

/**
 * Created by thienlh on 1/18/2016.
 */
public class Item {
    private String version;
    private String info;

    public Item() {
    }

    public Item(String version, String info) {
        this.version = version;
        this.info = info;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
