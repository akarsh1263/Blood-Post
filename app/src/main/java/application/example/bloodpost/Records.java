package application.example.bloodpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Records extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Intent i=getIntent();
        String mail=i.getStringExtra("mailid");
        String bp=i.getStringExtra("bp");
        RecyclerView bloodList=(RecyclerView) findViewById(R.id.bloodlist);
        bloodList.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Person> peeps=new ArrayList<Person>();
        DatabaseReference df= FirebaseDatabase.getInstance().getReference("people");
        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Person p=dataSnapshot.getValue(Person.class);
                    if((!p.getEmail().equals(mail))&&p.getBp().equals(bp)) {
                        peeps.add(p);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bloodList.setAdapter(new BloodAdapter(peeps));
    }
}