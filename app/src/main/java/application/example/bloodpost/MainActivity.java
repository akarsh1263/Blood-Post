package application.example.bloodpost;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void search(){
        EditText et=findViewById(R.id.email);
        String mail=et.getText().toString().trim();
        if(mail.length()==0)
            Toast.makeText(this,"email empty", Toast.LENGTH_LONG).show();
        else{

        }
    }
}