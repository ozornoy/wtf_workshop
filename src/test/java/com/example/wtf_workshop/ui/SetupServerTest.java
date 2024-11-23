package com.example.wtf_workshop.ui;

import com.example.wtf_workshop.ui.pages.setup.FirstStartPage;
import org.testng.annotations.Test;

public class SetupServerTest extends BaseUiTest{
    @Test(groups = {"Setup"})
    public void setupTeamcityServerTest() {
        FirstStartPage.open().setupFirstStart();
    }
}
