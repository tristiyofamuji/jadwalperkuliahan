package com.example.jadwalperkuliahan;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ScheduleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Schedule> scheduleList;

    public ScheduleAdapter(Context context, ArrayList<Schedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
    }

    @Override
    public Object getItem(int position) {
        return scheduleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_schedule, parent, false);
            holder = new ViewHolder();
            holder.tvSubject = convertView.findViewById(R.id.tvSubject);
            holder.tvLecturer = convertView.findViewById(R.id.tvLecturer);
            holder.tvTime = convertView.findViewById(R.id.tvTime);
            holder.btnAbsen = convertView.findViewById(R.id.btnAbsen);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Schedule schedule = scheduleList.get(position);

        holder.tvSubject.setText(schedule.getSubject());
        holder.tvLecturer.setText(schedule.getLecturer());
        holder.tvTime.setText(schedule.getTime());

        holder.btnAbsen.setOnClickListener(v -> showAbsensiDialog(schedule));

        return convertView;
    }

    private void showAbsensiDialog(Schedule schedule) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_absen, null);
        builder.setView(dialogView);

        TextView tvDialogTitle = dialogView.findViewById(R.id.tvDialogTitle);
        TextView tvSubject = dialogView.findViewById(R.id.tvSubject);
        TextView tvLecturer = dialogView.findViewById(R.id.tvLecturer);
        TextView tvTime = dialogView.findViewById(R.id.tvTime);
        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        // Set isi pesan dialog untuk setiap TextView
        tvSubject.setText("Mata Kuliah: " + schedule.getSubject());
        tvLecturer.setText("Dosen: " + schedule.getLecturer());
        tvTime.setText("Jadwal: " + schedule.getTime());

        AlertDialog dialog = builder.create();

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(context, "Absen berhasil untuk " + schedule.getSubject(), Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    static class ViewHolder {
        TextView tvSubject;
        TextView tvLecturer;
        TextView tvTime;
        Button btnAbsen;
    }
}
