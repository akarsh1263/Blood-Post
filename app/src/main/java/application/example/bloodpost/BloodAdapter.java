package application.example.bloodpost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.BloodViewHolder>{
    public ArrayList<Person> data;
    public BloodAdapter(ArrayList<Person> l){
        this.data=l;
    }
    @NonNull
    @Override
    public BloodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.blood_list,parent,false);
        return new BloodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BloodViewHolder holder, int position) {
        String n=data.get(position).getName();
        String e=data.get(position).getEmail();
        String p=data.get(position).getPhone();
        String a=data.get(position).getAddress();
        holder.name.setText("Name: "+n);
        holder.email.setText("Email: "+e);
        holder.phone.setText("Phone: "+p);
        holder.ad.setText("Address: "+a);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class BloodViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView email;
        TextView phone;
        TextView ad;

        public BloodViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.Name);
            email=(TextView)itemView.findViewById(R.id.Email);
            phone=(TextView)itemView.findViewById(R.id.Phone);
            ad=(TextView)itemView.findViewById(R.id.Address);
        }
    }
}

