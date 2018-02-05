package com.example.noor.studentrecords;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private android.widget.ImageButton ibnAdd;
    private android.widget.ImageButton ibtnShow;

    StudentDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ibtnShow = findViewById(R.id.ibtnShow);
        this.ibnAdd = findViewById(R.id.ibnAdd);

        db = new StudentDB(this);


        ibnAdd.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                View view = getLayoutInflater().inflate(R.layout.add_record, null);

                Button btnCancel = view.findViewById(R.id.btnCancel);
                Button btnAdd = view.findViewById(R.id.btnAdd);
                final EditText etEmail = view.findViewById(R.id.etEmail);
                final RadioButton rbMale = view.findViewById(R.id.rbMale);
                final EditText etRoll = view.findViewById(R.id.etRoll);
                final EditText etName = view.findViewById(R.id.etName);
                final EditText etPhone = view.findViewById(R.id.etPhone);

                builder.setView(view);
                builder.setCancelable(false);
                builder.create();
                final AlertDialog alertDialog=builder.show();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Record record=new Record();
                        record.setRoll(Integer.parseInt(etRoll.getText().toString()));
                        record.setName(etName.getText().toString());
                        record.setPhone(etPhone.getText().toString());
                        record.setEmail(etEmail.getText().toString());
                        String gender;
                        if (rbMale.isChecked()){
                            gender="Male";
                        }else {
                            gender="Female";
                        }
                        record.setGender(gender);

                        db.addRecord(record);

                        Toast.makeText(MainActivity.this, "Details Saved...", Toast.LENGTH_SHORT).show();
                        etRoll.setText("");
                        etName.setText("");
                        etPhone.setText("");
                        etEmail.setText("");
                        rbMale.setChecked(true);

                    }
                });



            }
        });

        ibtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);

                View view = getLayoutInflater().inflate(R.layout.show_details, null);

                Button btnCancel = view.findViewById(R.id.btnCancel);
                Button btnShow = view.findViewById(R.id.btnShow);
                final EditText etEmail = view.findViewById(R.id.etEmail);
                final RadioButton rbMale = view.findViewById(R.id.rbMale);
                final RadioButton rbFemale = view.findViewById(R.id.rbFemale);
                final EditText etRoll = view.findViewById(R.id.etRoll);
                final EditText etName = view.findViewById(R.id.etName);
                final EditText etPhone = view.findViewById(R.id.etPhone);

                builder.setView(view);
                builder.setCancelable(false);
                builder.create();
                final AlertDialog alertDialog=builder.show();

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();

                    }
                });
                btnShow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int roll = Integer.parseInt(etRoll.getText().toString());
                        Record record=db.getRecord(roll);
                        etName.append(record.getName());
                        etPhone.append(record.getPhone());
                        etEmail.append(record.getEmail());
                        if (record.getGender().equals("Male")){
                            rbMale.setChecked(true);
                        }else {
                            rbFemale.setChecked(true);
                        }
                    }
                });
            }
        });
    }
}
