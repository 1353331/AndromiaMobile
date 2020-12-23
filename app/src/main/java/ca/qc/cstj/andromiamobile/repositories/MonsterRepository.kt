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
    suspend fun getMonsters(accessToken:String): RepositoryResult<List<Monster>> {
        return withContext(Dispatchers.IO) {
            val token = "Bearer $accessToken".replace("\"", "")
            val (_, _, result) = Services.INVENTORY_SERVICE.httpGet().header("Authorization" to token).responseJson()

            when (result) {
                is Result.Success -> {
                    RepositoryResult.Success(Json { ignoreUnknownKeys = true }.decodeFromString(result.value.obj().getJSONArray("monster").toString()))
                }
                is Result.Failure -> {
                    RepositoryResult.Error(result.getException())
                }

            }
        }
    }
}