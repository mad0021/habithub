package com.dennnisver4.habithub.di;

import android.content.Context;
import com.dennnisver4.habithub.data.HabitHubDao;
import com.dennnisver4.habithub.data.HabitHubDatabase;
import com.dennnisver4.habithub.data.repository.MonthlyCalendarRepository;
import com.dennnisver4.habithub.data.repository.ObjectivesRepository;
import com.dennnisver4.habithub.data.repository.ProgressRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Singleton;

@Module
@InstallIn(SingletonComponent.class)
public final class AppModule {

    @Provides
    @Singleton
    public static HabitHubDatabase provideDatabase(@ApplicationContext Context context) {
        // Kotlin companion methods are exposed via Companion in Java unless annotated with @JvmStatic
        return HabitHubDatabase.Companion.getDatabase(context);
    }

    @Provides
    @Singleton
    public static HabitHubDao provideHabitHubDao(HabitHubDatabase db) {
        return db.habitHubDao();
    }

    @Provides
    @Singleton
    public static MonthlyCalendarRepository provideMonthlyCalendarRepository(HabitHubDao dao) {
        return new MonthlyCalendarRepository(dao);
    }

    @Provides
    @Singleton
    public static ObjectivesRepository provideObjectivesRepository(HabitHubDao dao) {
        return new ObjectivesRepository(dao);
    }

    @Provides
    @Singleton
    public static ProgressRepository provideProgressRepository(HabitHubDao dao) {
        return new ProgressRepository(dao);
    }
}
