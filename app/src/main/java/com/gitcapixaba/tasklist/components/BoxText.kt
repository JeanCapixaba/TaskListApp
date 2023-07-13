package com.gitcapixaba.tasklist.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.gitcapixaba.tasklist.ui.theme.MyBlack
import com.gitcapixaba.tasklist.ui.theme.MyBlue
import com.gitcapixaba.tasklist.ui.theme.MyWhite
import com.gitcapixaba.tasklist.ui.theme.ShapeEditText

@ExperimentalMaterial3Api
@Composable
fun BoxText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = { Text(text = label) },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MyBlack,
            focusedBorderColor = MyBlue,
            focusedLabelColor = MyBlue,
            containerColor = MyWhite,
            cursorColor = MyBlue
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

