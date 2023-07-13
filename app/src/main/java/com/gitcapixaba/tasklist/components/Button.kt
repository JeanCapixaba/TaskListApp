package com.gitcapixaba.tasklist.components


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gitcapixaba.tasklist.ui.theme.MyBlue
import com.gitcapixaba.tasklist.ui.theme.MyWhite


@Composable
fun Button(onClick: () -> Unit, modifier: Modifier, text: String) {

    androidx.compose.material3.Button(
        onClick, modifier, colors = ButtonDefaults.buttonColors(
            containerColor = MyBlue,
            contentColor = MyWhite
        )
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }

}


