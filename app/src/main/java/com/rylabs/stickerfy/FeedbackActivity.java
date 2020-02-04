package com.rylabs.stickerfy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class FeedbackActivity extends BaseActivity {
    private TextInputEditText email, subject, message;
    ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        email = findViewById(R.id.emaildata);
        subject = findViewById(R.id.subjectdata);
        message = findViewById(R.id.messagedata);
        fab = findViewById(R.id.btn_send);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SubjectFeedback = subject.getText().toString();
                String MessageFeeedback = message.getText().toString();
                String EmailFeedback = email.getText().toString();

                Intent send = new Intent(Intent.ACTION_SEND);
                send.putExtra(Intent.EXTRA_EMAIL, EmailFeedback);
                send.putExtra(Intent.EXTRA_SUBJECT, SubjectFeedback);
                send.putExtra(Intent.EXTRA_TEXT, MessageFeeedback);
                send.setType("message/rfc822");
                send.setPackage("com.google.android.gm");
                startActivity(send);
                Snackbar.make(view, getString(R.string.feedback_toast), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
