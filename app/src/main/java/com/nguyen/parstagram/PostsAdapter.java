package com.nguyen.parstagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nguyen.parstagram.databinding.ItemPostBinding;
import com.parse.ParseFile;

import java.util.List;

class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    Context context;
    List<Post> posts;

    PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemPostBinding binding = ItemPostBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemPostBinding binding;

        ViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Post post) {
            binding.description.setText(post.getDescription());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(binding.image);
            }
            binding.username.setText(post.getUser().getUsername());
        }
    }
}
