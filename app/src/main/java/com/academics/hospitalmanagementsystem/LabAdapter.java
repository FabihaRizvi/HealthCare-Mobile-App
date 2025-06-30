package com.academics.hospitalmanagementsystem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class LabAdapter extends BaseAdapter {
    Context context;
    List<Lab> labList;

    public LabAdapter(Context context, List<Lab> labList) {
        this.context = context;
        this.labList = labList;
    }

    @Override
    public int getCount() {
        return labList.size();
    }

    @Override
    public Object getItem(int position) {
        return labList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView labName, labAddress, labContact, labFees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.lab_details_list, parent, false);
            holder = new ViewHolder();
            holder.labName = convertView.findViewById(R.id.labName);
            holder.labAddress = convertView.findViewById(R.id.labLocation);
            holder.labContact = convertView.findViewById(R.id.labContact);
            holder.labFees = convertView.findViewById(R.id.labFee);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Lab lab = labList.get(position);
        holder.labName.setText("Name: " + lab.name);
        holder.labAddress.setText("Location: " + lab.location);
        holder.labContact.setText("Contact: " + lab.contact);
        holder.labFees.setText("Fees: " + lab.fee);

        return convertView;
    }
}

