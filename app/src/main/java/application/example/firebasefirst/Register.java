package application.example.firebasefirst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {
    EditText email;
    EditText password;
    Button register2;
    DatabaseReference database;
    ListView listView;
    List<Artist> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent i= getIntent();
        password=(EditText)findViewById(R.id.password);
        email=(EditText)findViewById(R.id.mail);
        database= FirebaseDatabase.getInstance().getReference("artist");
        listView=findViewById(R.id.listView);
        arrayList=new ArrayList<>();
        listView.setVisibility(View.INVISIBLE);
    }

    public void firebaseRegister(View view) {
        register2=(Button)findViewById(R.id.register2);
        if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) )
            Toast.makeText(this, "Enter the details", Toast.LENGTH_SHORT).show();
        else {
            String id = database.push().getKey();
            Artist ob = new Artist(id,email.getText().toString(), password.getText().toString());
            database.child(id).setValue(ob);
            listView.setVisibility(View.INVISIBLE);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot artistSnapshot:dataSnapshot.getChildren())
                {
                    Artist artist= artistSnapshot.getValue(Artist.class);
                    arrayList.add(artist);
                }
                login_list adapter=new login_list(Register.this, arrayList);//Custom adapter class created by the name of login_list is used
                listView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void button3(View view)
    {
        Button button3=(Button) findViewById(R.id.button3);
        listView.setVisibility(View.VISIBLE);
    }
}
