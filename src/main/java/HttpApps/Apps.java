package HttpApps;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;


public class Apps {

    private static String url = "https://www.baidu.com";

    public static void main(String[] args) {
        // Create an instance of HttpClient.
        HttpClient client = new HttpClient();


        HostConfiguration config = client.getHostConfiguration();
        config.setProxy("192.168.1.230",3228);
        client.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_0);
        // Create a method instance.
        PostMethod method = new PostMethod(url);
        NameValuePair[] data = {
                new NameValuePair("user", "joe"),
                new NameValuePair("password", "bloggs")
        };
        method.setRequestBody(data);
        // Provide custom retry handler is necessary
        //method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

        try {
            // Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = method.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            System.out.println(new String(responseBody));

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Release the connection.
            method.releaseConnection();
        }
    }
}
