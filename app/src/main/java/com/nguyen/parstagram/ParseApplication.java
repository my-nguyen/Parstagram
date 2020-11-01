package com.nguyen.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // register Parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ubVwuw251AGEcXVNx1APboUQorGqVOoBdramVu08")
                .clientKey("eWxXIlSqzeyi983gJCTfwde2HGxgvYmH9zo4wgvh")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
