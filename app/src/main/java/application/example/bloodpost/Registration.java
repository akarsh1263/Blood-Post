package application.example.bloodpost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    FirebaseDatabase fd;
    DatabaseReference people;
    Person p;
    RadioButton rb;
    boolean b=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fd=FirebaseDatabase.getInstance();
        people=fd.getReference().child("people");
    }
    public void search(View v){
        p=new Person();
        EditText name=findViewById(R.id.name);
        p.setName(name.getText().toString());
        RadioGroup rg=findViewById(R.id.groups);
        int sel=rg.getCheckedRadioButtonId();
        if(sel!=-1)
            rb=findViewById(sel);
        p.setBp(rb.getText().toString());
        EditText mail=findViewById(R.id.mail);
        p.setEmail(mail.getText().toString());
        EditText phone=findViewById(R.id.phone);
        p.setPhone(phone.getText().toString());
        EditText add=findViewById(R.id.add);
        p.setAddress(add.getText().toString());
        DatabaseReference qref=FirebaseDatabase.getInstance().getReference("people");

        if(p.getAddress().length()==0||sel==-1||p.getEmail().length()==0||p.getName().length()==0||p.getPhone().length()==0)
            Toast.makeText(this,"fill up the information", Toast.LENGTH_LONG).show();
        else{
            Query check=qref.orderByChild("email").equalTo(mail.getText().toString());
            check.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()) {
                        b = false;
                        Toast.makeText(Registration.this,"This email id already exists", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            if(b) {
                people.push().setValue(p);
                Intent i = new Intent(this, Records.class);
                i.putExtra("bp", rb.getText().toString());
                i.putExtra("mailid", mail.getText().toString());
                startActivity(i);
            }
        }
    }
}