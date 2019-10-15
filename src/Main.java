import java.io.*;
import okhttp3.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class Main {

    private static void okhttp_test(){

        //第一步创建OKHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        //第二步创建RequestBody
        RequestBody body = new FormBody.Builder()
                .add("code", "A579")
                .add("quantity", "10")
                .add("shade", "27")
                .build();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody jbody = RequestBody.create(JSON, "{\"code\": \"A579\", \"quantity\": \"10\",\"shade\": \"27\"}");
        //第三步创建Rquest
        final Request request = new Request.Builder()
                .url("http://www.aristonfabrics.com/api/inquiry/availability")
                .post(jbody)
                .build();
        //第四步创建call回调对象
        final Call call = client.newCall(request);
        //第五步发起请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    String result = response.body().string();
                    //Log.i("response", result);
                    System.out.println(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static void jsoup_test() throws Exception {
        String _url = "http://support.vitalitytex.com:8080/pc/ec/prodetail?num=1210085";
        Document doc = Jsoup.connect(_url).get();
        Elements bianhao = doc.getElementsByClass("y_detailsR_A");
        System.out.println("===" + bianhao.first().getElementsByTag("h5").get(1).text() + "===");
        Elements xiangqing = doc.getElementsByClass("y_detailsR_C");
        for (Element el:xiangqing){
            System.out.println(el.getElementsByClass("y_detailsR_C_em").text() + "   ===   ");
        }
        Elements avl = doc.getElementsByClass("y_detailsR_D").first().getElementsByClass("y_detailsR_C_em");
        System.out.println(avl.text());
    }

    public static void main(String[] args) throws Exception{
        jsoup_test();//测试jsoup
    }
}
