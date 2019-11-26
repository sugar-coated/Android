package coursework.luyu.no217.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import coursework.luyu.no217.Activities.CreateContent;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.Helper;


public class add_fragment extends Prototype_fragment implements View.OnClickListener {

    @BindView(R.id.casual)
    Button casual217;
    @BindView(R.id.life)
    Button life217;
    @BindView(R.id.work)
    Button work217;
    @BindView(R.id.things)
    Button things217;
    @BindView(R.id.study)
    Button study217;
    @BindView(R.id.cost)
    Button cost217;
    @BindView(R.id.other)
    Button other217;

    private Intent intent;


    @Override
    public View initView() {

        //这里的上下文对象在onCreate方法中已经初始化
        View add_fragment_view217 = View.inflate(mContext217, R.layout.add_fragment_layout, null);
        ButterKnife.bind(this,add_fragment_view217);
        listener();
        return add_fragment_view217;
    }

   //监听器
    private void listener() {
        casual217.setOnClickListener(this);
        life217.setOnClickListener(this);
        work217.setOnClickListener(this);
        things217.setOnClickListener(this);
        study217.setOnClickListener(this);
        cost217.setOnClickListener(this);
        other217.setOnClickListener(this);

    }

    @Override
    public List<Event> initData(Helper helper) {
        return null;
    }


    @Override
    public void onClick(View v) {

        intent = new Intent(v.getContext(), CreateContent.class);

        switch (v.getId()) {

            case R.id.casual:

                intent.putExtra("EventType", "随心记录");
                startActivity(intent);
                break;

            case R.id.life:

                intent.putExtra("EventType", "生活小日常");
                startActivity(intent);
                break;
            case R.id.work:

                intent.putExtra("EventType", "繁忙的工作");
                startActivity(intent);
                break;
            case R.id.things:

                intent.putExtra("EventType", "杂七杂八的琐事");
                startActivity(intent);
                break;
            case R.id.study:

                intent.putExtra("EventType", "学习提醒");
                startActivity(intent);
                break;
            case R.id.cost:

                intent.putExtra("EventType", "又花钱了");
                startActivity(intent);
                break;
            case R.id.other:

                intent.putExtra("EventType", "其他");
                startActivity(intent);
                break;


            default:

                break;


        }
    }
}
