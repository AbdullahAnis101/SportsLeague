package com.example.sportsleaguev2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.sportsleaguev2.HomeActivity;
import com.example.sportsleaguev2.R;

import com.example.sportsleaguev2.Model.RequestServerForService;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;

import java.net.URI;
import java.net.URISyntaxException;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private View view;
    TextView serverResponse, mOutput;

    WebSocketClient mWebSocketClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        if(getArguments() != null) {
////            Log.d("USErTest", getArguments().toString());
////            Log.d("UUUUU", getActivity().getIntent().getExtras().getString("name"));
//        } else {
//            Log.d("NOPE", "Null");
//        }
        serverResponse = root.findViewById(R.id.serverResponse2);
        serverResponse.setText(getActivity().getIntent().getExtras().getString("Games"));
        final TextView welcome = root.findViewById(R.id.welcome_mes);
        final TextView first_name = root.findViewById(R.id.first_name);
        final TextView last_name = root.findViewById(R.id.last_name);
        final TextView phone_number = root.findViewById(R.id.phone_number);
        final TextView email = root.findViewById(R.id.email);
        final TextView rank = root.findViewById(R.id.rank);

        mOutput = root.findViewById(R.id.textView4);
        // Add scrolling
        mOutput.setMovementMethod(new ScrollingMovementMethod());

        connectWebSocket();

        Log.d("USER", getActivity().getIntent().getExtras().toString());

        welcome.setText("Welcome " + getActivity().getIntent().getExtras().getString("first_name") + "!");

        if(getActivity().getIntent().getExtras().getString("first_name") !=  null) {
            first_name.setText(getActivity().getIntent().getExtras().getString("first_name"));
        }
        if(getActivity().getIntent().getExtras().getString("last_name") != null) {
            last_name.setText(getActivity().getIntent().getExtras().getString("last_name"));
        }
        if(getActivity().getIntent().getExtras().getString("phone_number") != null) {
            phone_number.setText(getActivity().getIntent().getExtras().getString("phone_number"));
        }
        if(getActivity().getIntent().getExtras().getString("email") != null) {
            email.setText(getActivity().getIntent().getExtras().getString("email"));
        }
        if(getActivity().getIntent().getExtras().getString("rank") != null) {
            rank.setText(getActivity().getIntent().getExtras().getString("rank"));
        }

        Button logoutButton = (Button) root.findViewById(R.id.btn_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.loginActivity);
            }
        });
        return root;
    }

    public void connectWebSocket() {
        String address = "ws://coms-309-kk-09.cs.iastate.edu:8080/websocket/"+getActivity().getIntent().getExtras().getString("email");
        Log.d("address", address);
        URI uri;
        try {
            /*
             * To test the clientside without the backend, simply connect to an echo server such as:
             *  "ws://echo.websocket.org"
             */
            uri = new URI(address); // 10.0.2.2 = localhost
//             uri = new URI("ws://echo.websocket.org");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {

            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String msg) {
                Log.i("Websocket", "Message Received");
                // Appends the message received to the previous messages
                mOutput.append("\n" + msg);
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
                Log.i("Websocket", "Closed " + reason);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

}