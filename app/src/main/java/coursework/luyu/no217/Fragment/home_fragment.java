package coursework.luyu.no217.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.LinkedList;
import java.util.List;
import coursework.luyu.no217.Adapter.CardViewAdapter;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.DBManager;
import coursework.luyu.no217.Sqlite.Helper;


public class home_fragment extends Prototype_fragment {


    private List<Event> list217;
    private CardViewAdapter cardViewAdapter;
    private RecyclerView recycler217;
    private List<Event> list27;
    private Toolbar toolBar217;
    private FloatingActionButton floatActionButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    /*
     * 初始化布局
     * */

    @SuppressLint("ResourceType")
    @Override
    public View initView() {

        View home_fragment_view217 = View.inflate(mContext217, R.layout.home_fragment_layout, null);


        recycler217 = (RecyclerView) home_fragment_view217.findViewById(R.id.recycler);
        floatActionButton = (FloatingActionButton) home_fragment_view217.findViewById(R.id.float_button);
        swipeRefreshLayout = (SwipeRefreshLayout)home_fragment_view217.findViewById(R.id.refresh_data);

        //设置刷新圆圈颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
         //在初始化布局就进行数据刷新
        initListener();
        return home_fragment_view217;

    }

    /*
     * 初始化数据
     * */
    @Override
    public List<Event> initData(Helper helper) {

        list27 = new LinkedList<>();
        SQLiteDatabase db217 = helper.getWritableDatabase();
        DBManager dbManager217 = new DBManager(db217, mContext217);
        list217 = dbManager217.read(null);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext217, 2);
        recycler217.setLayoutManager(gridLayoutManager);
        cardViewAdapter = new CardViewAdapter(mContext217, list217,db217);
        recycler217.setAdapter(cardViewAdapter);

        return null;
    }


    private void initListener() {

        floatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recycler217.scrollToPosition(0);

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                SQLiteDatabase db217 = helper217.getWritableDatabase();
                refreshData(db217,mContext217);
                recycler217.setAdapter(cardViewAdapter);
                //刷新结束隐藏进度条
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void refreshData(SQLiteDatabase db217, Context mContext217){

    new Thread(new Runnable() {
        @Override
        public void run() {
            //刷新数据的逻辑代码
            DBManager manager217 = new DBManager(db217,mContext217);
            list217 = manager217.read(null);
            cardViewAdapter = new CardViewAdapter(mContext217, list217,db217);

        }
    }).start();

    }

}
