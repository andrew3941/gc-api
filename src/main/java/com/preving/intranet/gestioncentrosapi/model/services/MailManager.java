package com.preving.intranet.gestioncentrosapi.model.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.preving.intranet.gestioncentrosapi.model.domain.EmailRaw;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Service
public class MailManager implements MailService {
    @Value(value = "${api-comunicacion.url}")
    String communicationsAPIURL = "";

    @Value(value = "${api-comunicacion.usuario}")
    String username = "";

    @Value(value = "${api-comunicacion.password}")
    String password = "";

    Gson gson = new Gson();
    HttpClient httpClient = HttpClients.createDefault();

    @Override
    public boolean sendMail(String sendTo, Map<String,Object> emailData) {
        System.out.println(communicationsAPIURL);
        String url = communicationsAPIURL + "/email";
        String token = getToken();
        HttpPost post = new HttpPost(url);
        String[] to ={sendTo};
        String[] cc = {};
        String[] bcc = {};

        //todo cambiar email
        EmailRaw datos = new EmailRaw(to,"jorge.perez@preving.com",cc,bcc,emailData);

        try {
            StringEntity datosJson = new StringEntity((gson.toJson(datos))); //Converts data to JSON
            post.setEntity(datosJson);
            post.setHeader("Authorization", "Bearer "+ token);
            post.setHeader("Content-type","application/json");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response =  httpClient.execute(post);
            System.out.println(response.toString());
            System.out.println(response.getEntity().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  true;
    }

    @Override
    public String getToken() {
        try {
            String url = communicationsAPIURL + "/auth/login";
            String input = "{\"username\": \"" + username + "\",\"password\":\"" + password + "\"}";
            HttpPost post = new HttpPost(url);

            StringEntity auth = new StringEntity(input);
            post.setEntity(auth);
            post.setHeader("Content-type","application/json");

            HttpResponse response = httpClient.execute(post);
            JSONObject datos = new JSONObject(EntityUtils.toString(response.getEntity()));

            return datos.get("token").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}