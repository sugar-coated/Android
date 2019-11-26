package coursework.luyu.no217.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import butterknife.ButterKnife;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.Sqlite.Helper;

public abstract class Prototype_fragment extends Fragment {

   protected Context mContext217;
   protected Helper helper217;
    /*
     * onCreate()方法是最开始进行初始化非图形变量，
     * 然后是onCreateView()方法对布局文件进行初始
     * 化，然后是onActivityCreate()方法执行，具备
     * 了之前的多有条件进行活动
     * */

    //初始化上下文对象和baseFragment公用上下文对象
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext217 = getContext();
        helper217 = new Helper(getContext(),"TuoGe.db",null,1);

    }

    //在活动创建时加载数据
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData(helper217);

    }
    //在初始化布局时初始化布局
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView();
    }

    //初始化布局
    public abstract  View initView();
    //初始化数据
    public abstract List<Event> initData(Helper helper);

    @Override
    public void onDestroy() {
        super.onDestroy();
        helper217.close();

    }



}
