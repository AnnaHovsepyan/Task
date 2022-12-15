package com.inconceptlabs.task.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Person::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDb : RoomDatabase() {

    companion object {
        lateinit var databaseBuilder: PersonDb

        fun create(context: Context, applicationScope: CoroutineScope): PersonDb {
            val database = Room.databaseBuilder(context, PersonDb::class.java, "person.db")
            databaseBuilder = database
                .fallbackToDestructiveMigration()
                .addCallback(PersonDatabaseCallback(applicationScope))
                .build()
            return databaseBuilder
        }
    }

    private class PersonDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            databaseBuilder.let {
                scope.launch(Dispatchers.IO) {
                    populateDatabase()
                }
            }
        }

        suspend fun populateDatabase() {
            val dao: PersonDao = databaseBuilder.personDao()
            val listOfPersonData = listOf(
                Person("Liam", 12),
                Person("Noah", 22),
                Person("Oliver", 54),
                Person("Elijah", 43),
                Person("William", 43),
                Person("James", 21),
                Person("Benjamin", 33),
                Person("Lucas", 11),
                Person("Henry", 27),
                Person("Alexander", 26),
                Person("Olivia", 55),
                Person("Emma", 41),
                Person("Ava", 47),
                Person("Charlotte", 28),
                Person("Sophia", 38),
                Person("Amelia", 32),
                Person("Isabella", 33),
                Person("Mia", 19),
                Person("Evelyn", 16),
                Person("Harper", 77),
                Person("Michael", 12),
                Person("David", 22),
                Person("William", 54),
                Person("Richard", 43),
                Person("Thomas", 43),
                Person("Charles", 21),
                Person("Gary", 33),
                Person("Larry", 11),
                Person("Ronald", 27),
                Person("Joseph", 26),
                Person("Donald", 55),
                Person("Kenneth", 41),
                Person("Steven", 47),
                Person("Dennis", 21),
                Person("Paul", 33),
                Person("Stephen", 11),
                Person("George", 27),
                Person("Daniel", 26),
                Person("Edward", 55),
                Person("Mark", 41),
                Person("Jerry", 47),
                Person("Gregory", 28),
                Person("Bruce", 38),
                Person("Roger", 32),
                Person("Douglas", 33),
                Person("Frank", 19),
                Person("Terry", 16),
                Person("Raymond", 77)
            )
            dao.insertAll(listOfPersonData)
        }
    }

    abstract fun personDao(): PersonDao
}