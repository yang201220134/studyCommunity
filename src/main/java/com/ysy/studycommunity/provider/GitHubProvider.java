package com.ysy.studycommunity.provider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysy.studycommunity.dto.AccessTokenDTO;
import com.ysy.studycommunity.dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
           String responseFromGit = response.body().string();
           System.out.println(responseFromGit);
            return responseFromGit;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();

        try (Response response = client.newCall(request).execute()) {

            String repstring = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(repstring, GitHubUser.class);

        } catch (IOException e) {
                e.printStackTrace();
            }



        return null;
    }




}
