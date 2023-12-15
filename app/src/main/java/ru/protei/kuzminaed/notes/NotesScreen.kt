package ru.protei.kuzminaed.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.protei.kuzminaed.domain.Note

@Composable
fun NoteRow(note: Note, onNoteSelected: (Note) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable {
                onNoteSelected(note)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    )  {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(note.title, fontSize = 25.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(10.dp))
            Text(note.text, color = Color.Gray, modifier = Modifier.padding(10.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteList(vm: NotesViewModel) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (vm.isEditing.value) {
                    vm.onEditCompleted(vm.selected.value?.title ?: "", vm.selected.value?.text ?: "")
                } else {
                    var newNote = Note("Название", "Описание")
                    vm.onAddNoteClicked(newNote.title, newNote.text)
                }
            }) {
                if (vm.isEditing.value) {
                    Icon(Icons.Default.Check, contentDescription = "Save")
                } else {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }
    ) {it
        if (vm.isEditing.value) {
            val note = vm.selected.value ?: return@Scaffold
            EditNotePage(vm, title = note.title, text = note.text ?: "")
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(6.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                items(vm.notes) { note ->
                    NoteRow(note) {
                        vm.onNoteSelected(it)
                    }
                }
            }
        }
    }
}

@Composable
fun EditNotePage(vm: NotesViewModel, title: String, text: String) {
    var editedTitle by remember { mutableStateOf(title) }
    var editedText by remember { mutableStateOf(text) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = editedTitle,
            onValueChange = {
                editedTitle = it
                vm.onNoteChanged(it, editedText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textStyle = MaterialTheme.typography.bodyLarge
                .copy(color = Color(0xFF625b71)),
        )

        BasicTextField(
            value = editedText,
            onValueChange = {
                editedText = it
                vm.onNoteChanged(editedTitle, it)
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyLarge
                .copy(color = Color(0xFF625b71)),
        )
    }
}