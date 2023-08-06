import com.example.gridapplication.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("products")
    fun getApiResponse(): Call<ApiResponse>
}