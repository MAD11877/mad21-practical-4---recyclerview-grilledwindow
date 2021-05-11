package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Main Activity";
    private User user;
    private boolean initialFollowed;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "Main Activity Created");

        TextView title = findViewById(R.id.title);
        TextView lorem = findViewById(R.id.lorem);
        Button followBtn = findViewById(R.id.follow_btn);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            user = bundle.getParcelable("user");
            initialFollowed = user.isFollowed();
            title.setText(user.name);
            lorem.setText(user.description);
            followBtn.setText(user.isFollowed() ? "Unfollow" : "Follow");
        }
        else {
            user = new User("hi", "hi", 1, false);
        }

        followBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                user.setFollowed(!user.isFollowed());
                followBtn.setText(user.isFollowed() ? "Unfollow" : "Follow");

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(
                        MainActivity.this,
                        !user.isFollowed() ? "Unfollowed" : "Followed",
                        duration
                );
                toast.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "Stop");

        if (user.isFollowed() != initialFollowed) {
            SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("user_id", user.id);
            editor.apply();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "Destroy");
    }
}