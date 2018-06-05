import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Apps {

    public  static void main(String[] args) throws IOException, InterruptedException {
        OkHttpClient client = client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

                for (int i=0;i<200;i++){
                    try {
                        okhttpclientthread tmp=new okhttpclientthread(client,i);
                        tmp.start();
                    }catch (Exception e){
                        System.out.println("ssss");
                        continue;
                    }

                }
                Thread.sleep(200*1000);

    }
    public static void aaaa(OkHttpClient client) throws IOException {
        Request request=null;
        Response response=null;
            request = new Request.Builder().url("http://localhost:8080/intelliq-web/TestServlet/aa").build();
            response = client.newCall(request).execute();
            response.body().close();

        //System.out.println(response.body().string());
    }


}

class okhttpclientthread extends Thread {
    int number=0;
    OkHttpClient client =null;
    public  okhttpclientthread(OkHttpClient client,int num){
        this.client=client;
        this.number=num;
    }
    @Override
    public void run() {

        System.out.println(this.number+"running");
        Request request=null;
        Response response=null;
        request = new Request.Builder().url("http://localhost:8080/intelliq-web/TestServlet/aa").build();
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.body().close();

    }
}
