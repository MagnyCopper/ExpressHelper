package com.example.hanchangming.expresshelper;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class scanActivity extends AppCompatActivity {

    EditText ExpressNumber;
    Button enterButton;
    Spinner comSelecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ExpressNumber = (EditText) findViewById(R.id.ExpressNumber);
        enterButton = (Button) findViewById(R.id.enterButton);
        comSelecter = (Spinner) findViewById(R.id.comSelecter);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfomation();
            }
        });
    }

    protected String companyCodeGetter() {
        String comCode = null;

        if (comSelecter.getSelectedItem().toString().equals("顺丰速运")) {
            comCode = "shunfeng";
        }
        if (comSelecter.getSelectedItem().toString().equals("韵达快运")) {
            comCode = "yunda";
        }
        if (comSelecter.getSelectedItem().toString().equals("中通速递")) {
            comCode = "zhongtong";
        }
        return comCode;
    }

    protected void getInfomation() {
        String comCode = companyCodeGetter();
        String expressNumber = ExpressNumber.getText().toString();
        String url ="http://wap.kuaidi100.com/wap_result.jsp?rand=20120517&id="+comCode+"&fromWeb=null&&postid="+expressNumber;

        Uri uri=Uri.parse(url);
        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }
}
