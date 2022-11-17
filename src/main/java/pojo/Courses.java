package pojo;

import java.util.List;

public class Courses {

    private List<WebAutomation> webAutomation;
    private List<Api> api;
    private List<Mobile> mobile;

    public Courses() {
    }

    public List<WebAutomation> getWebAutomation() {
        return this.webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Api> getApi() {
        return this.api;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public List<Mobile> getMobile() {
        return this.mobile;
    }

    public void setMobile(List<Mobile> mobile) {
        this.mobile = mobile;
    }
}
