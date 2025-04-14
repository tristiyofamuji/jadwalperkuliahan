package com.example.jadwalperkuliahan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewSchedule;
    ArrayList<Schedule> scheduleList;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isUserLoggedIn()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_main);

        listViewSchedule = findViewById(R.id.listViewSchedule);
        buttonLogout = findViewById(R.id.buttonLogout);
        scheduleList = new ArrayList<>();

        scheduleList.add(new Schedule("Pemrograman Website", "R. Bagus Bambang Sumantri", "Tanggal: 20/04/2025 | Waktu: 10:00 - 12:00"));
        scheduleList.add(new Schedule("Kalkulus", "Dede Yusuf", "Tanggal: 21/04/2025 | Waktu: 08:00 - 10:00"));
        scheduleList.add(new Schedule("Algoritma & Struktur Data", "Walidy Rahman Hakim", "Tanggal: 22/04/2025 | Waktu: 13:00 - 15:00"));
        scheduleList.add(new Schedule("Basis Data", "Tri Stiyo Famuji", "Tanggal: 23/04/2025 | Waktu: 09:00 - 11:00"));

        ScheduleAdapter adapter = new ScheduleAdapter(this, scheduleList);
        listViewSchedule.setAdapter(adapter);

        buttonLogout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    private boolean isUserLoggedIn() {
        return true;
    }

    public void onAbsenClick(View view) {
        int position = (int) view.getTag();
        Schedule selectedSchedule = scheduleList.get(position);
        Toast.makeText(this, "Absen untuk " + selectedSchedule.getSubject() + " berhasil!", Toast.LENGTH_SHORT).show();
    }
}
