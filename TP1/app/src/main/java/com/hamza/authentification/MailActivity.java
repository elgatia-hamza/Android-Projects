package com.hamza.authentification;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.hamza.authentification.databinding.ActivityMailBinding;

public class MailActivity extends AppCompatActivity {
    ActivityMailBinding binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder= DataBindingUtil.setContentView(this,R.layout.activity_mail);
    }
    

    public void sendmail(View view) {
        if(!Patterns.EMAIL_ADDRESS.matcher(binder.editTextMail.getText()).matches()){
            binder.editTextMail.setError("Not a Valid Email Format !");
            return;
        }
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{binder.editTextMail.getText().toString()});
            i.putExtra(Intent.EXTRA_SUBJECT, binder.objMail.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT   , binder.bodyMailText.getText());
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
}
