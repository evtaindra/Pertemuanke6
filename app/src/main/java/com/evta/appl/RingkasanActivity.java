package com.evta.appl;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RingkasanActivity extends AppCompatActivity {

    TextView tvNama, tvMenu, tvJumlah;
    ImageView imgRingkasan;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ringkasan);

        // Inisialisasi view
        tvNama = findViewById(R.id.tvNama);
        tvMenu = findViewById(R.id.tvMenu);
        tvJumlah = findViewById(R.id.tvJumlah);
        imgRingkasan = findViewById(R.id.imgRingkasan);
        btnKembali = findViewById(R.id.btnKembali);

        // Ambil data dari Intent
        String nama = getIntent().getStringExtra("NAMA");
        String menu = getIntent().getStringExtra("MENU");
        int jumlah = getIntent().getIntExtra("JUMLAH", 0);

        // Tampilkan ke TextView
        tvNama.setText("Nama: " + nama);
        tvMenu.setText("Menu: " + menu);
        tvJumlah.setText("Jumlah: " + jumlah + " porsi");

        // Tampilkan gambar berdasarkan menu
        switch (menu) {
            case "Nasi Goreng":
                imgRingkasan.setImageResource(R.drawable.nasigoreng);
                break;
            case "Mie Ayam":
                imgRingkasan.setImageResource(R.drawable.mieayam);
                break;
            case "Sate Ayam":
                imgRingkasan.setImageResource(R.drawable.sateayam);
                break;
            case "Soto Ayam":
                imgRingkasan.setImageResource(R.drawable.sotoayam);
                break;
            case "Bakso":
                imgRingkasan.setImageResource(R.drawable.bakso);
                break;
            case "Rendang":
                imgRingkasan.setImageResource(R.drawable.rendang);
                break;
            case "Gado-Gado":
                imgRingkasan.setImageResource(R.drawable.gadogado);
                break;
            case "Nasi Padang":
                imgRingkasan.setImageResource(R.drawable.nasipadang);
                break;
            case "Ayam Penyet":
                imgRingkasan.setImageResource(R.drawable.ayampenyet);
                break;
            case "Pecel Lele":
                imgRingkasan.setImageResource(R.drawable.pecellele);
                break;
            default:
                imgRingkasan.setImageResource(R.drawable.nasigoreng); // fallback
                break;
        }

        // Aksi tombol kembali
        btnKembali.setOnClickListener(v -> {
            Intent intent = new Intent(RingkasanActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }
}
