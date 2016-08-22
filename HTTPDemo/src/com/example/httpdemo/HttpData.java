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
    
    //1.�����ͻ���
    private HttpClient mhttpclient;
    //2��ָ�����ݵķ���
    private HttpGet mhttpget;
    //3.������Ӧ
    private HttpResponse mhttpresponse;
    //4.����ʵ��
    private HttpEntity mhttpentity;
    //5.ʹ���������������ݵĴ���
    private InputStream in;
    //6.����������
    private BufferedReader br;
    //7.�������е�����
    private StringBuffer sb;
    
    //�����ӿ�
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
            //ʵ����һ���ͻ���
            mhttpclient = new DefaultHttpClient();
            //ʹ��get�������д���
            mhttpget = new HttpGet(url);
            //ͨ���ͻ��˽��з���
            mhttpresponse = mhttpclient.execute(mhttpget);
            //ͨ��response������ȡʵ��
            mhttpentity = mhttpresponse.getEntity();
            //ͨ������ȡ���������
            in = mhttpentity.getContent();
            //����������
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