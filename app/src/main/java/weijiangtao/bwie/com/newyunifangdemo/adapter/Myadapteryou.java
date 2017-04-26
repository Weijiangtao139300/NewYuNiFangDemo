package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.bean.Homebean;

/**
 * Created by asus on 2017/4/18.
 * <p>
 * 未江涛
 */

public class Myadapteryou extends RecyclerView.Adapter<Myadapteryou.Viewhouder> {
    private List<Homebean.DataBean.DefaultGoodsListBean> list;
    private Context context;

    public Myadapteryou(List<Homebean.DataBean.DefaultGoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public Viewhouder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= View.inflate(context, R.layout.you_item_item,null);
        

        Viewhouder holder=new Viewhouder(view);
        
        return holder;
    }

    @Override
    public void onBindViewHolder(Viewhouder holder, int position) {

        ImageLoader.getInstance().displayImage(list.get(position).getGoods_img(),holder.image2);
       



    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Viewhouder extends RecyclerView.ViewHolder{
       
        private final ImageView image2;

        public Viewhouder(View itemView) {
            super(itemView);


            image2 = (ImageView) itemView.findViewById(R.id.you_item_item_image);

           




        }
    }
}
