package net.skhu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Exam2Activity extends AppCompatActivity implements ValueEventListener {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam2);
        textView = findViewById(R.id.textView);

        DatabaseReference item04 = FirebaseDatabase.getInstance().getReference("item04");
        item04.addValueEventListener(this);

        EditText editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.okbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = textView.getText().toString();
                String s2 = editText.getText().toString();
                item04.setValue(s1 + "\r\n" + s2);
                editText.setText("");
            }
        });
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String value = dataSnapshot.getValue(String.class);
        textView.setText(value);
        Log.d("내태그", "받은 데이터: " + value);
    }

    @Override
    public void onCancelled(DatabaseError error) {
        Log.e("내태그", "서버 에러: ", error.toException());
    }
}

