package com.jxw.git_hub_users.view;

import com.jxw.git_hub_users.model.UserProfile;

public interface UserDetailParentView {
    void displayUserProfile(UserProfile userProfile);
    void handleError();
}
