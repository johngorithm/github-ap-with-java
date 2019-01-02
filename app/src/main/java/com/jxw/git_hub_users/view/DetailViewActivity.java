package com.jxw.git_hub_users.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.jxw.git_hub_users.R;
import com.jxw.git_hub_users.model.UserProfile;
import com.jxw.git_hub_users.presenter.UserProfilePresenter;
import com.jxw.git_hub_users.utils.NetworkUtility;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailViewActivity extends AppCompatActivity implements UserDetailParentView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "DetailViewActivity";
    public static final String USER_INFO_KEY = "userData";
    public String userName;
    public UserProfile ghUserInfo;
    ProgressDialog progressDialog;
    Intent incomingIntent;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeRefreshLayout = findViewById(R.id.detail_view_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent
        );

        incomingIntent = getIntent();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        if (savedInstanceState != null) {
            this.ghUserInfo = savedInstanceState.getParcelable(USER_INFO_KEY);
            if (this.ghUserInfo != null) {
                this.displayUserProfile(this.ghUserInfo);
            } else {
                loadProfile(incomingIntent);
            }
        } else {
            loadProfile(incomingIntent);
        }

        Log.i(TAG, "ON-CREATE IS CALLED");


    }

    public class ClickToRefresh implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            loadProfile(incomingIntent);
        }
    }


    public void loadProfile(Intent incomingIntent) {
        if (NetworkUtility.isConnected(this)) {
            fetchData(incomingIntent);
        } else {
            progressDialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
            showSnackBar();
        }
    }

    public void showSnackBar() {
        Snackbar networkSnackBar = Snackbar.make(findViewById(R.id.user_detail_view), R.string.network_failure_message, Snackbar.LENGTH_LONG);
        networkSnackBar.setAction(R.string.click_to_refresh, new ClickToRefresh());
        networkSnackBar.show();
    }


    private void fetchData(Intent newIntent) {
        // HANDLING INCOMING INTENT
        if (newIntent.hasExtra("userName")) {
            String userName = newIntent.getStringExtra("userName");

            UserProfilePresenter profilePresenter = new UserProfilePresenter(this);
            profilePresenter.getUserProfile(userName);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Invaid Username ID, Please try again", Toast.LENGTH_LONG);
            toast.show();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(USER_INFO_KEY, this.ghUserInfo);
        Log.i(TAG, "ON-SAVE-INSTANCE-STATE IS CALLED");
    }

    @Override
    public void handleError() {
        progressDialog.dismiss();
        Toast.makeText(this, "Something Went Wrong. Please, Try Again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayUserProfile(UserProfile userProfile) {
        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);

        this.ghUserInfo = userProfile;
        this.userName = userProfile.getUserName();

        CircleImageView userImageElement = findViewById(R.id.user_image);
        TextView followingCountElement = findViewById(R.id.following_count);
        TextView followersCountElement = findViewById(R.id.followers_count);
        TextView userNameElement = findViewById(R.id.username_value);
        TextView companyNameElement = findViewById(R.id.company_name);
        TextView bioTextElement = findViewById(R.id.bio_text);
        TextView publicReposElement = findViewById(R.id.public_repo_count);

        Glide.with(this).asBitmap()
                .load(userProfile.getImageUrl())
                .into(userImageElement);
        followingCountElement.setText(userProfile.getFollowing());
        followersCountElement.setText(userProfile.getFollowers());
        userNameElement.setText(userProfile.getUserName());
        this.handleCompanyData(companyNameElement);
        this.handleBioData(bioTextElement);
        publicReposElement.setText(userProfile.getPublicRepos());
    }

    public void handleCompanyData(TextView companyNameElement) {
        if (ghUserInfo.getCompany() == null) {
            companyNameElement.setText(getString(R.string.company_default_text));
        } else {
            companyNameElement.setText(ghUserInfo.getCompany());
        }
    }

    public void handleBioData(TextView bioTextElement) {
        if (ghUserInfo.getCompany() == null) {
            bioTextElement.setText(getString(R.string.bio_default_text));
        } else {
            bioTextElement.setText(ghUserInfo.getBio());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share_icon) {
            if (this.userName != null) {
                String text = "Checkout this awesome developer @" + this.userName + "\nhttps://github.com/"+this.userName;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(intent, "Share via"));
            } else {
                Toast.makeText(this, "Shareable user data not found", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sharemenu, menu);
        Log.d(TAG, "ON-CREATE-OPTION-MENU-WAS-CALLED");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRefresh() {
        loadProfile(incomingIntent);
    }
}
