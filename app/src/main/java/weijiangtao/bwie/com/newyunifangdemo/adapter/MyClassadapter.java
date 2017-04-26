package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.ActionBarContextView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.Bean;

/**
 * Created by asus on 2017/4/13.
 * <p>
 * 未江涛
 */

public class MyClassadapter extends BaseAdapter{
    
    private List<Bean.DataBean.GoodsBriefBean>list;
    private Context context;

    public MyClassadapter(List<Bean.DataBean.GoodsBriefBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewhouder v;
        
        if(convertView==null){
            convertView=View.inflate(context, R.layout.classitem,null);
            v=new Viewhouder();
            v.itemclass_image= (ImageView) convertView.findViewById(R.id.itemclass_image);
            v.itemclass_text1= (TextView) convertView.findViewById(R.id.itemclass_text1);
            v.itemclass_text2= (TextView) convertView.findViewById(R.id.itemclass_text2);
            v.itemclass_text3= (TextView) convertView.findViewById(R.id.itemclass_text3);
            convertView.setTag(v);
        }else {
            v= (Viewhouder) convertView.getTag();
        }
        //显示图片的配置  
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),  v.itemclass_image, options);


        v.itemclass_text1.setText(list.get(position).getEfficacy());
        v.itemclass_text2.setText(list.get(position).getGoods_name());
        v.itemclass_text3.setText(list.get(position).getMarket_price()+"");
      
        return convertView;
    }
    
    class Viewhouder{
        ImageView itemclass_image;
        TextView itemclass_text1,itemclass_text2,itemclass_text3;
    }
    
    
}
