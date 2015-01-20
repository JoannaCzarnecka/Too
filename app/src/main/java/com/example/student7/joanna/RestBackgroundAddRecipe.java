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
public class RestBackgroundAddRecipe {
    @RootContext
    AddRecipeView activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background withoud blocking main application ;-)
    @Background
    void addRecipe(Recipe recipe, User user){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);

            //recipe is filled in AddRecipeView so it only requires to pass it to restClient.addRecipe
            restClient.addRecipe(recipe);
        } catch(Exception e){
            publishError(e);
        }

    }

    @UiThread
    void publishSuccess(){
        activity.showSuccess();
    }

    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}
