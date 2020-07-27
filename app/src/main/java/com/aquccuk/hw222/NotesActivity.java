package com.aquccuk.hw222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.*;


public class NotesActivity extends AppCompatActivity {

    private EditText mInputNote, mOutputNote;
    private Button mBtnSaveNote;

    private SharedPreferences myNoteSharePref;

    private static String NOTE_TEXT = "note_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        initViews();
        getDataFromSharedPref();
    }

    private void initViews(){
        mInputNote = findViewById(R.id.inputNote);
        mBtnSaveNote = findViewById(R.id.btnSaveNote);
        mOutputNote = findViewById(R.id.outPut);

        myNoteSharePref = getSharedPreferences("MyNote", MODE_PRIVATE);

        mBtnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor myEditor = myNoteSharePref.edit();
                String noteText = mInputNote.getText().toString();
                myEditor.putString(NOTE_TEXT, noteText);
                myEditor.apply();
                Toast.makeText(NotesActivity.this, " данные сохранены", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getDataFromSharedPref(){
        String noteText = myNoteSharePref.getString(NOTE_TEXT, "");
        mOutputNote.setText(noteText);
    }
}