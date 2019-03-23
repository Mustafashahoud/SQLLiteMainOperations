package com.example.sqlliteapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    EditText editTextName, editTextSurName, editTextMark, editTextId;
    Button btnAddData;
    Button btnViewAllButton;
    Button btnUpdateData;
    Button btnDeleteData;
    Context mContext = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(mContext);

        editTextName = findViewById(R.id.nameId);
        editTextSurName = findViewById(R.id.surnameId);
        editTextMark = findViewById(R.id.markId);
        editTextId = findViewById(R.id.idPrimary);

        btnAddData = findViewById(R.id.addDataId);
        btnViewAllButton = findViewById(R.id.btnViewAllButton);
        btnUpdateData = findViewById(R.id.updateData);
        btnDeleteData = findViewById(R.id.deleteData);

        addData();
        viewAll();
        updateData();
        deleteData();

    }

    /**
     * Add data.
     */
    public  void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editTextName.getText().toString(),
                                editTextSurName.getText().toString(),
                                editTextMark.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    /**
     * View all.
     */
    public void viewAll() {
        btnViewAllButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n");
                            buffer.append("Marks :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    /**
     * Show message.
     *
     * @param title   the title
     * @param Message the message
     */
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    /**
     * Update data.
     */
    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int updatedRows =  myDb.updateData(
                        editTextId.getText().toString(),
                        editTextName.getText().toString(),
                        editTextSurName.getText().toString(),
                        editTextMark.getText().toString());

               if (updatedRows > 0 )
                   Toast.makeText(MainActivity.this,"Updated Rows" + updatedRows, Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(MainActivity.this,"Data NOT Updated", Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * Delete data.
     */
    public void deleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int deletedRows =  myDb.deleteData(editTextId.getText().toString());
                if (deletedRows > 0 )
                    Toast.makeText(MainActivity.this,"deleted Rows" + deletedRows, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data NOT Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }
}
