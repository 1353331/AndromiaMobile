package ca.qc.cstj.andromiamobile.repositories

import android.util.Log
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.models.Monster
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object MonsterRepository {
    suspend fun getMonsters(): RepositoryResult<List<Monster>> {
        return withContext(Dispatchers.IO) {
            //Fonctionne dans un autre thread
            val (_, _, result) = Services.MONSTER_SERVICE.httpGet().responseJson()

            when (result) {
                is Result.Success -> {
                    RepositoryResult.Success(Json { ignoreUnknownKeys = true }.decodeFromString(result.value.content))
                }
                is Result.Failure -> {
                    RepositoryResult.Error(result.getException())
                }

            }
        }
    }
}