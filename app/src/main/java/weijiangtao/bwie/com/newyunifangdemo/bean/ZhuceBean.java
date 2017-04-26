package weijiangtao.bwie.com.newyunifangdemo.bean;

/**
 * Created by asus on 2017/4/21.
 * <p>
 * 未江涛
 */

public class ZhuceBean {
    
    private String dataStr;
    private int id;

    public ZhuceBean(String dataStr, int id) {
        this.dataStr = dataStr;
        this.id = id;
    }

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ZhuceBean{" +
                "dataStr='" + dataStr + '\'' +
                ", id=" + id +
                '}';
    }
}
