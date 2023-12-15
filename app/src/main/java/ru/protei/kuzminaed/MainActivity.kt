package ru.protei.kuzminaed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import ru.protei.kuzminaed.ui.theme.KuzminaedTheme
import ru.protei.kuzminaed.notes.NoteList
import ru.protei.kuzminaed.notes.NotesViewModel

class MainActivity : ComponentActivity() {

    private val notesViewModel: NotesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KuzminaedTheme {
                Surface(color = Color.White) {
                    NoteList(notesViewModel)
                }
            }
        }
    }
}
