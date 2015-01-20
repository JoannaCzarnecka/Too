package com.example.student7.joanna;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;
/**
 * Created by student7 on 2015-01-20.
 */
@EBean
public class RestLoginBackgroundTask {
    @RootContext
    LoginActivity activity;
    @RestService
    CookbookRestClient restClient;

    //login in background
    @Background
    void login(EmailAndPassword emailAndPassword){
        try{
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");

            User user = restClient.login(emailAndPassword);
            publishResult(user);
        } catch(Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(User user){
        activity.loginSuccess(user);
    }
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }

}
