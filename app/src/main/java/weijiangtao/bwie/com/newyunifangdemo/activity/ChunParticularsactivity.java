package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.SortedList;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.youth.banner.Banner;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



import java.io.IOException;


import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.BeanXiang;
import weijiangtao.bwie.com.newyunifangdemo.bean.IdBean;
import weijiangtao.bwie.com.newyunifangdemo.bean.TianBean;
import weijiangtao.bwie.com.newyunifangdemo.beaner.Bannner;
import com.squareup.okhttp.FormEncodingBuilder;

/**
 * Created by asus on 2017/4/18.
 * <p>
 * 未江涛
 */

public class ChunParticularsactivity extends AppCompatActivity implements Serializable{

   
    private Banner banner;
    private List<String>list=new ArrayList<>();
    private TextView xiang_text1;
    private TextView xiang_text2_jiage;
    private String name;
    private String id;
    private Button but_gouwu;
    private AlertDialog aler;
    private String goods_img;
    private double shop_price;
    private String goods_name;
    private PopupWindow popWindow;
    private int width;
    private int deid;
    private String shuju;
    private Button but_goumai;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.particulars_activity);

        SharedPreferences sha = getSharedPreferences("si", MODE_PRIVATE);
        deid = sha.getInt("dengid", -1);
        
        Log.i("ghgh","deid");
        banner = (Banner) findViewById(R.id.banner);
        xiang_text1 = (TextView) findViewById(R.id.xiang_text1);
        xiang_text2_jiage = (TextView) findViewById(R.id.xiang_text2_jiage);
        but_goumai = (Button) findViewById(R.id.but_goumai);
        
        but_gouwu = (Button) findViewById(R.id.but_gouwu);
        //设置图片加载器
        banner.setImageLoader(new Bannner());
        //设置图片集合
        initdata1();
        initdata();

        but_gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuptWindow();
            }
        });

        but_goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        
        
        
    }

    private void initPopuptWindow() {
        
        View view=View.inflate(this,R.layout.pupuwindow,null);
        ImageView pupuwindow_image = (ImageView) view.findViewById(R.id.pupuwindow_image);
        TextView pupuwindow_text = (TextView) view.findViewById(R.id.pupuwindow_text);
        popWindow = new PopupWindow(view, width,400 );
        Button pupuwindow_but = (Button) view.findViewById(R.id.pupuwindow_but);
        ImageLoader.getInstance().displayImage(goods_img,pupuwindow_image);
        pupuwindow_text.setText("￥"+shop_price+"");

          pupuwindow_but.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  
                                                  if(deid==-1){
                                                      Toast.makeText(ChunParticularsactivity.this,"请登录",Toast.LENGTH_SHORT).show();
                                                      Intent intent=new Intent(ChunParticularsactivity.this,Loginactivity.class);
                                                      startActivity(intent);
                                                  }else 
                                                      initdatas();
                                                  popWindow.dismiss();
                                              }
                                          }
        );
        width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        //实例化PupopWindow,参数中直接为其设置了view以及它的宽和高
       
                //设置PupopWindow捕获焦点
                 popWindow.setFocusable(true);
                popWindow.setTouchable(true);//设置popWindow可点击
                //为popWindow设置背景
                 ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFF00"));
               popWindow.setBackgroundDrawable(cd);
                 //将popWindwo显示出来
                popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initdatas() {
        
        Log.i("dwwwwwww",deid+"");
        
        OkHttpClient okh=new OkHttpClient();
        FormEncodingBuilder mfor=new FormEncodingBuilder();
        mfor.add("productID",id);
        mfor.add("count","1");
        mfor.add("price",""+shop_price);
        mfor.add("name",goods_name);
        mfor.add("userID",""+deid);
        mfor.add("pic",goods_img)
        .build();
        Request mRequest=new Request.Builder()
                .url("http://169.254.217.5:8080/bullking1/cart")
                .post(mfor.build())
                .build();
        Call call = okh.newCall(mRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                shuju = response.body().string();
                Log.i("ggggggggggg",shuju+"");
                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      Gson gson=new Gson();
                                     TianBean tianBean = gson.fromJson(shuju, TianBean.class);
                                      String dataStr = tianBean.getDataStr();
                                      if(dataStr.equals("succeed")){
                                          Toast.makeText(ChunParticularsactivity.this,"添加购物车成功",Toast.LENGTH_SHORT).show();
                                     }else {
                                          Toast.makeText(ChunParticularsactivity.this,"添加购物车失败",Toast.LENGTH_SHORT).show();
                                     }

                                  }
                              }
                );
                
            }
        });
    }
    private void initdata1() {
        Bundle bundle =getIntent().getExtras();
        id = bundle.getString("id");
        Log.i("ssss", id);
        
        
    }

    private void initdata() {
 
        OkHttpClient okhttp = new OkHttpClient();
        Request.Builder requestBuilder = new Request
                .Builder()
                .url("http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+id);
        requestBuilder.method("GET", null);
        Request build = requestBuilder.build();
        Call call = okhttp.newCall(build);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }
            @Override
            public void onResponse(Response response) throws IOException {
                
                name = response.body().string();

                Log.i("ffffff", name);
                runOnUiThread(new Runnable() {


                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        BeanXiang beanXiang = gson.fromJson(name, BeanXiang.class);
                        BeanXiang.DataBean.GoodsBean goods = beanXiang.getData().getGoods();
                        goods_img = goods.getGoods_img();
                        goods_name = goods.getGoods_name();
                        shop_price = goods.getShop_price();

                        
                        //获取轮播图片并且添加到集合里面
                        List<BeanXiang.DataBean.GoodsBean.GalleryBean> gallery = goods.getGallery();
                        for (int i = 0; i < gallery.size(); i++) {
                            String normal_url = gallery.get(i).getNormal_url();
                            list.add(normal_url);
                        }


                       banner.setImages(list);
                        //banner设置方法全部调用完毕时最后调用
                       banner.start();

                        //给textview赋值
                        xiang_text1.setText(goods_name);
                        xiang_text2_jiage.setText("￥" + shop_price + "");
                    }

                });
            }
        });
       
    }

}
