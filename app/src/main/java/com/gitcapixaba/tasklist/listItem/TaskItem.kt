package com.gitcapixaba.tasklist.listItem

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.gitcapixaba.tasklist.R
import com.gitcapixaba.tasklist.model.Task
import com.gitcapixaba.tasklist.repository.TaskRepository
import com.gitcapixaba.tasklist.ui.theme.MyBlack
import com.gitcapixaba.tasklist.ui.theme.MyBlue
import com.gitcapixaba.tasklist.ui.theme.MyHighPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyLowPriorityColor
import com.gitcapixaba.tasklist.ui.theme.MyLowPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyMediumPrioritySelected
import com.gitcapixaba.tasklist.ui.theme.MyWhite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun taskItem(
    position: Int,
    taskList: MutableList<Task>,
    context: Context,
    navController: NavController
) {

    val taskTitle = taskList[position].task
    val taskDescription = taskList[position].description
    val taskPriority = taskList[position].priority

    val scope = rememberCoroutineScope()
    val taskRepository = TaskRepository()

    fun dialogDelete(){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Delete Task")
            .setMessage("want to delete the task?")
            .setPositiveButton("Yes"){_, _ ->
                taskRepository.deleteTask(taskTitle.toString())

                scope.launch(Dispatchers.Main) {
                    taskList.removeAt(position)
                    navController.navigate("TaskListView")
                    Toast.makeText(context, "Success in delete the task", Toast.LENGTH_SHORT).show()
                }

            }
            .setNegativeButton("No"){_, _ ->

            }
            .show()
    }

    var taskLevel: String = when (taskPriority) {
        0 -> {
            "No priority"
        }

        1 -> {
            "Low priority"
        }

        2 -> {
            "Medium priority"
        }

        else -> {
            "High Priority"
        }
    }

    val colorLevel = when (taskPriority) {
        0 -> {
            MyLowPriorityColor
        }

        1 -> {
            MyLowPrioritySelected
        }

        2 -> {
            MyMediumPrioritySelected
        }

        else -> {
            MyHighPrioritySelected
        }
    }



    Card(
        colors = CardDefaults.cardColors(containerColor = MyBlack),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(30.dp)
        ) {
            val (txtTitle, txtDescription, cardPriority, txtPriority, btnDelete) = createRefs()



            Text(text = taskTitle.toString(), color = MyWhite, fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(text = taskDescription.toString(), color = MyWhite,
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(text = taskLevel, color = MyWhite, fontWeight = FontWeight.W400,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = colorLevel),
                modifier = Modifier
                    .size(23.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 20.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    }
            ) {

            }

            IconButton(onClick = { dialogDelete() }, modifier = Modifier.constrainAs(btnDelete) {
                top.linkTo(txtDescription.bottom, margin = 10.dp)
                start.linkTo(cardPriority.end, margin = 20.dp)
                end.linkTo(parent.end, margin = 10.dp)
                bottom.linkTo(parent.bottom, margin = 10.dp)
            }) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = "Delete Task"
                )
            }

        }

    }
}

