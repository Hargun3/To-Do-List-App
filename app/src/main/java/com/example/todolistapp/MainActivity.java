package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Button add;
    private AlertDialog dialog;
    private LinearLayout layout;
    private int taskId = 0;
    private Set<String> taskNames = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        buildDialog();
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialog.show();
            }
        });
    }

    private void buildDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        final EditText name = view.findViewById(R.id.nameEdit);
        builder.setView(view);
        builder.setTitle("Enter your Task")
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String taskName = name.getText().toString().trim();
                        if (!taskName.isEmpty() && !taskNames.contains(taskName)) {
                            addCard(taskName);
                            taskNames.add(taskName);
                        } else {
                            // Display a message indicating that the task name is already added
                            Toast.makeText(MainActivity.this, "Task already exists or name is empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        // Do nothing
                    }
                });

        dialog = builder.create();
    }

    private void addCard(final String name){
        final View view = getLayoutInflater().inflate(R.layout.card, null);
        view.setTag("task_" + taskId++);

        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);
        nameView.setText(name);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                layout.removeView(view);
                taskNames.remove(name);
            }
        });
        layout.addView(view);
    }
}
