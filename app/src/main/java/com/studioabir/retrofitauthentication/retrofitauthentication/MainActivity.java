package com.studioabir.retrofitauthentication.retrofitauthentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFromActivityListener,WelcomeFragment.OnLogoutListener {


    public static  PrefConfig prefConfig;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefConfig =new PrefConfig(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragmentcontainer) != null)
        {
            if (savedInstanceState!=null)
            {
                return;
            }

            //-----check if user already in login mode-----///
            if (prefConfig.ReadLoginStatus())
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, new WelcomeFragment()).commit();

            }

            //-----check if user not already in login mode to to login page-----///
            else
            {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentcontainer, new LoginFragment()).commit();

            }


        }


    }

    @Override
    public void performRegister() {

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new RegistrationFragment()).addToBackStack(null).commit();

    }

    @Override
    public void performLogin(String name) {

        prefConfig.writeName(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new WelcomeFragment()).commit();


    }

    @Override
    public void LogoutPerform() {

        prefConfig.writeLoginStatus(false);
        prefConfig.writeName("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new LoginFragment()).commit();


    }
}
