package com.example.roomcomplete

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    val notes by viewModel.notes.observeAsState(emptyList())
    var titleInput by remember { mutableStateOf("") }
    var textInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Note Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = textInput,
            onValueChange = { textInput = it },
            label = { Text("Note Content") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (titleInput.isNotBlank() && textInput.isNotBlank()) {
                viewModel.addNote(Note(title = titleInput, text = textInput))
                titleInput = ""
                textInput = ""
            }
        }) {
            Text("Add Note")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(notes) { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { viewModel.deleteNote(note) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = note.text,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
