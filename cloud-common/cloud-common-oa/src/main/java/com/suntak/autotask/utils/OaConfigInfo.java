package com.suntak.autotask.utils;

public class OaConfigInfo {

    private String url;
    private String username;
    private String password;
    private String restuser;
    private String restpass;

    public OaConfigInfo(String env) {

        // 正式环境地址
        if ("prod".equalsIgnoreCase(env)) {
            setUrl("http://10.1.100.170");
            setUsername("service-admin");
            setPassword("mustchange");
            setRestuser("oasupport");
            setRestpass("oasupport");
        }
        // 测试环境地址
        else {
            setUrl("http://10.1.100.158:5678");
            setUsername("service-admin");
            setPassword("mustchange");
            setRestuser("oasupport");
            setRestpass("oasupport");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRestuser() {
        return restuser;
    }

    public void setRestuser(String restuser) {
        this.restuser = restuser;
    }

    public String getRestpass() {
        return restpass;
    }

    public void setRestpass(String restpass) {
        this.restpass = restpass;
    }

}
