package cafe.adriel.moov.model.repository

import cafe.adriel.moov.Constant
import cafe.adriel.moov.contract.MovieContract
import cafe.adriel.moov.model.entity.MovieResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object MovieRepository: MovieContract.IMovieRepository {

    private val service: IService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create<IService>(IService::class.java)
    }

    override fun getUpcomingMovies(page: Int) =
            service.getUpcomingMovies(Constant.TMDB_KEY, Constant.TMDB_LANGUAGE, Constant.TMDB_REGION, page)

    override fun getSearchMovies(query: String, page: Int) =
            service.getSearchMovies(Constant.TMDB_KEY, Constant.TMDB_LANGUAGE, Constant.TMDB_REGION, false, query, page)

    private interface IService {

        @GET("movie/upcoming")
        fun getUpcomingMovies(
                @Query("api_key") key : String,
                @Query("language") language : String,
                @Query("region") region : String,
                @Query("page") page : Int): Flowable<MovieResponse>

        @GET("search/movie")
        fun getSearchMovies(
                @Query("api_key") key : String,
                @Query("language") language : String,
                @Query("region") region : String,
                @Query("include_adult") includeAdult : Boolean,
                @Query("query") query : String,
                @Query("page") page : Int): Flowable<MovieResponse>

    }

}