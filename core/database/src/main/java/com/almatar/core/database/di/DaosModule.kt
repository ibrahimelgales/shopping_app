package com.almatar.core.database.di

import com.almatar.core.database.core.AppDatabase
import com.almatar.core.database.dao.ProductDao
import com.almatar.core.database.dao.ProductDaoFts
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesProductDao(
        database: AppDatabase,
    ): ProductDao = database.productDao()

    @Provides
    fun providesProductDaoFts(
        database: AppDatabase,
    ): ProductDaoFts = database.productDaoFts()
}
