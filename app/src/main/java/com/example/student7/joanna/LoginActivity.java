package com.example.student7.joanna;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ProgressDialog;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity {

        @Bean
        @NonConfigurationInstance
        RestLoginBackgroundTask restLoginBackgroundTask;

        @ViewById
        EditText email;
        @ViewById
        EditText password;

        ProgressDialog ringProgressDialog;

        //Bundle
//    @Extra
//    Bundle bundle;
//    @Extra
//    Recipe recipe;
        @Extra
        User user;

        @AfterViews
        void init() {
            ringProgressDialog = new ProgressDialog(this);
            ringProgressDialog.setMessage("Ładowanie...");
            ringProgressDialog.setIndeterminate(true);
        }

        @Click
        void loginClicked(){
            ringProgressDialog.show();
            EmailAndPassword emailAndPassword = new EmailAndPassword();
            emailAndPassword.email = email.getText().toString(); //example@example.com
            emailAndPassword.password = password.getText().toString(); //test00
            restLoginBackgroundTask.login(emailAndPassword);
        }

        public void loginSuccess(User user){
            ringProgressDialog.dismiss();
            MainView_.intent(this).user(user).start();
            Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show();
        }

        public void showError(Exception e){
            ringProgressDialog.dismiss();
            Toast.makeText(this, "Niestety, błąd\n" + e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
