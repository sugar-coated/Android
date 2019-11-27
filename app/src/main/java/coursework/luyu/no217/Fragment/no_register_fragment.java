package coursework.luyu.no217.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import java.util.List;
import coursework.luyu.no217.Activities.RegisterForVisitor;
import coursework.luyu.no217.JavaBeans.Event;
import coursework.luyu.no217.R;
import coursework.luyu.no217.Sqlite.Helper;

public class no_register_fragment extends Prototype_fragment {

 private Button button;

    @Override
    public View initView() {

        View no_register_fragment_layout = View.inflate(mContext217, R.layout.no_register_user_information_layout, null);

        button = no_register_fragment_layout.findViewById(R.id.button);
        listener();
        return no_register_fragment_layout;
    }

    private void listener() {


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent217 = new Intent(mContext217, RegisterForVisitor.class);
                startActivity(intent217);


            }
        });

    }


    @Override
    public List<Event> initData(Helper helper) {
        return null;
    }

    public no_register_fragment() {
    }
}
