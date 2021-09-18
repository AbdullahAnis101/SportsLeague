package com.example.sportsleaguev2.ui.message;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.example.sportsleaguev2.R;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class MessageFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    Button send, connect, disconnect;
    EditText convo;
    TextView mOutput;

    WebSocketClient mWebSocketClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message, container, false);

        send = (Button) root.findViewById(R.id.btn_send);
        connect = (Button) root.findViewById(R.id.btn_connect);
        disconnect = (Button) root.findViewById(R.id.btn_disconnect);
        convo = (EditText) root.findViewById(R.id.InsertMessage);

        mOutput = root.findViewById(R.id.m_output);
        // Add scrolling
        mOutput.setMovementMethod(new ScrollingMovementMethod());

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectWebSocket();
            }
        });
        disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebSocketClient.close();
                mOutput.setText("");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the message from the input
                String message = convo.getText().toString();

                // If the message is not empty, send the message
                if(message != null && message.length() > 0){
                    mWebSocketClient.send(message);
                }
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}