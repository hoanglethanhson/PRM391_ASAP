package com.example.assignment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.DBHandler.DatabaseHandler;
import com.example.assignment.Entity.ShortTermNote;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ViewShortPlanDetail extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    String dateTime;

    private int day;
    private int month;
    private int year;

    private int id;

    //relationship constants
    private static final int NOT_BELONG = -1;
    private static final int NOT_COMPLETE = 0;
    private static final int NOT_DELETED = -1;

    private static final int COMPLETE = 1;
    private static final int DELETED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_short_plan_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        String id = bundle.getString("id");
        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        ShortTermNote note = databaseHandler.findShortById(Integer.parseInt(id));
        this.id = note.getId();

        TextView title = findViewById(R.id.textViewShortTitle);
        TextView deadline = findViewById(R.id.textView8);
        TextView deadTime = findViewById(R.id.textViewShortTime);
        TextView content = findViewById(R.id.textViewShortPlanContent);
        CheckBox checkBox = findViewById(R.id.chkComplete);
        //checkBox.setEnabled(false);

        dateTime = note.getDeadline();
        String[] words = dateTime.split("\\s");
        title.setText(note.getTitle());
        deadline.setText(words[0]);
        deadTime.setText(words[1]);

        content.setText(note.getContent());
        if (note.getIsComplete() == 1) {
            checkBox.setChecked(true);
        }
    }

    public void onTitleClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Title");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        builder.setView(input);
        //Set up the button
        builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView title = findViewById(R.id.textViewShortTitle);
                title.setText(input.getText().toString());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    public void onDeadlineClick(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.day = dayOfMonth;
        //update date text view
        StringBuilder builder = new StringBuilder();
        builder.append(year).append("/").append(month + 1).append("/").append(day);
        TextView deadline = findViewById(R.id.textView8);
        deadline.setText(builder);
    }

    public void onSaveModClick(View view) {
        TextView title = findViewById(R.id.textViewShortTitle);
        TextView deadline = findViewById(R.id.textView8);
        TextView deadTime = findViewById(R.id.textViewShortTime);
        TextView content = findViewById(R.id.textViewShortPlanContent);
        CheckBox checkBox = findViewById(R.id.chkComplete);

        ShortTermNote note = new ShortTermNote();
        note.setTitle(title.getText().toString());
        note.setContent(content.getText().toString());
        note.setDeadline(deadline.getText().toString().trim() + " " + deadTime.getText().toString().trim());
        if (checkBox.isChecked()) {
            note.setIsComplete(COMPLETE);
        }

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        int result = databaseHandler.updateShortNote(this.id, note);

        //notice if succeeded
        if (result != 0) {
            Toast.makeText(this, "Update successfully", Toast.LENGTH_LONG).show();
            //restart the activity
            recreate();
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_LONG).show();
        }
    }
}
