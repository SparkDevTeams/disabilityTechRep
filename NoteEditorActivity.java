package com.example.noteboxalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NoteEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editNote = (EditText) findViewById(R.id.editNote);

        Intent intent = getIntent();
        final int noteId = intent.getIntExtra("noteID",-1);

        if(noteId != -1) {
            editNote.setText(MianActivity2.notes.get(noteId));
        }

        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                MianActivity2.notes.set(noteId,String.valueOf(charSequence));
                MianActivity2.arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
