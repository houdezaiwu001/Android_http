package com.example.httpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements HttpDataUrlListener{

	private HttpData httpdata;   
    private TextView tx;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpdata = (HttpData) new HttpData("http://192.168.1.103/project01/test.php",this).execute();
        tx = (TextView)findViewById(R.id.textview);
        
    }

    @Override
    public void GetDataUrl(String data) {
        // TODO Auto-generated method stub
        System.out.println(data);
        tx.setText(data);
    }

}
