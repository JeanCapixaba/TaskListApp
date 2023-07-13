package com.gitcapixaba.tasklist.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gitcapixaba.tasklist.R
import com.gitcapixaba.tasklist.constants.Constants
import com.gitcapixaba.tasklist.listItem.taskItem
import com.gitcapixaba.tasklist.model.Task
import com.gitcapixaba.tasklist.repository.TaskRepository
import com.gitcapixaba.tasklist.ui.theme.MyBlue
import com.gitcapixaba.tasklist.ui.theme.MyWhite
import com.google.firebase.ktx.Firebase


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskListView(navController: NavController) {


    val taskRepository = TaskRepository()
    val context = LocalContext.current

    Scaffold(/*topBar = {
            TopAppBar( modifier = Modifier.width(5.dp), colors = TopAppBarDefaults.smallTopAppBarColors(MyBlue),
                title = {

                    Text(
                        text = "Task List", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = MyWhite
                    )

                })

        } */floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate("SaveTaskListView") }, containerColor = MyBlue
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                contentDescription = "Add Task"
            )
        }
    })

    {


        val taskList = taskRepository.recoveryTask().collectAsState(mutableListOf()).value
        
        LazyColumn {
            itemsIndexed(taskList){position, _, ->
                taskItem(position = position, taskList = taskList, context = context, navController = navController)
            }
        }

    }


}







