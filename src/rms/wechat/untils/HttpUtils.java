package rms.wechat.untils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import rms.wechat.entity.Access_Token;
import rms.wechat.entity.Access_Token_Request;
import rms.wechat.entity.PageAccess_Token;
import rms.wechat.entity.TemplateData;
import rms.wechat.entity.TemplateMessage;
import rms.wechat.entity.jsapi_ticket;

import com.alibaba.fastjson.JSON;

/**
 * 用于发送/接送 http相关数据
 * @author zjx
 *
 */
public class HttpUtils {

/*    *//**
     *  发送模板消息路径
     *//*
    private  static String sendTemplateMessage;
    *//**
     * 获取二维码地址
     *//*
    private static String GETtwocode;
    *//**
     * 获取网页通信密钥的地址
     *//*
    private static String GETPageACCESS_TOKEN;
    *//**
     * 获取普通客户端调用密钥
     *//*
    private static String GETACCESS_TKOKEN;

    *//**
     * 初始化路径数据
//     *//*
    static{
	try {
	    Properties props = new Properties();
	    InputStream in = HttpUtils.class.getResourceAsStream("/httpconfig.properties");
	    props.load(in);
	    sendTemplateMessage=props.getProperty("sendTemplateMessage");
	    GETtwocode=props.getProperty("GETtwocode");
	    GETPageACCESS_TOKEN=props.getProperty("GETPageACCESS_TOKEN");
	    GETACCESS_TKOKEN=props.getProperty("GETACCESS_TKOKEN");
//	    ACCESS_TOKEN=props.getProperty("access_token");
	} catch (Exception e) {
	    throw new RuntimeException("Http初始化路径出错");
	}
    }*/
    /**
     * 
    * @Title: GETACCESS_TKOKEN 
    * @Description: 获取客户端接口调用凭证
    * @param @param RequestObject
    * @param @param wechaturl
    * @param @return
    * @param @throws Exception    
    * @return Access_Token    
    * @throws
     */
    public static Access_Token GETACCESS_TKOKEN(Access_Token_Request RequestObject,String wechaturl) throws Exception {
  	String url= wechaturl.replaceAll("\\$APPID", RequestObject.getAppid()).replaceAll("\\$APPSECRET", RequestObject.getAppsecret());
  	String Responsejesonmessage=sendHttpRequestofurl(url, "");
  	return JSON.parseObject(Responsejesonmessage, Access_Token.class);
      }

    /**
     * @throws Exception 
     * 
     * @Title: sendTempMessage 
     * @Description: 发送模板消息 
     * @param @param messgeobject  
     * @param @param ACCESS_TOKEN 
     * @param @param wechaturl 
     * @return void    
     * @throws
     */
    public static void  sendTemplateMessage(TemplateMessage messgeobject,String ACCESS_TOKEN,String wechaturl) throws Exception {
	String url= wechaturl.replaceAll("\\$ACCESS_TOKEN", ACCESS_TOKEN);
	String jsonmessage=JSON.toJSONString(messgeobject);
	System.out.println(jsonmessage);
	sendHttpRequestofurl(url, jsonmessage);
    }
    /**
     * 
     * @Title: getPageACCESS_TOKEN 
     * @Description: 获取网页接口调用凭证
     * @param @param code
     * @param @param appid
     * @param @param appsecret
     * @param @param wechaturl
     * @param @throws Exception    
     * @return void    
     * @throws
     */
    public static String getPageACCESS_TOKEN(String code,String appid,String appsecret,String wechaturl) throws Exception{
	String url=wechaturl
		.replaceAll("\\$APPID", appid)
		.replaceAll("\\$SECRET", appsecret)
		.replaceAll("\\$CODE", code);
	url=new String(url.getBytes("UTF-8"));
	System.out.println("url------->"+url);
	String reponseMessage=sendHttpRequestofurl(url,"");
	PageAccess_Token object=JSON.parseObject(reponseMessage, PageAccess_Token.class);
	return object.getOpenid();
    }
    
