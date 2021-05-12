package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView userImage, userImageBig;
    TextView name, description;

    public UserViewHolder(View itemView) {
        super(itemView);
        this.userImage = itemView.findViewById(R.id.img_profile);
        this.userImageBig = itemView.findViewById(R.id.img_profile_big);
        this.name = itemView.findViewById(R.id.name);
        this.description = itemView.findViewById(R.id.description);
    }
}
