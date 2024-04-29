package dadm.hsingh.horoscopoapp.di

import dadm.hsingh.horoscopoapp.data.horoscope.daily.*
import dadm.hsingh.horoscopoapp.data.horoscope.monthly.*
import dadm.hsingh.horoscopoapp.data.horoscope.weekly.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HoroscopeBinderModule {

    @Binds
    abstract fun bindDailyHoroscopeRepository(impl: DailyHoroscopeRepositoryImpl): DailyHoroscopeRepository

    @Binds
    abstract fun bindDailyHoroscopeDataSource(impl: DailyHoroscopeDataSourceImpl): DailyHoroscopeDataSource
    @Binds
    abstract fun bindWeeklyHoroscopeRepository(impl: WeeklyHoroscopeRepositoryImpl): WeeklyHoroscopeRepository

    @Binds
    abstract fun bindWeeklyHoroscopeDataSource(impl: WeeklyHoroscopeDataSourceImpl): WeeklyHoroscopeDataSource

    @Binds
    abstract fun bindMonthlyHoroscopeRepository(impl: MonthlyHoroscopeRepositoryImpl): MonthlyHoroscopeRepository

    @Binds
    abstract fun bindMonthlyHoroscopeDataSource(impl: MonthlyHoroscopeDataSourceImpl): MonthlyHoroscopeDataSource
}