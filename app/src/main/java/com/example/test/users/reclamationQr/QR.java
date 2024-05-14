package com.example.test.users.reclamationQr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.test.R;

public class QR  extends AppCompatActivity {
    private EditText contentEditText;
    private String defaultEmail = "ons.elyahmadi@fsb.ucar.tn";
    private static final int BARCODE_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        contentEditText = findViewById(R.id.contentEditText);
        Button sendButton = findViewById(R.id.sendButton);
        Button scanButton = findViewById(R.id.scanButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(defaultEmail);
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBarcodeScanner();
            }
        });
    }

    private void startBarcodeScanner() {
        // Lancez l'activité de scanner de codes-barres
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");


        startActivityForResult(intent, BARCODE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == BARCODE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String scannedContent = data.getStringExtra("SCAN_RESULT");
                // Mettez le contenu du code-barres scanné dans le champ de texte
                contentEditText.setText(scannedContent);
            }
        }
    }

    private void sendEmail(String recipient) {
        String subject = "Réclamation d'un nouveau produit à boycotter";
        String content = contentEditText.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        startActivity(Intent.createChooser(intent, "Envoyer via :"));
    }
}