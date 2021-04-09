package com.creat.god.mapper6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creat.god.mapper6.adapterDb.AdapterDb;
import com.creat.god.mapper6.dbHelper.DBofTable;
import com.creat.god.mapper6.mapper.UserMapper;
import com.creat.god.mapper6.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DBofTable dBofTable;
    private RecyclerView recyclerDbAll, recyclerDbMapper;
    private EditText findId;
    private AdapterDb adapterDb;
    private Button addUsers, btnFind;
    private UserMapper userMapper = new UserMapper();
    public static boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createDBandContent();
        addUserDBAll();
        readDbAll();
        findBtn();
        readDbMapper();
        User d = new User(-1, "Test", "0");
        userMapper.setListUser(d);
    }

    private void readDbMapper() {
        adapterDb = new AdapterDb(MainActivity.this, userMapper.getListUser());
        recyclerDbMapper.setAdapter(adapterDb);
    }

    private void findBtn() {
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findId.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Введите ID", Toast.LENGTH_SHORT).show();
                else
                findMapper(view, findId.getText().toString());
            }
        });
    }

    private void findMapper(View view, String idS) {
        int id = Integer.parseInt(idS);
        userMapper.findUsers(view, id);
        readDbMapper();
    }

    private void addUserDBAll() {
        addUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase database = dBofTable.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < 1000; i++) {
                    contentValues.put(DBofTable.KEY_NAME, createName());
                    contentValues.put(DBofTable.KEY_POWER, createPower());
                    database.insert(DBofTable.TABLE_CONTACTS, null, contentValues);
                }
                readDbAll();
            }
        });
    }

    private String createName() {
        String[] name = {"Max", "Jon", "Gaf", "Pot", "Tobby", "Radf", "Maikl", "Kris", "Staf", "Grom"};
        return name[new Random().nextInt(10)];
    }

    private String createPower(){
        String[] power = {"15", "32", "12", "23", "33", "65", "99", "21", "42", "65"};
        return power[new Random().nextInt(10)];
    }

    private void readDbAll() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase database = new DBofTable(this).getReadableDatabase();

        Cursor cursor = database.query(DBofTable.TABLE_CONTACTS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {

            int idIndex = cursor.getColumnIndex(DBofTable.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBofTable.KEY_NAME);
            int powerIndex = cursor.getColumnIndex(DBofTable.KEY_POWER);

            do {
                User user = new User();
                user.setId(cursor.getInt(idIndex));
                user.setName(cursor.getString(nameIndex));
                user.setPower(cursor.getString(powerIndex));

                userList.add(0, user);

            }while (cursor.moveToNext());
        }else
        {}
        cursor.close();

        adapterDb = new AdapterDb(MainActivity.this, userList);
        recyclerDbAll.setAdapter(adapterDb);
    }

    private void createDBandContent() {
        dBofTable = new DBofTable(this);

        recyclerDbAll = findViewById(R.id.recyclerDbAll);
        recyclerDbMapper = findViewById(R.id.recyclerDbMapper);
        findId = findViewById(R.id.findId);
        addUsers = findViewById(R.id.addUsers);
        btnFind = findViewById(R.id.btnFind);

        recyclerDbAll.setHasFixedSize(true);
        LinearLayoutManager lnManagerAll = new LinearLayoutManager(getApplicationContext());
        recyclerDbAll.setLayoutManager(lnManagerAll);

        recyclerDbMapper.setHasFixedSize(true);
        LinearLayoutManager lnManagerMapper = new LinearLayoutManager(getApplicationContext());
        recyclerDbMapper.setLayoutManager(lnManagerMapper);
    }
}
