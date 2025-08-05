package com.example.pagoproveedoresapp.di

import android.content.Context
import androidx.room.Room
import com.example.pagoproveedoresapp.data.model.dao.PagoDao
import com.example.pagoproveedoresapp.data.model.dao.ProveedorDao
import com.example.pagoproveedoresapp.data.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "pagos_db"
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideProveedorDao(database: AppDatabase): ProveedorDao =
        database.proveedorDao()

    @Provides
    fun providePagoDao(database: AppDatabase): PagoDao =
        database.pagoDao()
}
