package httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestThread1 extends Thread{
	int start;
	int end;
	public TestThread1(){}
	public TestThread1(int start, int end){
		this.start = start;
		this.end = end;
	}
	public void run(){
		for(int i = start; i < end; i++){
			TestThread1 testThread1 = new TestThread1();
			String value = testThread1.getValue(i);
			Data data = testThread1.getValus(value);
			if(data != null){
				System.out.println(data.getCity() + data.getOrigphoneno() + data.getType());
			}
		}
	}
	public String getValue(int number){
		DefaultHttpClient client = new DefaultHttpClient();
		String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query=" + number + "+%E6%89%8B%E6%9C%BA%E5%8F%B7%E6%AE%B5&co=&resource_id=6004&t=1460896841999&ie=utf8&oe=gbk&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery1102022389220127641263_1460896815174&_=1460896815195";
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		String str = null;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				str = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return str;
	}
	
	public Data getValus(String str){
		Data data = new Data();
		String strjson = str.replace("/**/jQuery1102022389220127641263_1460896815174(", "").replace(");", "");
		JsonParser jp = new JsonParser();
		JsonObject jsonobject = (JsonObject)jp.parse(strjson);
		if(jsonobject != null && jsonobject.toString().contains("city")){
			String city = jsonobject.getAsJsonArray("data").get(0).getAsJsonObject().get("city").toString();
			String type = jsonobject.getAsJsonArray("data").get(0).getAsJsonObject().get("type").toString();
			String origphoneno = jsonobject.getAsJsonArray("data").get(0).getAsJsonObject().get("origphoneno").toString(); 
			data.setCity(city);
			data.setOrigphoneno(origphoneno);
			data.setType(type);
		}
		return data;
	}
	
}

class Data{
	String city;
	String type;
	String origphoneno;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrigphoneno() {
		return origphoneno;
	}
	public void setOrigphoneno(String origphoneno) {
		this.origphoneno = origphoneno;
	}
}