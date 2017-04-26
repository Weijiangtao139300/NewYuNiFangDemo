package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.Map;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.Homebean;

/**
 * Created by asus on 2017/4/18.
 * <p>
 * 未江涛
 */

public class Myadapterwei extends RecyclerView.Adapter<Myadapterwei.Viewhouder> {
    private Context context;
    private List<Homebean.DataBean.SubjectsBean>list;

    public Myadapterwei(Context context, List<Homebean.DataBean.SubjectsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewhouder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=View.inflate(context, R.layout.wei_item,null);
        Viewhouder viewhouder=new Viewhouder(view);
        
        return viewhouder;
    }

    @Override
    public void onBindViewHolder(Viewhouder holder, int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getGoodsList().get(position).getGoods_img(),holder.wei_image);
        holder.wei_text1.setText(list.get(position).getGoodsList().get(position).getGoods_name());
        holder.wei_text2.setText(list.get(position).getGoodsList().get(position).getEfficacy());
        holder.wei_text3.setText(list.get(position).getGoodsList().get(position).getShop_price()+"");
    }

    
    public int getItemCount() {
        return list.size();
    }
    
    public class Viewhouder extends RecyclerView.ViewHolder{
        
        private final ImageView wei_image;
        private final TextView wei_text1,wei_text2,wei_text3;
        public Viewhouder(View itemView) {
            super(itemView);

             wei_image = (ImageView) itemView.findViewById(R.id.wei_image);
            wei_text1 = (TextView) itemView.findViewById(R.id.wei_text1);
            wei_text2 = (TextView) itemView.findViewById(R.id.wei_text2);
            wei_text3 = (TextView) itemView.findViewById(R.id.wei_text3);

        }
    }
    
    
    
}
