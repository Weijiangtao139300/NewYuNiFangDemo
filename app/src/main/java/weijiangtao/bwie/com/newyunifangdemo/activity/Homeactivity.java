package weijiangtao.bwie.com.newyunifangdemo.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.fragment.Classifyfragment;
import weijiangtao.bwie.com.newyunifangdemo.fragment.Homefragment;
import weijiangtao.bwie.com.newyunifangdemo.fragment.Myfragment;
import weijiangtao.bwie.com.newyunifangdemo.fragment.Shoppingfragment;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Homeactivity extends AppCompatActivity implements View.OnClickListener{

   
   
    int[] image_red = {R.mipmap.bottom_tab_home_selected, R.mipmap.bottom_tab_classify_selected,
            R.mipmap.bottom_tab_shopping_selected, R.mipmap.bottom_tab_user_selected,
    };
    int[] image_w = {R.mipmap.bottom_tab_home_normal, R.mipmap.bottom_tab_classify_normal,
            R.mipmap.bottom_tab_shopping_normal, R.mipmap.bottom_tab_user_normal
    };
    private LinearLayout linear_home;
    private LinearLayout linear_fenlei;
    private LinearLayout linear_shopping;
    private LinearLayout linear_my;
    private TextView textv_home;
    private TextView textv_home1;
    private TextView textv_home2;
    private TextView textv_home3;
    private ImageView image_home;
    private ImageView image_home1;
    private ImageView image_home2;
    private ImageView image_home3;
    private List<TextView> list_text;
    private List<ImageView> list_image;
    private Homefragment home;
    private Classifyfragment classif;
    private Shoppingfragment shop;
    private Myfragment my;
    private int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initview();


     
       

        initdata(0);
        getSupportFragmentManager().beginTransaction().replace(R.id.fraglayout,home).commit();

    }

    private void initdata(int num) {

        for (int i = 0; i <list_image.size() ; i++) {
            if(num==i){
                list_image.get(i).setImageResource(image_red[i]);
                list_text.get(i).setTextColor(Color.RED);
            }else {
                list_image.get(i).setImageResource(image_w[i]);
                list_text.get(i).setTextColor(Color.BLACK);
            }
        }
      


        }

    private void initview() {
        linear_home = (LinearLayout) findViewById(R.id.linear_home);
        linear_fenlei = (LinearLayout) findViewById(R.id.linear_fenlei);
        linear_shopping = (LinearLayout) findViewById(R.id.linear_shopping);
        linear_my = (LinearLayout) findViewById(R.id.linear_my);

        linear_home.setOnClickListener(this);
        linear_fenlei.setOnClickListener(this);
        linear_shopping.setOnClickListener(this);
        linear_my.setOnClickListener(this);

        textv_home = (TextView) findViewById(R.id.textv_home);
        textv_home1 = (TextView) findViewById(R.id.textv_home1);
        textv_home2 = (TextView) findViewById(R.id.textv_home2);
        textv_home3 = (TextView) findViewById(R.id.textv_home3);


        image_home = (ImageView) findViewById(R.id.image_home);
        image_home1 = (ImageView) findViewById(R.id.image_home1);
        image_home2 = (ImageView) findViewById(R.id.image_home2);
        image_home3 = (ImageView) findViewById(R.id.image_home3);

        list_text = new ArrayList<>();
        list_text.add(textv_home);
        list_text.add(textv_home1);
        list_text.add(textv_home2);
        list_text.add(textv_home3);


        list_image = new ArrayList<>();
        list_image.add(image_home);
        list_image.add(image_home1);
        list_image.add(image_home2);
        list_image.add(image_home3);


        home = new Homefragment();
        classif = new Classifyfragment();
        shop = new Shoppingfragment();
        my = new Myfragment();



       

    }
                       


    @Override
    public void onClick(View v) {
        






        switch (v.getId()){
           case R.id.linear_home:
               initdata(0);



              
               
               getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                       
                       .replace(R.id.fraglayout,home).commit();
               break;

           case R.id.linear_fenlei:
               initdata(1);
               getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                       .replace(R.id.fraglayout,classif).commit();
               break;

           case R.id.linear_shopping:
               initdata(2);
               getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                       .replace(R.id.fraglayout,shop).commit();
               break;

           case R.id.linear_my:
               initdata(3);
               getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                       .replace(R.id.fraglayout,my).commit();
               break;
       }

       



   
}

}