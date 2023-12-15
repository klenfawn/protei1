package ru.protei.kuzminaed.notes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.protei.kuzminaed.domain.Note

class NotesViewModel : ViewModel() {
    val isEditing = mutableStateOf(false)
    val selected = mutableStateOf<Note?>(null)

    var notes by mutableStateOf(
        listOf(
            Note("09.01.2023", "Пуся весит 65 г., Шуня - 90 г."),
            Note("21.01.2023", "Пуся весит 112 г., Шуня - 125 г."),
            Note("30.01.2023", "Пуся весит 135 г., Шуня - 150 г."),
            Note("14.02.2023", "Пуся весит 172 г., Шуня - 180 г."),
            Note("02.03.2023", "Пуся весит 184 г., Шуня - 205 г."),
        )
    )

    fun onNoteChanged(title: String, text: String) {
        selected.value?.let { note ->
            note.title = title
            note.text = text
        }
    }

    fun onEditCompleted(editedTitle: String, editedText: String) {
        selected.value?.apply {
            title = editedTitle
            text = editedText
        }
        isEditing.value = false
    }


    fun onNoteSelected(note: Note) {
        selected.value = note
        isEditing.value = true
    }



    fun onAddNoteClicked(title: String, text: String) {
        val newNote = Note(title, text)
        notes = notes.toMutableList() + newNote
    }
}