package com.gitcapixaba.tasklist.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gitcapixaba.tasklist.components.BoxText
import com.gitcapixaba.tasklist.components.Button
import com.gitcapixaba.tasklist.constants.Constants
import com.gitcapixaba.tasklist.repository.TaskRepository
import com.gitcapixaba.tasklist.ui.theme.MyBlue
import com.gitcapixaba.tasklist.ui.theme.MyHighPriorityDisable
import com.gitcapixaba.tasklist.ui.theme.MyHighPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyLowPriorityDisable
import com.gitcapixaba.tasklist.ui.theme.MyLowPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyMediumPriorityDisable
import com.gitcapixaba.tasklist.ui.theme.MyMediumPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyWhite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun SaveTaskListView(navController: NavController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val taskRepository = TaskRepository()

    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(MyBlue), title = {
                Text(
                    text = "Add Task",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyWhite
                )

            })

        },

        )

    {
        var titleTask by remember {
            mutableStateOf("")
        }

        var descriptionTask by remember {
            mutableStateOf("")
        }

        var noPriorityTask by remember {
            mutableStateOf(false)
        }

        var lowPriorityTask by remember {
            mutableStateOf(false)
        }

        var mediumPriorityTask by remember {
            mutableStateOf(false)
        }

        var highPriorityTask by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BoxText(
                value = titleTask,
                onValueChange = { titleTask = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 80.dp, 20.dp, 0.dp),
                label = "Title Task",
                maxLines = 1,
                keyboardType = KeyboardType.Text
            )

            BoxText(
                value = descriptionTask,
                onValueChange = { descriptionTask = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Description Task",
                maxLines = 5,
                keyboardType = KeyboardType.Text
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(text = "Priority Level")



                RadioButton(
                    selected = lowPriorityTask, onClick = {
                        lowPriorityTask = !lowPriorityTask
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = MyLowPriorityDisable,
                        selectedColor = MyLowPrioritySelected
                    )
                )

                RadioButton(
                    selected = mediumPriorityTask, onClick = {
                        mediumPriorityTask = !mediumPriorityTask
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = MyMediumPriorityDisable,
                        selectedColor = MyMediumPrioritySelected
                    )
                )

                RadioButton(
                    selected = highPriorityTask, onClick = {
                        highPriorityTask = !highPriorityTask
                    }, colors = RadioButtonDefaults.colors(
                        unselectedColor = MyHighPriorityDisable,
                        selectedColor = MyHighPrioritySelected
                    )
                )

            }

            Button(
                onClick = {

                    var message = true
                    scope.launch(Dispatchers.IO) {
                        if (titleTask.isEmpty()) {
                            message = false
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && noPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.NO_PRIORITY_ID)
                            message = true
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && lowPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.LOW_PRIORITY_ID)
                            message = true
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && mediumPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.MEDIUM_PRIORITY_ID)
                            message = true
                        } else if (titleTask.isNotEmpty() && descriptionTask.isNotEmpty() && highPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.HIGH_PRIORITY_ID)
                            message = true
                        }else if (titleTask.isNotEmpty() && noPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.NO_PRIORITY_ID)
                            message = true
                        }else if (titleTask.isNotEmpty() && lowPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.LOW_PRIORITY_ID)
                            message = true
                        }else if (titleTask.isNotEmpty() && mediumPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.MEDIUM_PRIORITY_ID)
                            message = true
                        }else if (titleTask.isNotEmpty() && highPriorityTask){
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.HIGH_PRIORITY_ID)
                            message = true
                        } else {
                            taskRepository.saveTaskRepository(titleTask, descriptionTask, Constants.PRIORITY.NO_PRIORITY_ID)
                            message = true
                        }
                    }

                    scope.launch(Dispatchers.Main) {
                        if (message) {
                            Toast.makeText(context, "Sucess in save this task", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Title is mandatory *", Toast.LENGTH_SHORT).show()

                        }
                    }

                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .padding(20.dp), text = "Save"
            )
        }
    }

}