package weijiangtao.bwie.com.newyunifangdemo.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import weijiangtao.bwie.com.newyunifangdemo.R;
import weijiangtao.bwie.com.newyunifangdemo.activity.Loginactivity;


/**
 * Created by asus on 2017/4/11.
 * <p>
 * 未江涛
 */

public class Myfragment extends Fragment {

    private View view;
    private ImageView denglu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_fragment,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        denglu = (ImageView) view.findViewById(R.id.denglu);
        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(),Loginactivity.class);
                startActivity(in);
                
            }
        });


    }
}
