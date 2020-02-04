package com.rylabs.stickerfy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ShareCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.appbar.MaterialToolbar;

public class AboutActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout share, license, github, telegram;
    String version;
    TextView versionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        try {
            PackageInfo packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), PackageManager.GET_ACTIVITIES);
            version = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionText = (TextView) findViewById(R.id.app_version);
        versionText.setText(version);

        share = (LinearLayout) findViewById(R.id.share);
        license = (LinearLayout) findViewById(R.id.license);
        github = (LinearLayout) findViewById(R.id.github);
        telegram = (LinearLayout) findViewById(R.id.telegram);

        share.setOnClickListener(this);
        license.setOnClickListener(this);
        github.setOnClickListener(this);
        telegram.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.share:
                ShareCompat.IntentBuilder.from(this)
                    .setType("text/plain")
                    .setChooserTitle(R.string.share_app)
                    .setText(String.format(getString(R.string.app_share)))
                    .startChooser();
                break;

            case R.id.license:
                new MaterialAlertDialogBuilder(AboutActivity.this)
                        .setTitle(R.string.license)
                        .setMessage(R.string.license_text)
                        .setPositiveButton(R.string.changelog_ok_button, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                break;

            case R.id.github:
                Intent github = new Intent();
                github.setAction(Intent.ACTION_VIEW);
                github.setData(Uri.parse("https://github.com/XRzky/Sticker.fy"));
                startActivity(github);
                break;

            case R.id.telegram:
                Intent telegram = new Intent();
                telegram.setAction(Intent.ACTION_VIEW);
                telegram.setData(Uri.parse("http://t.me/RygentRepo"));
                startActivity(telegram);
                break;

        }
    }

}

