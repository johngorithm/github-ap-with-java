package com.jxw.git_hub_users.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;

import com.bumptech.glide.Glide;
import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.model.UserProfile;
import com.jxw.git_hub_users.presenter.UserProfilePresenter;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailViewActivity extends AppCompatActivity implements UserDetailParentView {
    private static final String TAG = "DetailViewActivity";
    public static String USER_INFO_KEY = "userData";
    public UserProfile ghUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState != null) {
            this.ghUserInfo = savedInstanceState.getParcelable(USER_INFO_KEY);
            this.displayUserProfile(this.ghUserInfo);
        } else {
            // HANDLING INCOMING INTENT
            Intent incomingIntent = getIntent();
            if (incomingIntent.hasExtra("userName")) {
                String userName = incomingIntent.getStringExtra("userName");

                UserProfilePresenter profilePresenter = new UserProfilePresenter(this);
                profilePresenter.getUserProfile(userName);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Invaid Username ID, Please try again", Toast.LENGTH_LONG);
                toast.show();
            }
        }

        Log.i(TAG, "ON-CREATE IS CALLED");


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(USER_INFO_KEY, this.ghUserInfo);
        Log.i(TAG, "ON-SAVE-INSTANCE-STATE IS CALLED");
    }

    @Override
    public void displayUserProfile(UserProfile userProfile) {
        ghUserInfo = userProfile;

        CircleImageView userImageElement = findViewById(R.id.user_image);
        TextView followingCountElement = findViewById(R.id.following_count);
        TextView followersCountElement = findViewById(R.id.followers_count);
        TextView userNameElement = findViewById(R.id.username_value);
        TextView companyNameElement = findViewById(R.id.company_name);
        TextView bioTextElement = findViewById(R.id.bio_text);
        TextView publicReposElement = findViewById(R.id.public_repo_count);

        Glide.with(this)
                .asBitmap()
                .load(userProfile.getImageUrl())
                .into(userImageElement);
        followingCountElement.setText(userProfile.getFollowing());
        followersCountElement.setText(userProfile.getFollowers());
        userNameElement.setText(userProfile.getUserName());

        if (userProfile.getCompany() == null) {
            companyNameElement.setText(getString(R.string.company_default_text));
        } else {
            companyNameElement.setText(userProfile.getCompany());
        }

        if (userProfile.getBio() == null) {
            bioTextElement.setText(getString(R.string.bio_default_text));
        } else {
            bioTextElement.setText(userProfile.getBio());
        }
        publicReposElement.setText(userProfile.getPublicRepos());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
