package application.example.bloodpost;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.BloodViewHolder>{
    public ArrayList<Person> data;
    public BloodAdapter(ArrayList<Person> l){
        data=l;
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
        holder.name.setText("Name: "+data.get(position).getName());
        holder.email.setText("Email: "+data.get(position).getEmail());
        holder.phone.setText("Phone: "+data.get(position).getPhone());
        holder.ad.setText("Address: "+data.get(position).getAddress());
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
            name=itemView.findViewById(R.id.Name);
            email=itemView.findViewById(R.id.Email);
            phone=itemView.findViewById(R.id.Phone);
            ad=itemView.findViewById(R.id.Address);
        }
    }
}

