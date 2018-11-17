package com.studioabir.retrofitauthentication.retrofitauthentication;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class WelcomeFragment extends Fragment {


    public WelcomeFragment() {
        // Required empty public constructor
    }

    private TextView textView;
    private Button BnLogout;

    //-----handler Methord for Logout-----//
    OnLogoutListener logoutListener;

    ///-----interface method for logout---//
    public interface OnLogoutListener
    {
        public void LogoutPerform();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        textView = view.findViewById(R.id.fetch_username);
        BnLogout = view.findViewById(R.id.button_logout);
        textView.setText("WELCOME "+MainActivity.prefConfig.readName());

        BnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutListener.LogoutPerform();

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener =  (OnLogoutListener) activity;

    }
}
