package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder>{
    ArrayList<User> data;
    Context mContext;

    public UserAdapter(ArrayList<User> input, Context mContext) {
        this.data = input;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user,
                parent,
                false);

        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = data.get(position);
        Log.v("User", String.format("%d, %b", user.id, user.followed));
        holder.name.setText(user.name);
        holder.description.setText(user.description);

        holder.userImage.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

            builder.setTitle("Profile")
                    .setMessage(user.name)
                    .setNegativeButton("Close", null)
                    .setPositiveButton("View", (dialog, id) -> {
                        // User clicked View button
                        Intent mainIntent = new Intent(mContext, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("user", user);
                        mainIntent.putExtras(bundle);
                        mContext.startActivity(mainIntent);
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
