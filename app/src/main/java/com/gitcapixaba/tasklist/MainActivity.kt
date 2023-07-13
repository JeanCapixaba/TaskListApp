@file:OptIn(ExperimentalMaterial3Api::class)

package com.gitcapixaba.tasklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gitcapixaba.tasklist.ui.theme.TaskListTheme
import com.gitcapixaba.tasklist.view.SaveTaskListView
import com.gitcapixaba.tasklist.view.TaskListView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskListTheme {

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "TaskListView") {

                    composable(route = "TaskListView") {
                        TaskListView(navController)
                    }

                    composable(route = "SaveTaskListView") {
                        SaveTaskListView(navController)
                    }

                }

            }
        }
    }
}

