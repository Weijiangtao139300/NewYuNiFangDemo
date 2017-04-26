package weijiangtao.bwie.com.newyunifangdemo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.activity.ChunParticularsactivity;
import weijiangtao.bwie.com.newyunifangdemo.adapter.MyChunJiadapter;
import weijiangtao.bwie.com.newyunifangdemo.adapter.Myadapterwei;
import weijiangtao.bwie.com.newyunifangdemo.adapter.Myadapteryou;
import weijiangtao.bwie.com.newyunifangdemo.adapter.Myhotadapter;
import weijiangtao.bwie.com.newyunifangdemo.bean.Homebean;
import weijiangtao.bwie.com.newyunifangdemo.beaner.Bannner;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Homefragment extends Fragment implements View.OnClickListener {

    private View view;
    private LinearLayout linear_qiandao;
    private LinearLayout linear_jifen;
    private LinearLayout linear_duihuan;
    private LinearLayout linear_chaxun;
    private List<Homebean.DataBean.SubjectsBean> subjects;
    private List<Homebean.DataBean.DefaultGoodsListBean> defaultGoodsList;
    private List<Homebean.DataBean.BestSellersBean> bestSellers;
    private List<Homebean.DataBean.BestSellersBean.GoodsListBeanX> goodsList;
    private RecyclerView recy_chunji;
    private RecyclerView recy_hot;
    private ImageView hot_image;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList1;
    private ListView listv_hot;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList2;
    private ListView hot_listv;
    private RecyclerView recyclerView_hot_topics;
    private RecyclerView recyclerView_hot_topics1;
    private RecyclerView recyclerView_hot_topics2;
    private RecyclerView recyclerView_hot_topics3;
    private RecyclerView recyclerView_hot_topics4;
    private RecyclerView recyclerView_hot_topics5;
    private RecyclerView recyclerView_hot_topics6;
  
    
    private ImageView image_hot1;
    private ImageView image_hot2;
    private ImageView image_hot3;
    private ImageView image_hot4;
    private ImageView image_hot5;
    private ImageView image_hot6;
    private ImageView image_hot;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList3;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList4;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList5;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList6;
    private List<Homebean.DataBean.SubjectsBean.GoodsListBean> goodsList7;
    private View view1;
    private RecyclerView viewById;
    private RecyclerView recy_youhui;
    private List<Homebean.DataBean.BestSellersBean.GoodsListBeanX> chunjilist;
    private MyChunJiadapter chun;
    private String id;
    private Banner banner_home;
    private List<String>list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        banner_home = (Banner) view.findViewById(R.id.banner_home);
        banner_home.setImageLoader(new Bannner());


        initview();
        initdata();


    }

    private void initdata() {
        OkHttpClient okhttp = new OkHttpClient();
        Request.Builder requestBuilder = new Request
                .Builder()
                .url("http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447");

        requestBuilder.method("GET", null);
        Request build = requestBuilder.build();
        Call call = okhttp.newCall(build);
        call.enqueue(new Callback() {

            private String s;

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                s = response.body().string();

                getActivity().runOnUiThread(new Runnable() {


                    @Override
                    public void run() {

                        Gson gson = new Gson();
                        Homebean homebean = gson.fromJson(s, Homebean.class);
                        Homebean.DataBean data = homebean.getData();

                        List<Homebean.DataBean.Ad1Bean> ad1 = data.getAd1();
                        for (int i = 0; i <ad1.size() ; i++) {
                            String image = ad1.get(i).getImage();
                            list.add(image);
                        }

                        banner_home.setImages(list);
                        //banner设置方法全部调用完毕时最后调用
                        banner_home.start();
                        //春季
                        bestSellers = data.getBestSellers();

                        //优惠活动
                        defaultGoodsList = data.getDefaultGoodsList();

                        //热门
                        subjects = data.getSubjects();


                        initbestSellers();
                     //   initdefaultGoodsList();

                        initsubjects();
                        initsubjects1();
                        initsubjects2();
                        initsubjects3();
                        initsubjects4();
                        initsubjects5();
                        initsubjects6();
                        
                        initwei();

                    }

                   


                });

            }
        });

    }

    private void initbestSellers() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy_chunji.setLayoutManager(linearLayoutManager);

        for (int i = 0; i < bestSellers.size(); i++) {
            chunjilist = bestSellers.get(i).getGoodsList();
            id = chunjilist.get(i).getId();


        }
        chun = new MyChunJiadapter(chunjilist, getActivity());
        recy_chunji.setAdapter(chun);
        
        //点击跳转详情页面
        chun.setOnItemClickListener(new MyChunJiadapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in=new Intent(getActivity(),ChunParticularsactivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                in.putExtras(bundle);
                startActivity(in);
                
            }
        });
        
       // 长按收藏
        chun.setOnItemLongClickListener(new MyChunJiadapter.OnItemLongClickListener() {
            @Override
            public void onitemLongClick(View view, int position) {
                
            }
        });
        
        
        
        
        
        
    }
       

    //优惠
    private void initdefaultGoodsList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recy_youhui.setLayoutManager(linearLayoutManager);

       

       Myadapteryou you=new Myadapteryou(defaultGoodsList,getActivity());
        recy_youhui.setAdapter(you);

    }


        
    private void initsubjects1() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics1.setLayoutManager(linearLayoutManager);
        
        String image2 = subjects.get(1).getImage();
        ImageLoader.getInstance().displayImage(image2,image_hot1);
        goodsList2 = subjects.get(1).getGoodsList();
        Myhotadapter hot1=new Myhotadapter(goodsList2,getActivity());
        recyclerView_hot_topics1.setAdapter(hot1);
    }
    
    
    private void initsubjects2() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics2.setLayoutManager(linearLayoutManager);
       
        String image3 = subjects.get(2).getImage();
        ImageLoader.getInstance().displayImage(image3,image_hot2);
        goodsList3 = subjects.get(2).getGoodsList();
        Myhotadapter hot2=new Myhotadapter(goodsList3,getActivity());
        recyclerView_hot_topics2.setAdapter(hot2);
    }
    
    
    private void initsubjects3() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics3.setLayoutManager(linearLayoutManager);
       
        String image4 = subjects.get(3).getImage();
        ImageLoader.getInstance().displayImage(image4,image_hot3);
        goodsList4 = subjects.get(3).getGoodsList();
        Myhotadapter hot3=new Myhotadapter(goodsList4,getActivity());
        recyclerView_hot_topics3.setAdapter(hot3);
    }
    private void initsubjects4() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics4.setLayoutManager(linearLayoutManager);

        String image4 = subjects.get(4).getImage();
        ImageLoader.getInstance().displayImage(image4,image_hot4);
        goodsList4 = subjects.get(4).getGoodsList();
        Myhotadapter hot3=new Myhotadapter(goodsList4,getActivity());
        recyclerView_hot_topics4.setAdapter(hot3);
    }
    private void initsubjects5() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics5.setLayoutManager(linearLayoutManager);

        String image4 = subjects.get(5).getImage();
        ImageLoader.getInstance().displayImage(image4,image_hot5);
        goodsList4 = subjects.get(5).getGoodsList();
        Myhotadapter hot3=new Myhotadapter(goodsList4,getActivity());
        recyclerView_hot_topics5.setAdapter(hot3);
    }
    private void initsubjects6() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics6.setLayoutManager(linearLayoutManager);

        String image4 = subjects.get(6).getImage();
        ImageLoader.getInstance().displayImage(image4,image_hot6);
        goodsList4 = subjects.get(6).getGoodsList();
        Myhotadapter hot3=new Myhotadapter(goodsList4,getActivity());
        recyclerView_hot_topics6.setAdapter(hot3);
    }
   // 热门   
    private void initsubjects() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_hot_topics.setLayoutManager(linearLayoutManager);
        String image1 = subjects.get(0).getImage();
        ImageLoader.getInstance().displayImage(image1,image_hot);
        goodsList1 = subjects.get(0).getGoodsList();
        Myhotadapter hot=new Myhotadapter(goodsList1,getActivity());
        recyclerView_hot_topics.setAdapter(hot);

    }
    private void initwei() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        viewById.setLayoutManager(gridLayoutManager);

        viewById.setAdapter(new Myadapterwei(getActivity(),subjects));
        
    }
    
    private void initview() {
        linear_qiandao = (LinearLayout) view.findViewById(R.id.linear_qiandao);
        linear_jifen = (LinearLayout) view.findViewById(R.id.linear_jifen);
        linear_duihuan = (LinearLayout) view.findViewById(R.id.linear_duihuan);
        linear_chaxun = (LinearLayout) view.findViewById(R.id.linear_chaxun);

        recy_chunji = (RecyclerView) view.findViewById(R.id.Recy_chunji);
        image_hot = (ImageView) view.findViewById(R.id.image_hot);
        image_hot1 = (ImageView) view.findViewById(R.id.image_hot1);
        image_hot2 = (ImageView) view.findViewById(R.id.image_hot2);
        image_hot3 = (ImageView) view.findViewById(R.id.image_hot3);
        image_hot4 = (ImageView) view.findViewById(R.id.image_hot4);
        image_hot5 = (ImageView) view.findViewById(R.id.image_hot5);
        image_hot6 = (ImageView) view.findViewById(R.id.image_hot6);
        recyclerView_hot_topics = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics);
        recyclerView_hot_topics1 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics1);
        recyclerView_hot_topics2 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics2);
        recyclerView_hot_topics3 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics3);
        recyclerView_hot_topics4 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics4);
        recyclerView_hot_topics5 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics5);
        recyclerView_hot_topics6 = (RecyclerView) view.findViewById(R.id.recyclerView_hot_topics6);

        viewById = (RecyclerView) view.findViewById(R.id.recy_wei);

        recy_youhui = (RecyclerView) view.findViewById(R.id.Recy_youhui);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_qiandao:
                
                
                
                break;

            case R.id.linear_jifen:
                
                break;

            case R.id.linear_duihuan:
                
                break;

            case R.id.linear_chaxun:
                
                break;
        }
        
        
    }
}
