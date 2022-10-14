package com.sofia.hunian.admin.laporan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.sofia.hunian.R;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanHunian;
import com.sofia.hunian.adapter.adapterlaporan.AdapterLaporanUser;
import com.sofia.hunian.admin.LaporanAdmin;
import com.sofia.hunian.helper.DataHelper;
import com.sofia.hunian.model.ModelHunian;
import com.sofia.hunian.model.ModelUser;

import java.util.List;

public class LaporanDataUser extends AppCompatActivity {

    RecyclerView rvLaporanDataUser;
    DataHelper dbCenter;
    List<ModelUser> listUser;
    AdapterLaporanUser itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_laporan_data_user);

        rvLaporanDataUser = findViewById(R.id.rvLaporanDataUser);

        dbCenter = new DataHelper(this);
        getData();
    }

    private void getData(){

        listUser = dbCenter.getAllUser();

        if (listUser.size()>0){
            itemList = new AdapterLaporanUser(listUser);
            rvLaporanDataUser.setLayoutManager(new LinearLayoutManager(LaporanDataUser.this));
            rvLaporanDataUser.setAdapter(itemList);
        }

    }

    private void goToLaporan(){
        Intent a = new Intent(LaporanDataUser.this, LaporanAdmin.class);
        startActivity(a);
        finish();
    }

    @Override
    public void onBackPressed(){
        goToLaporan();
    }
}