package com.rylabs.stickerfy;

import android.os.Bundle;
import android.view.View;

import com.firebase.client.Firebase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class FeedbackActivity extends BaseActivity {
    private TextInputEditText name, email, subject, message;
    private ExtendedFloatingActionButton fab;
    private Firebase feedback;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.feedback);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        name = findViewById(R.id.namedata);
        email = findViewById(R.id.emaildata);
        subject = findViewById(R.id.subjectdata);
        message = findViewById(R.id.messagedata);

        fab = findViewById(R.id.btn_send);
        Firebase.setAndroidContext(this);

        feedback = new Firebase("https://stickerfy-a1fbe.firebaseio.com");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String NameInput = name.getText().toString();
                final String EmailInput = email.getText().toString();
                final String SubjectInput = subject.getText().toString();
                final String MessageInput = message.getText().toString();

                Firebase child_name = feedback.child("Name");
                child_name.setValue(NameInput);
                if (NameInput.isEmpty()) {
                    name.setError(getString(R.string.feedback_error));
                    fab.setEnabled(false);
                } else {
                    name.setError(null);
                    fab.setEnabled(true);
                }

                Firebase child_email = feedback.child("Email");
                child_email.setValue(EmailInput);
                if (EmailInput.isEmpty()) {
                    email.setError(getString(R.string.feedback_error));
                    fab.setEnabled(false);
                } else {
                    email.setError(null);
                    fab.setEnabled(true);
                }

                Firebase child_subject = feedback.child("Subject");
                child_subject.setValue(SubjectInput);
                if (SubjectInput.isEmpty()) {
                    subject.setError(getString(R.string.feedback_error));
                    fab.setEnabled(false);
                } else {
                    subject.setError(null);
                    fab.setEnabled(true);
                }

                Firebase child_message = feedback.child("Message");
                child_message.setValue(MessageInput);
                if (MessageInput.isEmpty()) {
                    message.setError(getString(R.string.feedback_error));
                    fab.setEnabled(false);
                } else {
                    message.setError(null);
                    fab.setEnabled(true);
                }
                Snackbar.make(view, getString(R.string.feedback_toast), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
