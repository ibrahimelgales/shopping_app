package com.almatar.core.data.di

import com.almatar.core.data.repository.OfflineProductRepository
import com.almatar.core.data.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsProductRepository(
        offlineProductRepository: OfflineProductRepository,
    ): ProductRepository
}
