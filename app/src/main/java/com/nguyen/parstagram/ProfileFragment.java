package com.nguyen.parstagram;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // add User (the post author) as additional details in the post
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        // limit to 20 posts only
        query.setLimit(20);
        // sort by most recent post first
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting posts", e);
                } else {
                    for (Post post : items) {
                        Log.d(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    }
                    posts.addAll(items);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
