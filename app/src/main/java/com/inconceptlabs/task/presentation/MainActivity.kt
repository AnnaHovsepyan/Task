package com.inconceptlabs.task.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.inconceptlabs.task.R
import com.inconceptlabs.task.data.SortingType
import com.inconceptlabs.task.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    private val personViewModel: PersonViewModel by viewModels()

    private var adapter: CustomAdapter = CustomAdapter()

    private val spinnerItemSelectListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            p0: AdapterView<*>?,
            p1: View?,
            position: Int,
            p3: Long
        ) {
            when (position) {
                0 -> getPersonData(SortingType.NOT_SORTED)
                1 -> getPersonData(SortingType.ASC)
                2 -> getPersonData(SortingType.DESC)
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) = Unit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }

    private fun initViews() {
        initSpinner()
        recyclerView.adapter = adapter
    }

    private fun initSpinner() {
        val arrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.arr,
            android.R.layout.simple_spinner_item
        )
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerSortBy.apply {
            adapter = arrayAdapter
            onItemSelectedListener = spinnerItemSelectListener
        }
    }

    private fun getPersonData(sortingType: SortingType) {
        lifecycleScope.launch {
            personViewModel.getPersonData(sortingType).collectLatest {
                adapter.setItems(it.toMutableList())
                recyclerView.scrollToPosition(0)
            }
        }
    }
}