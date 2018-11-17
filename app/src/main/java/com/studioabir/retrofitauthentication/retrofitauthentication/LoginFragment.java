package com.studioabir.retrofitauthentication.retrofitauthentication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {


    public LoginFragment() {
        // Required empty public constructor
    }

    private TextView RegText;
    private EditText UserName,UserPassword;
    private Button BnLogin;
    public static ApiInterface apiInterface;



    ///-----Interface Handler methord-----///
    OnLoginFromActivityListener loginFromActivityListener;


    ///-----INTERFACE FOR LOGIN REGISTER MAINTAIN----//
    public interface OnLoginFromActivityListener
    {
        public void performRegister();
        public void performLogin(String name);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        RegText = view.findViewById(R.id.gotoregister);
        UserName = view.findViewById(R.id.user_name);
        UserPassword = view.findViewById(R.id.user_password);
        BnLogin = view.findViewById(R.id.button_login);

        BnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MyLogin();
            }
        });


        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginFromActivityListener.performRegister();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        loginFromActivityListener = (OnLoginFromActivityListener) activity;

    }


    private void MyLogin()
    {
        String username = UserName.getText().toString();
        String userpassword = UserPassword.getText().toString();

        Call<User> call = apiInterface.performUserLogin(username,userpassword);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFromActivityListener.performLogin(response.body().getName());
                }

                if (response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Login Falied..Please Try Again....");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        UserName.setText("");
        UserPassword.setText("");

    }
}
