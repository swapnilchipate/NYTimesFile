package sat.heady.com.nytimes.module;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin: Yang on 26-09-2018.
 */
@Module
public class ApplicationModule {

    public final static String apivalue = "49b930030fb94ddc8590e358c40db3fd";
    public final static String apiKey = "api-key";
    public static final String baseURL = "https://api.nytimes.com/";

    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    public OkHttpClient providesOkHttpClient(HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request original = chain.request();

                        Request.Builder requestBuilder = original.newBuilder();
                        requestBuilder.addHeader(apiKey, apivalue);

                        Request request = requestBuilder.build();
                        Response originalResponse = chain.proceed(request);
                        return originalResponse;
                    }
                }).build();
    }

    @Provides
    public Gson provideGson(){
        return  new GsonBuilder()
                .setLenient()
                .create();
    }
}
