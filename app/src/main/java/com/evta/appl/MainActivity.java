package com.evta.appl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ListView listRiwayat;
    EditText etNama, etJumlah;
    Spinner spinnerMenu;
    ImageView imgMakanan;
    Button btnPesan, btnRiwayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = findViewById(R.id.etNama);
        etJumlah = findViewById(R.id.etJumlah);
        spinnerMenu = findViewById(R.id.spinnerMenu);
        imgMakanan = findViewById(R.id.imgMakanan);
        btnPesan = findViewById(R.id.btnPesan);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        listRiwayat = findViewById(R.id.listRiwayat);

        // Inisialisasi spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.menu_makanan, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMenu.setAdapter(adapter);

        // Ubah gambar saat menu dipilih
        spinnerMenu.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, android.view.View view, int position, long id) {
                String menu = spinnerMenu.getSelectedItem().toString();
                switch (menu) {
                    case "Nasi Goreng":
                        imgMakanan.setImageResource(R.drawable.nasigoreng);
                        break;
                    case "Mie Ayam":
                        imgMakanan.setImageResource(R.drawable.mieayam);
                        break;
                    case "Sate Ayam":
                        imgMakanan.setImageResource(R.drawable.sateayam);
                        break;
                    case "Soto Ayam":
                        imgMakanan.setImageResource(R.drawable.sotoayam);
                        break;
                    case "Bakso":
                        imgMakanan.setImageResource(R.drawable.bakso);
                        break;
                    case "Rendang":
                        imgMakanan.setImageResource(R.drawable.rendang);
                        break;
                    case "Gado-Gado":
                        imgMakanan.setImageResource(R.drawable.gadogado);
                        break;
                    case "Nasi Padang":
                        imgMakanan.setImageResource(R.drawable.nasipadang);
                        break;
                    case "Ayam Penyet":
                        imgMakanan.setImageResource(R.drawable.ayampenyet);
                        break;
                    case "Pecel Lele":
                        imgMakanan.setImageResource(R.drawable.pecellele);
                        break;
                    default:
                        imgMakanan.setImageResource(R.drawable.nasigoreng); // fallback
                        break;
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {}
        });

        // Tombol Pesan
        btnPesan.setOnClickListener(v -> {
            String nama = etNama.getText().toString();
            String menu = spinnerMenu.getSelectedItem().toString();
            int jumlah = Integer.parseInt(etJumlah.getText().toString());

            // Simpan ke SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("RIWAYAT_PESANAN", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nama", nama);
            editor.putString("menu", menu);
            editor.putInt("jumlah", jumlah);
            editor.apply();

            // Kirim ke RingkasanActivity
            Intent intent = new Intent(MainActivity.this, RingkasanActivity.class);
            intent.putExtra("NAMA", nama);
            intent.putExtra("MENU", menu);
            intent.putExtra("JUMLAH", jumlah);
            startActivity(intent);
        });

        // Tombol Riwayat
        btnRiwayat.setOnClickListener(v -> {
            SharedPreferences sharedPref = getSharedPreferences("RIWAYAT_PESANAN", MODE_PRIVATE);
            String nama = sharedPref.getString("nama", "Belum ada");
            String menu = sharedPref.getString("menu", "Belum ada");
            int jumlah = sharedPref.getInt("jumlah", 0);

            // Format data sebagai list
            String[] dataRiwayat = {
                    "Nama: " + nama + " Menu: " + menu + " Jumlah: " + jumlah + " porsi"
            };

            ArrayAdapter<String> adapterlist = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, dataRiwayat);

            listRiwayat.setAdapter(adapterlist);
            Toast.makeText(this, "Data menu: " + menu, Toast.LENGTH_SHORT).show();
        });
    }
}
