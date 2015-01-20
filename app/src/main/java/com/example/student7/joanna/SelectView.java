package com.example.student7.joanna;

/**
 * Created by student7 on 2015-01-20.
 */
public class SelectView {
}
@Extra
Recipe recipe;
@Extra
User user;

@Extra
Bundle bundle;


//process dialog
ProgressDialog ringProgressDialog;

//region background tasks
@Bean
@NonConfigurationInstance
RestBackgroundComment restBackgroundComment;

@Bean
@NonConfigurationInstance
RestBackgroundLike restBackgroundLike;
    //endregion


//buttons
@ViewById
Button comment;
@ViewById
Button like;

//region TextVievs
@ViewById
TextView title;
@ViewById
TextView introduction;
@ViewById
TextView ingredients;
@ViewById
TextView created;
@ViewById
TextView preparationMinutes;
@ViewById
TextView cookingMinutes;
@ViewById
TextView servings;
@ViewById
TextView likes;
//endregion

    @AfterViews
    void init() {
        listComment.setAdapter(adapter);

        //unpack bundle
        recipe = (Recipe)bundle.getSerializable("recipe");
        user = (User)bundle.getSerializable("user");

        //process dialog
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);

        //for loading comments
        ringProgressDialog.show();

        //set textviews
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
        ingredients.setText(recipe.ingredients);
        created.setText(recipe.created);
        preparationMinutes.setText(recipe.preparationMinutes);
        cookingMinutes.setText(recipe.cookingMinutes);
        servings.setText(recipe.servings);


        //fill comments and likes
        restBackgroundComment.getComment("recipeId=" + Integer.toString(recipe.id));
        restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));
    }

    @Click
    void likeClicked(){
        //check if user is logged in
        if(user == null) {
            LoginActivity_.intent(this).user(user).start();
        } else {
            ringProgressDialog.show();
            //add like to the recipe
            restBackgroundLike.setLike(user, recipe);
            //refresh like's
            restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));
        }
    }
