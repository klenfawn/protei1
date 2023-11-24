package ru.protei.kuzminaed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.protei.kuzminaed.ui.theme.KuzminaedTheme
import ru.protei.kuzminaed.domain.Note

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KuzminaedTheme {
                Surface(color = Color.White) {
                    NoteList(notes)
                }
            }
        }
    }
}

val notes = listOf(
    Note("09.01.2023", "Пуся весит 65 г., Шуня - 90 г."),
    Note("21.01.2023", "Пуся весит 112 г., Шуня - 125 г."),
    Note("30.01.2023", "Пуся весит 135 г., Шуня - 150 г."),
    Note("14.02.2023", "Пуся весит 172 г., Шуня - 180 г."),
    Note("02.03.2023", "Пуся весит 184 г., Шуня - 205 г."),
)

@Composable
fun NoteList(notes: List<Note>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        notes.forEach { note ->
            NoteRow(note)
        }
    }
}

@Composable
fun NoteRow(note: Note) {
    Card(modifier = Modifier
        .padding(all = 7.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Text(note.title, fontSize = 25.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(10.dp))
            Text(note.text, color = Color.Gray, modifier = Modifier.padding(10.dp))
        }
    }
}