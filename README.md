# my-note
MyNote app is sample of Android `app` which written in `Kotlin` and follow `MVVM` with DI `Koin`

## Table of contents
* [1. Dependencies:](#1.-Dependencies:)
* [2. Minimum Android version](#2.-Minimum-Android-version)
* [3. Permissions](#3.-Permissions)
* [4. Build](#4.-Build)
* [5. License](#5.-License)

## 1. Dependencies:
- MVVM (Architecture)
- Android architecture components (LiveData,LifeCycle, & viewModel)
- android navigation components
- Koin (Dependency Injection)
- Retrofit 2 (HTTP API)
- RxKotlin (Reactive programming)
- kotlin-Coroutines (for non-blocking programming)
- Moshi (lib to parse JSON into model)
- Recyclerview
- Cardview
- Semantic Version
- Espresso,Mockito & Kotlinx-coroutines-test (Testing)



## 2. Minimum Android version

Android 5.1 Lollipop (API-level 22)


## 3. Permissions

- android.permission.INTERNET (Normal)
- android.permission.ACCESS_NETWORK_STATE (Normal)


## 4. Build

- Release
```
./gradlew clean AssembleRelease
```
- Debug
```
./gradlew clean AssembleDebug
```

## 5. License
### Apache 2.0 License
[![License](https://img.shields.io/badge/License-Apache%202.0-yellowgreen.svg)](https://opensource.org/licenses/Apache-2.0)  
=======
    Copyright 202 Maher Abuthraa

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License. 


