package com.studioabir.retrofitauthentication.retrofitauthentication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrationFragment extends Fragment {


    public RegistrationFragment() {
        // Required empty public constructor
    }

    private EditText Name,UserName,UserPassword;
    private Button BnRegister;
    public static ApiInterface apiInterface;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Name = view.findViewById(R.id.name);
        UserName = view.findViewById(R.id.user_name);
        UserPassword = view.findViewById(R.id.user_password);
        BnRegister = view.findViewById(R.id.button_register);

        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myregistration();

            }
        });

        return view;
    }

    public void  myregistration()
    {
        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = UserPassword.getText().toString();

        Call<User> call = apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration Successfully.....");
                }
                else if (response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User Already Exist.....");

                }
                else if (response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Somethings Went Wrong.....");

                }


            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        Name.setText("");
        UserName.setText("");
        UserPassword.setText("");

    }





}
