package com.jxw.git_hub_users.view;

import com.jxw.git_hub_users.model.GithubUsers;
import java.util.List;

public interface UsersParentView {
    void displayUsers(List<GithubUsers> usersList);
    void showErrorMessage(String message);
}
