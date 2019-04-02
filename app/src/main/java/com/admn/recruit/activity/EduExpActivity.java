package com.admn.recruit.activity;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.admn.recruit.R;

public class EduExpActivity extends AppCompatActivity {

    private View view;
    private Button btn_add_edu_exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_exp);

        initView();

        btn_add_edu_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(EduExpEditActivity.class);
            }
        });
    }






    private void initView() {
        btn_add_edu_exp =(Button) findViewById(R.id.btn_add_edu_exp);

    }



    public void jumpActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
