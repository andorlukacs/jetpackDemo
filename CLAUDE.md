# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Run unit tests
./gradlew test

# Run a single unit test class
./gradlew test --tests "com.example.jetpackdemo.ExampleUnitTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Lint
./gradlew lint
```

## Architecture

Clean Architecture + MVVM with Jetpack Compose, using Hilt for DI.

**Layer flow:** `presentation` → `domain` → `data`

- **`domain/`** — pure Kotlin; `model/` holds domain entities, `repository/` holds interfaces, `usecase/` holds single-responsibility use cases invoked via `operator fun invoke()`
- **`data/`** — implements domain interfaces; `remote/dto/` holds `@Serializable` DTOs mapped to domain models inside `RepositoryImpl` (`.toDomain()` extension)
- **`presentation/`** — `viewmodel/` exposes `StateFlow<SealedState>` (Loading / Content / Error pattern), `ui/view/` contains Composable screens that receive state + lambdas (no direct ViewModel refs in screens)
- **`di/`** — single Hilt `@Module` (`ApiModule`) installed in `SingletonComponent`; binds `MovieRepositoryImpl → MovieRepository`
- **`common/`** — app entry point (`MyApplication`), `navigation/Screen.kt` defines the sealed nav routes with icon/label metadata

**Navigation:** `MainActivity` hosts a `Scaffold` with `MainBottomBar` + `MainNavHost`. Screens are registered in `MainNavHost`; add new screens to `Screen.kt` and `topLevelScreens` list, then wire them in `MainNavHost`.

**API:** OMDB API (`https://www.omdbapi.com/`). The API key is hardcoded in `MovieRepositoryImpl`. Retrofit uses `kotlinx-serialization` for JSON conversion. DTOs use `@SerialName` annotations matching the OMDB JSON field names.

**State pattern:** ViewModels emit sealed classes (`MovieState`, `UiState`). Screens collect state with `collectAsStateWithLifecycle()`. Loading state is set before async work and replaced on completion or error.
