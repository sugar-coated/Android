package coursework.luyu.no217.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.List;

import coursework.luyu.no217.Activities.CreateContent;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.Helper;


public class add_fragment extends Prototype_fragment implements View.OnClickListener {

  private Button casual;
  private Button life;
  private Button work;
  private Button things;
  private Button study;
  private Button cost;
  private Button other;
  private Intent intent;


    @Override
    public View initView() {

        //这里的上下文对象在onCreate方法中已经初始化
         View add_fragment_view217 = View.inflate(mContext217, R.layout.add_fragment_layout,null);

         casual = (Button)add_fragment_view217.findViewById(R.id.casual);
        life = (Button)add_fragment_view217.findViewById(R.id.life);
        work = (Button)add_fragment_view217.findViewById(R.id.work);
        things = (Button)add_fragment_view217.findViewById(R.id.things);
        study = (Button)add_fragment_view217.findViewById(R.id.study);
        cost = (Button)add_fragment_view217.findViewById(R.id.cost);
        other = (Button)add_fragment_view217.findViewById(R.id.other);

        casual.setOnClickListener(this);
        life.setOnClickListener(this);
        work.setOnClickListener(this);
        things.setOnClickListener(this);
        study.setOnClickListener(this);
        cost.setOnClickListener(this);
        other.setOnClickListener(this);

        return add_fragment_view217;
    }

    @Override
    public List<Event> initData(Helper helper) {
        return null;
    }


    @Override
    public void onClick(View v) {

        intent = new Intent(v.getContext(), CreateContent.class);

        switch (v.getId()){

            case R.id.casual:

                intent.putExtra("EventType","随心记录");
                startActivity(intent);
                break;

            case R.id.life:

                intent.putExtra("EventType","生活小日常");
                startActivity(intent);
                break;
            case R.id.work:

                intent.putExtra("EventType","繁忙的工作");
                startActivity(intent);
                break;
            case R.id.things:

                intent.putExtra("EventType","杂七杂八的琐事");
                startActivity(intent);
                break;
            case R.id.study:

                intent.putExtra("EventType","学习提醒");
                startActivity(intent);
                break;
            case R.id.cost:

                intent.putExtra("EventType","又花钱了");
                startActivity(intent);
                break;
            case R.id.other:

                intent.putExtra("EventType","其他");
                startActivity(intent);
                break;


                default:

                    break;


        }
    }
}
