package com.nguyen.parstagram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.nguyen.parstagram.databinding.FragmentPostsBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {
    public static final String TAG = "PostsFragment";

    FragmentPostsBinding binding;
    PostsAdapter adapter;
    List<Post> posts;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_posts, container, false);
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        posts = new ArrayList<>();
        adapter = new PostsAdapter(getContext(), posts);
        binding.posts.setAdapter(adapter);
        binding.posts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        // add User (the post author) as additional details in the post
        query.include(Post.KEY_USER);
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