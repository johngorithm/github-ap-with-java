package com.jxw.git_hub_users;

import android.os.Bundle;
import android.support.test.runner.AndroidJUnitRunner;
import com.linkedin.android.testbutler.TestButler;

public class CustomTestRunner extends AndroidJUnitRunner {

    @Override
    public void onStart() {
        TestButler.setup(getTargetContext());
        super.onStart();
    }

    @Override
    public void finish(int resultCode, Bundle results) {
        TestButler.teardown(getTargetContext());
        super.finish(resultCode, results);
    }
}