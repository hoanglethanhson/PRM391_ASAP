package com.example.assignment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.assignment.DBHandler.DatabaseHandler;
import com.example.assignment.Entity.ShortTermNote;

import org.w3c.dom.Text;

public class ViewShortPlanDetail extends AppCompatActivity {

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

        TextView title = findViewById(R.id.textViewShortTitle);
        TextView deadline = findViewById(R.id.textView8);
        TextView content = findViewById(R.id.textViewShortPlanContent);
        CheckBox checkBox = findViewById(R.id.chkComplete);
        checkBox.setEnabled(false);

        title.setText(note.getTitle());
        deadline.setText(note.getDeadline());
        content.setText(note.getContent());
        if (note.getIsComplete() == 1) {
            checkBox.setChecked(true);
        }
    }

}
