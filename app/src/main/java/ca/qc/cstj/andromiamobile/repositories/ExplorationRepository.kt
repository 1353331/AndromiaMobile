package ca.qc.cstj.andromiamobile.repositories

import android.util.Log
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.models.Exploration
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ExplorationRepository {
    suspend fun getExplorations(accessToken:String): RepositoryResult<List<Exploration>> {
        return withContext(Dispatchers.IO) {
            //EL :On va chercher les explorations
            val token = "Bearer $accessToken".replace("\"", "")
            val (_, _, result) = Services.EXPLORATION_SERVICE.httpGet().header("Authorization" to token).responseJson()

            //EL :On teste voir si on a réussi à aller les récupérer ou non
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