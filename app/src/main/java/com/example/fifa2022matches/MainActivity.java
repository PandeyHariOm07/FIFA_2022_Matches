package com.example.fifa2022matches;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fifa2022matches.Adapter.fixtureAdapter;
import com.example.fifa2022matches.manager.RequestManager;
import com.example.fifa2022matches.model.FixtureResponce;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog dialog;
    RequestManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_fixture);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading....");
        manager = new RequestManager(this);
        manager.getFixture(listener,1331);
        dialog.show();
    }
    private final  ResponceListener listener = new ResponceListener() {
        @Override
        public void didfatch(FixtureResponce responce, String message) {
            dialog.dismiss();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
            fixtureAdapter adapter = new fixtureAdapter(MainActivity.this,responce.data);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}