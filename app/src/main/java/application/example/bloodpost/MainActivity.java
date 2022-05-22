package application.example.bloodpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fd=FirebaseDatabase.getInstance();
        people=fd.getReference().child("people");
    }
    public void register(View view){
        Intent intent=new Intent(this,Registration.class);
        startActivity(intent);
    }
    public void search(View view){
        EditText et=findViewById(R.id.email);
        String mail=et.getText().toString().trim();
        if(mail.length()==0)
            Toast.makeText(this,"email empty", Toast.LENGTH_LONG).show();
        else{
            DatabaseReference qref=FirebaseDatabase.getInstance().getReference("people");
            Query check=qref.orderByChild("email").equalTo(mail);
            check.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.exists()) {
                        Toast.makeText(MainActivity.this,"Email not registered", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent i=new Intent(MainActivity.this,Records.class);
                        i.putExtra("mailid",mail);
                        qref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    Person p=dataSnapshot.getValue(Person.class);
                                    if(p.getEmail().equals(mail)){
                                        i.putExtra("bp",p.getBp());
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        startActivity(i);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
    }
}