package com.example.httpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class HttpData extends AsyncTask<String, Void, String>{
    
    //1.创建客户端
    private HttpClient mhttpclient;
    //2。指明传递的方法
    private HttpGet mhttpget;
    //3.做出回应
    private HttpResponse mhttpresponse;
    //4.创建实体
    private HttpEntity mhttpentity;
    //5.使用数据流进行数据的传输
    private InputStream in;
    //6.创建缓冲区
    private BufferedReader br;
    //7.储存所有的数据
    private StringBuffer sb;
    
    //声明接口
    private HttpDataUrlListener listener;
    
    
    
    private String url;
    
    public HttpData(){
    }
    public HttpData(String url){
        this.url = url;
    }
    public HttpData(String url,HttpDataUrlListener listener){
        this.url = url;
        this.listener = listener;
    }
    
    @Override
    protected String doInBackground(String... params) {
        // TODO Auto-generated method stub
        
        try {
            //实例化一个客户端
            mhttpclient = new DefaultHttpClient();
            //使用get方法进行传递
            mhttpget = new HttpGet(url);
            //通过客户端进行发送
            mhttpresponse = mhttpclient.execute(mhttpget);
            //通过response方法获取实体
            mhttpentity = mhttpresponse.getEntity();
            //通过流获取具体的内容
            in = mhttpentity.getContent();
            //创建缓冲区
            br = new BufferedReader(new InputStreamReader(in));
            String line = null;
            sb = new StringBuffer();
            while ((line = br.readLine())!=null){
                sb.append(line);
            }
            return sb.toString();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //
        
        return null;
    }
    
    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        listener.GetDataUrl(result);
        super.onPostExecute(result);
    }

}