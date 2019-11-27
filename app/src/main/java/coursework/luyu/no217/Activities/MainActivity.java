package coursework.luyu.no217.Activities;

import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import coursework.luyu.no217.Fragment.Prototype_fragment;
import coursework.luyu.no217.Fragment.no_register_fragment;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Fragment.add_fragment;
import coursework.luyu.no217.Fragment.user_information;
import coursework.luyu.no217.Fragment.home_fragment;

public class MainActivity extends FragmentActivity{

    private RadioButton home217;
    private RadioButton add217;
    private RadioButton  delete217;
    private List<Prototype_fragment> fragment_list217;
    private RadioGroup radioGroup217;
    private int position217 ;
    private Fragment saveFragment217;
    private  FragmentTransaction fragmentTransaction217;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home217 = (RadioButton)findViewById(R.id.home);
        add217 = (RadioButton)findViewById(R.id.add);
        delete217 = (RadioButton)findViewById(R.id.delete);
        radioGroup217 = (RadioGroup)findViewById(R.id.radio_button_group);

        //初始化Fragment
        initFragment();

        //RadioGroup中的RadioButton的check事件监听器
        fragmentsListener();

        //默认选中为home界面
        radioGroup217.check(0);
        home217.setChecked(true);

    }

    private void initFragment(){

        fragment_list217  = new ArrayList<>();
        fragment_list217.add(new home_fragment());
        fragment_list217.add(new add_fragment());

        initUserInformation();




    }

    private void initUserInformation() {

        int flag217 = -1;
        SharedPreferences sharedPreferences217 = this.getSharedPreferences("user1",MODE_PRIVATE);
        flag217 =  sharedPreferences217.getInt("keyForLayout",-1);

        if(flag217==0){

            fragment_list217.add(new no_register_fragment());

        }else if (flag217==1){

            fragment_list217.add(new user_information());

        }
    }


    private void fragmentsListener(){

        Log.d("调试信息","当前已经进入监听函数");

         radioGroup217.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {

                  switch (checkedId){

                      case R.id.home:

                      position217 = 0;

                          Log.d("check调试信息",0+"");

                      break;

                      case R.id.add:

                          position217 = 1;
                          Log.d("check调试信息",1+"");
                             break;
                      case R.id.delete:

                          position217 = 2;
                          Log.d("check调试信息",2+"");


                          break;

                          default:

                              break;

                  }

                 Prototype_fragment selectedFragment217 = fragment_list217.get(position217);

                 //选项卡功能
                 showSelectedFragment(selectedFragment217);

             }

         });

        radioGroup217.check(R.id.home);
        Log.d("调试信息","当前默认界面已经绑定，且当前的position为："+position217);

    }

  //选项卡功能，实现对Fragment在不重复添加Fragment的情况下实现切换
    private void showSelectedFragment(Prototype_fragment selectedFragment217) {


         //判断保存Fragment和当前选择的Fragment是否相同
         if(saveFragment217 != selectedFragment217){

             Log.d("调试信息","当前进入判断是否重复点击");


             if (selectedFragment217!=null) {

               fragmentTransaction217 = getSupportFragmentManager().beginTransaction();

                 if(selectedFragment217.isAdded()){

                     Log.d("调试信息","当前进入判断是否已经添加函数");
                     if(saveFragment217!=null){

                         fragmentTransaction217.hide(saveFragment217);
                         Log.d("调试信息","当前执行隐藏上一个fragment"+saveFragment217.toString());
                         saveFragment217 = selectedFragment217;
                         Log.d("调试信息","当前执行隐藏上一个fragment"+saveFragment217.toString());

                     }

                     fragmentTransaction217.show(selectedFragment217).commit();

                     Log.d("调试信息","展现已经加过的fragment");

                 }else{

                   if (saveFragment217!=null) {

                         fragmentTransaction217.hide(saveFragment217);
                         Log.d("调试信息","隐藏已经加过的fragment");

                     }

                     fragmentTransaction217.add(R.id.fragment,selectedFragment217).commit();
                     Log.d("调试信息","添加完成");

                     fragmentTransaction217.show(selectedFragment217);
                     saveFragment217 = selectedFragment217;

                 }

             }

         }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