    /**
     * 
    * @Title: getJsApiTicket 
    * @Description: 获取 jsapi 调用凭据
    * @param @param access_token
    * @param @return wechaturl
    * @param @throws Exception    
    * @return jsapi_ticket    
    * @throws
     */
    public static jsapi_ticket getJsApiTicket(String access_token,String wechaturl) throws Exception {
	String url=wechaturl.replaceAll("\\$ACCESS_TOKEN", access_token);
//	String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
	String reponseMessage=sendHttpRequestofurl(url,"");
	jsapi_ticket reponseObject=JSON.parseObject(reponseMessage, jsapi_ticket.class);
	if("0".equals(reponseObject.getErrcode())) {
	    return reponseObject;
	}
	return null;
    }
    

    /**
     * 
     * @Title: getTwoCodeMessage 
     * @Description: 获取二维码
     * @param @param message
     * @param @param ACCESS_TOKEN
     * @param @throws Exception    
     * @return void    
     * @throws
     */
    public static void getTwoCodeMessage(String message,String ACCESS_TOKEN) throws Exception {
//	String url=GETtwocode.replaceAll("\\$ACCESS_TOKEN", ACCESS_TOKEN);
//	sendHttpRequestofurl(url, message);
	//暂不使用
    }

    /**
     * 
     * @Title: sendHttpRequestofurl 
     * @Description: 向指定的url发送http请求，并返回响应字符
     * @param @param url(携带请求参数)
     * @param @param message 发送的信息
     * @param @return
     * @param @throws Exception    
     * @return String    
     * @throws
     */
    private static String sendHttpRequestofurl(String url,String message) throws Exception{
	StringBuilder sb = new StringBuilder();
	// 设置soap请求报文的相关属性
	URL u = new URL(url);
	HttpURLConnection conn = (HttpURLConnection) u.openConnection();
	conn.setDoInput(true);
	conn.setDoOutput(true);
	conn.setUseCaches(false);
	conn.setDefaultUseCaches(false);
	conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
	conn.setRequestMethod("POST");
	System.out.println(message);
	// 定义输出流
	OutputStream output = conn.getOutputStream();
	if (null != message) {
	    byte[] b = message.getBytes("utf-8");
	    // 发送soap请求报文
	    output.write(b, 0, b.length);
	}
	output.flush();
	output.close();
	// 定义输入流，获取soap响应报文
	InputStream input = conn.getInputStream();
	int c = -1;
	// sb为返回的soap响应报文字符串
	while (-1 != (c = input.read())) {
	    sb.append((char)c);
	}
	input.close();
	System.out.println("request"+sb);
	return sb.toString();

    }
    public static void main(String[] args) {
	try {
//		TemplateMessage tm=new TemplateMessage();
//		tm.setTouser("ocsPft5Ly1aVTcZ5CgUMAVPrqOJA");
//		tm.setTemplate_id("_lUqhg3B8DAjlueOPUXWc3Er-H-nISm-YvABmeuKn5Y");
//		tm.setTopcolor("#FF0000");
//		tm.setUrl("http://weixin.qq.com/download");
//		Map<String,TemplateData> dataMap=new HashMap<>();
//		TemplateData price=new TemplateData();
//		price.setColor("#173177");
//		price.setValue("12312元");
//		TemplateData date=new TemplateData();
//		date.setColor("#173177");
//		date.setValue("囧");
//		
//		dataMap.put("price", price);
//		dataMap.put("date", date);
//		tm.setData(dataMap);
//		sendTemplateMessage(tm);
//		sendTempMessage(message.toString());

//	    StringBuffer message=new StringBuffer();
//	    message.append("{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}");
//
//	    getTwoCodeMessage(message.toString());;

	    //	https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
//		System.out.println(message);
//		System.out.println(JSON.toJSONString(tm));
	    
	    Date time=new Date(System.currentTimeMillis()-1000);
	    SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd hh:mm:ss");
	    System.out.println(sdf.format(time));
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
