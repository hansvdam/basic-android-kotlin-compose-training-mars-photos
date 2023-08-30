/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.fake.FakeDataSource
import com.example.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MarsViewModelTestHilt {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var marsPhotosRepository: MarsPhotosRepository

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = marsPhotosRepository
            )
            assertEquals(
                MarsUiState.Success(FakeDataSource.photosList),
                marsViewModel.marsUiState
            )
        }
}
