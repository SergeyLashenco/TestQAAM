package tests.apiTests;

import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginApiTest {
    String url = "http://localhost/mantisbt-2.23.0/login.php";

    @DataProvider
    public Object[][] loginDataProvider() {
        return new Object[][]{
                {"administrator", "root", "on", "index.php", url}

        };
    }

    @Test(dataProvider = "loginDataProvider")
    public void testParseLocale(String username, String password, String secure_session, String returned, String url) throws IOException {
        HttpPost post = new HttpPost(url);
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("username", username));
        urlParameters.add(new BasicNameValuePair("password", password));
        urlParameters.add(new BasicNameValuePair("return", returned));
        urlParameters.add(new BasicNameValuePair("secure_session", secure_session));
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(post);
        StatusLine status = response.getStatusLine();
        int statusCode = status.getStatusCode();

        Assert.assertEquals(statusCode, 302);


    }


}
