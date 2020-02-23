package appmanager;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpSession {

    /**
     * В класе есть Конструктор который создает новый обект и помещен в поле httpclient
     * 1 Медот - login () -- выполняет логин.
     * 2 Метод isLoggedInAs () -- Проверяет какой пользователь сейчас залогинен.
     */


    private CloseableHttpClient httpclient;
    private  ApplicationManager app;

    public HttpSession (ApplicationManager app){
        this.app=app;
        /*
        Создается новая сессия для работы по протоколу Http (обект который будет отправлять запросы на сервер)
        setRedirectStrategy - Стратегия перенаправлений (если её не установить вообще  то при первом же запросе Получем Ответ 302 - Перенаправление на другую страницу и потом  )
        самому нужно будет как то это перенаправления обрабатовать. Что бы сделать так что бы Http клиент автоматом перенаправлял эму нужно установить -new LaxRedirectStrategy()
        */

        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login (String username , String password) throws IOException {
        //Выполннения Логин - отправка post запроса по указанному  адресу
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        //HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login_page.php");
        // В строчках кода 47-51  формируется набор параметров.
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username",username));
        params.add(new BasicNameValuePair("password",password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        // в строчке 53 Параметры упаковываются
        post.setEntity(new UrlEncodedFormEntity(params));
        //httpclient.execute(post) Происходит отправка запроса ст.55
        // Результат записывается в response
        CloseableHttpResponse response = httpclient.execute(post);
        String body = getTextFrom(response);
        //ст.59 Проверяется успешно ли Пользователь зашел , действительно ли код страници содержит строчку указанную в String.format.
        return  body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        }finally {
            response.close();
        }
    }

    public boolean isLoggedInAs (String username ) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = getTextFrom(response);
        return  body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username));
    }
}
