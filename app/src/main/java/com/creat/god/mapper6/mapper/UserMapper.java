package com.creat.god.mapper6.mapper;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.creat.god.mapper6.dbHelper.DBofTable;
import com.creat.god.mapper6.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.creat.god.mapper6.MainActivity.check;

public class UserMapper {

    private List<User> listUser = new ArrayList<>();


    public void findUsers(View view, int id){
            if (check == true){
                long timeKash = System.nanoTime();
                for (int j = 0; j < listUser.size(); j++) {
                    if (id == listUser.get(j).getId()){
                        Toast.makeText(view.getContext(), "мы нашли из Кэша "
                                + listUser.get(j).getName()
                                + " за время " + (System.nanoTime() - timeKash), Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                check = false;
            }
            if (check == false){
                check = true;
                long timeDB = System.nanoTime();
                SQLiteDatabase database = new DBofTable(view.getContext()).getReadableDatabase();

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

                        if (user.getId() == id){
                            Toast.makeText(view.getContext(), "мы нашли из базы "
                                            + user.getName() + " за время " + (System.nanoTime() - timeDB),
                                    Toast.LENGTH_LONG).show();
                            this.listUser.add(user);
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
    }


    public List<User> getListUser() {
        return this.listUser;
    }

    public void setListUser(User listUser) {
        this.listUser.add(listUser);
    }
}
