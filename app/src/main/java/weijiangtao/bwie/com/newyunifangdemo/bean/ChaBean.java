package weijiangtao.bwie.com.newyunifangdemo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by asus on 2017/4/23.
 * <p>
 * 未江涛
 */

public class ChaBean implements Serializable{


    /**
     * cartItemList : [{"colorID":0,"count":1,"id":185,"name":"多彩水润亮颜蚕丝面膜套装21片","pic":"http://image.hmeili.com/yunifang/images/goods/428/goods_img/160819094034113421009937866.jpg","price":79.9,"productID":428,"repertory":899,"sizeID":0,"userID":51}]
     * response : cart
     */

    private String response;
    private List<CartItemListBean> cartItemList;
    

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<CartItemListBean> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItemListBean> cartItemList) {
        this.cartItemList = cartItemList;
    }

 

    public static class CartItemListBean implements Serializable{
        /**
         * colorID : 0
         * count : 1
         * id : 185
         * name : 多彩水润亮颜蚕丝面膜套装21片
         * pic : http://image.hmeili.com/yunifang/images/goods/428/goods_img/160819094034113421009937866.jpg
         * price : 79.9
         * productID : 428
         * repertory : 899
         * sizeID : 0
         * userID : 51
         */

        private int colorID;
        private int count;
        private int id;
        private String name;
        private String pic;
        private double price;
        private int productID;
        private int repertory;
        private int sizeID;
        private int userID;
        private boolean chekebox;


        public boolean isChekebox() {
            return chekebox;
        }

        public void setChekebox(boolean chekebox) {
            this.chekebox = chekebox;
        }

        public int getColorID() {
            return colorID;
        }

        public void setColorID(int colorID) {
            this.colorID = colorID;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public int getRepertory() {
            return repertory;
        }

        public void setRepertory(int repertory) {
            this.repertory = repertory;
        }

        public int getSizeID() {
            return sizeID;
        }

        public void setSizeID(int sizeID) {
            this.sizeID = sizeID;
        }

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }
    }
}
