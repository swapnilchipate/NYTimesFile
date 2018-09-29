package sat.heady.com.nytimes.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Admin: Yang on 26-09-2018.
 */
@Module
public class ContextModule {
    private final Context mcontext;
    public ContextModule(Context context){
        mcontext=context;
    }

    @Provides
    public Context context(){
        return mcontext;
    }
}
