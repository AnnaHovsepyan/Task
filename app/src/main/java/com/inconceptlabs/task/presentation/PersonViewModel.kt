package com.inconceptlabs.task.presentation

import android.app.Application
import androidx.lifecycle.*
import com.inconceptlabs.task.data.SortingType
import com.inconceptlabs.task.data.PersonDb
import com.inconceptlabs.task.data.Person
import com.inconceptlabs.task.data.PersonDataRepository
import kotlinx.coroutines.flow.Flow

class PersonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PersonDataRepository(
        PersonDb.create(
            application.applicationContext,
            viewModelScope
        ).personDao()
    )

    fun getPersonData(sortingType: SortingType): Flow<Array<Person>> {
        return if (sortingType == SortingType.NOT_SORTED) {
            repository.getPersonData()
        } else {
            repository.getPersonOrderedData(sortingType.name)
        }
    }
}
