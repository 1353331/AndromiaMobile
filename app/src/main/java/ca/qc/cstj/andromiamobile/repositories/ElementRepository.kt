package ca.qc.cstj.andromiamobile.repositories

import android.util.Log
import ca.qc.cstj.andromiamobile.helpers.RepositoryResult
import ca.qc.cstj.andromiamobile.helpers.Services
import ca.qc.cstj.andromiamobile.models.Element
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object ElementRepository {
    suspend fun getElements(accessToken:String): RepositoryResult<List<Element>> {
        return withContext(Dispatchers.IO) {
            val token = "Bearer $accessToken".replace("\"", "")
            //EL :On va chercher les Elements
            val (_, _, result) = Services.INVENTORY_SERVICE.httpGet().header("Authorization" to token).responseJson()

            //EL :On teste voir si on a réussi à aller les récupérer ou non
            when (result) {
                is Result.Success -> {
                    Log.d("testElement", result.value.obj().getJSONArray("element").toString())
                    RepositoryResult.Success(Json { ignoreUnknownKeys = true }.decodeFromString(result.value.obj().getJSONArray("element").toString()))
                }
                is Result.Failure -> {
                    RepositoryResult.Error(result.getException())
                }

            }
        }
    }
}