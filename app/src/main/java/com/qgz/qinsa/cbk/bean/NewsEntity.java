package com.qgz.qinsa.cbk.bean;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by qinsa on 2016/11/14.
 */

public class NewsEntity implements Serializable{
    private int id;
    private String title;
    private String source;
    private String description;
    private String wap_thumb;
    private String create_time;
    private String nickname;

    public NewsEntity(JSONObject jsonObject) {
        this.id = jsonObject.optInt("id");
        this.title = jsonObject.optString("title");
        this.source = jsonObject.optString("source");
        this.description = jsonObject.optString("description");
        this.wap_thumb = jsonObject.optString("wap_thumb");
        this.create_time = jsonObject.optString("create_time");
        this.nickname = jsonObject.optString("nickname");
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

    public String getWap_thumb() {
        return wap_thumb;
    }

    public String getCreate_time() {
        return create_time;
    }

    public String getNickname() {
        return nickname;
    }
}
