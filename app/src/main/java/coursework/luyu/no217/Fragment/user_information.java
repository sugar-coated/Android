package coursework.luyu.no217.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.Activities.ChangeInformation;
import coursework.luyu.no217.Activities.GiveMoneyToMr_TG;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.Helper;
import de.hdodenhof.circleimageview.CircleImageView;

public class user_information extends Prototype_fragment {


    @BindView(R.id.touxiang)
    CircleImageView touxiang217;
    @BindView(R.id.show_user_information)
    RelativeLayout showUserInformation217;
    @BindView(R.id.w_times)
    TextView wTimes217;
    @BindView(R.id.message1)
    RelativeLayout message1217;
    @BindView(R.id.d_times)
    TextView dTimes217;
    @BindView(R.id.message2)
    RelativeLayout message2217;
    @BindView(R.id.message3)
    RelativeLayout message3217;
    @BindView(R.id.message4)
    RelativeLayout message4217;
    @BindView(R.id.item_select)
    LinearLayout itemSelect217;

    @BindView(R.id.user_name)
    TextView userNameView217;
    @BindView(R.id.change_id)
    ImageView changeIdView217;
    @BindView(R.id.dashang)
    ImageView dashangView217;
    @BindView(R.id.new_update)
    ImageView newUpdate217;
    @BindView(R.id.delete_update)
    ImageView deleteUpdate217;

    private String username217;
    private int record_times217;
    private int finish_times217;

    @Override
    public View initView() {
        View delete_fragment_view217 = null;
        int flag = isRegister();
        Log.d("调试信息", "当前已经成功进入函数" + flag);

        delete_fragment_view217 = View.inflate(mContext217, R.layout.user_information_layout, null);

        //绑定ButterKnife
        ButterKnife.bind(this, delete_fragment_view217);

        //监听器
        listener();
        return delete_fragment_view217;
    }

    private void chushi() {


    }

    //根据sp中的只判断加载那个布局
    private int isRegister() {
        int flag217 = 0;
        SharedPreferences sharedPreferences217 = mContext217.getSharedPreferences("user1", Context.MODE_PRIVATE);
        flag217 = sharedPreferences217.getInt("keyForLayout", -1);
        return flag217;
    }

    @Override
    public List<Event> initData(Helper helper) {

        get_user_information();

        initializeDataOnView();

        return null;
    }

    //将数据绑定到组件上
    private void initializeDataOnView() {

        userNameView217.setText(username217);
        wTimes217.setText("你已经记事的次数:"+record_times217);
        dTimes217.setText("你已经完成的事件数量:"+finish_times217);

    }

    //初始化用户名称和事件相关次数
    private void get_user_information() {

        SharedPreferences sharedPreferences217 = mContext217.getSharedPreferences("user1", Context.MODE_PRIVATE);
        username217 = sharedPreferences217.getString("username", "null");
        record_times217 = sharedPreferences217.getInt("record_times", 0);
        finish_times217 = sharedPreferences217.getInt("finish_times", 0);

    }

    //监听器
    private void listener() {

        changeIdView217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent217 = new Intent(mContext217, ChangeInformation.class);
                startActivity(intent217);

            }
        });

        dashangView217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent217 = new Intent(mContext217, GiveMoneyToMr_TG.class);
                startActivity(intent217);

            }
        });

        newUpdate217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            update(0);


            }
        });


        deleteUpdate217.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               update(1);
            }
        });

    }

    private void update(int mode){
        SharedPreferences sharedPreferences217 = mContext217.getSharedPreferences("user1",Context.MODE_PRIVATE);
        int value217 = 0;
        switch (mode){

            case 0:
                value217 = sharedPreferences217.getInt("record_times",-1);
                wTimes217.setText("你已经记事的次数:"+value217);
                break;
            case 1:
                value217 = sharedPreferences217.getInt("finish_times",-1);
                dTimes217.setText("你已经完成的事件数量:"+value217);
                break;
                default:
                    break;

        }


    }

}