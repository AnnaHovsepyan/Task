package com.inconceptlabs.task.data

import kotlinx.coroutines.flow.Flow

class PersonDataRepository(private val personDb: PersonDao) {

    fun getPersonData(): Flow<Array<Person>> =
        personDb.getPersonData()

    fun getPersonOrderedData(sort: String) =
        personDb.getPersonData(sort == SortingType.ASC.name)
}


