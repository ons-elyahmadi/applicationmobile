package com.example.test.users.reclamationtel;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;

public class SMSActivity extends AppCompatActivity {
    private EditText contentEditText;
    private String defaultPhoneNumber = "56206103"; // Numéro de téléphone par défaut

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsactivity);

        contentEditText = findViewById(R.id.contentEditText);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS(defaultPhoneNumber);
            }
        });
    }

    private void sendSMS(String phoneNumber) {
        String content = "Réclamation d'un nouveau produit à boycotter: " + contentEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", content);
        startActivity(intent);
    }
}
