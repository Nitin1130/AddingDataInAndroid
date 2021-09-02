package com.example.databasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.databasedemo.databinding.ActivityMainBinding
import com.example.databasedemo.db.MyDbHelper
import com.example.databasedemo.db.TodoTable


class MainActivity : AppCompatActivity() {

    val todos = ArrayList<Todo>()

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)





        val todoAdapter = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )

        val db = MyDbHelper(this).writableDatabase

        fun refreshTodoList() {
            val todoList = TodoTable.getAllTodos(db)
            todos.clear()
            todos.addAll(todoList)
            todoAdapter.notifyDataSetChanged()
        }

        binding.lvTodos.adapter = todoAdapter


        binding.btnAddTodo.setOnClickListener {
            val newTodo = Todo(
                binding.etNewTodo.text.toString(),
                false,
            )

            TodoTable.insertTodo(db, newTodo)
            refreshTodoList()

        }
    }
}