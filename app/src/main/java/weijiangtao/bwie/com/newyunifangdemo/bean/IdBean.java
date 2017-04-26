package weijiangtao.bwie.com.newyunifangdemo.bean;

/**
 * Created by asus on 2017/4/21.
 * <p>
 * 未江涛
 */

public class IdBean {
    private int id;
    
    

    public IdBean(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdBean{" +
                "id=" + id +
                '}';
    }
}
