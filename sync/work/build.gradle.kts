/*
 * Copyright 2022 The Android Open Source Project
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
plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.library.jacoco")
    id("nowinandroid.android.hilt")
}

android {
    defaultConfig {
        testInstrumentationRunner =
            "com.google.samples.apps.nowinandroid.core.testing.NiaTestRunner"
    }
    namespace = "com.google.samples.apps.nowinandroid.sync"
}

dependencies {
    api(project(":core:analytics"))
    api(project(":core:common"))
    api(project(":core:data"))
    api(project(":core:datastore"))
    api(project(":core:model"))
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.work.ktx)
    api(libs.firebase.cloud.messaging)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.hilt.ext.work)
    with(libs.hilt.ext.compiler) {
        kapt(this)
        kaptTest(this)
        kaptAndroidTest(this)
    }

    androidTestImplementation(libs.androidx.work.testing)
    androidTestImplementation(project(":core:testing"))
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(project(":core:datastore-test"))
    androidTestImplementation(project(":core:data-test"))
}

kapt {
    javacOptions {
        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
    }
}
