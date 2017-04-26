package weijiangtao.bwie.com.newyunifangdemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Myviewpage extends PagerAdapter {

    private List<View>list;

    public Myviewpage(List<View> list) {
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));



        return list.get(position);



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
        container.removeView(list.get(position));


    }
}
