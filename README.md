# Project Repository

## 1. Title & Overview
## 2. Objective
## 3. Vision
## 4. Problem Statement

# Android Application Development — Java Laboratory Suite

An academic Android mobile application repository and interactive web device simulator titled **"Android Application Development — Java Laboratory Suite"**.

Built strictly in **Java 17 / Java 21** (no Kotlin), this repository demonstrates core Android Mobile Application Development concepts through a cohesive **Smart Task & Resource Manager** application.

---

## Android Concept Coverage Matrix

| Android Syllabus Concept | Java Implementation File | Description & Demonstration |
| :--- | :--- | :--- |
| **Activities & Lifecycle** | [`SplashActivity.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/ui/SplashActivity.java), [`MainActivity.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/ui/MainActivity.java) | Lifecycle callback tracing (`onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onDestroy()`). |
| **Explicit & Implicit Intents** | [`TaskFormActivity.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/ui/TaskFormActivity.java), [`TaskDetailActivity.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/ui/TaskDetailActivity.java) | Explicit Intent with Bundle extras (`taskId`), Implicit Intent for action sharing. |
| **Fragments & Navigation** | [`TaskListFragment.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/fragment/TaskListFragment.java), [`AnalyticsFragment.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/fragment/AnalyticsFragment.java), [`SettingsFragment.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/fragment/SettingsFragment.java) | `BottomNavigationView` managing dynamic `FragmentTransaction` switches. |
| **SQLite Local Database** | [`DatabaseHelper.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/db/DatabaseHelper.java) | `SQLiteOpenHelper` managing `tasks_lab.db` with CRUD operations & transactions. |
| **SharedPreferences** | [`PreferencesManager.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/db/PreferencesManager.java) | Key-value persistent storage for user profile, dark theme, and notification preferences. |
| **RecyclerView & Adapters** | [`TaskAdapter.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/adapter/TaskAdapter.java) | `RecyclerView.Adapter` with ViewHolder pattern, checkbox toggles, and item click listeners. |
| **Background Services** | [`NotificationService.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/service/NotificationService.java) | Background `Service` building `NotificationChannel` task reminder alerts. |
| **BroadcastReceivers** | [`NetworkStatusReceiver.java`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/app/src/main/java/com/mobile/lab/receiver/NetworkStatusReceiver.java) | `BroadcastReceiver` monitoring system connectivity state change events. |

---

## Technical Architecture & File Structure

```
d:/R O H I T H/GitHub_Repos/android-java-lab-suite/
│
├── app/
│   ├── build.gradle             # Android Gradle configuration (Compile SDK 34, Java 17)
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/
│       │   │   └── com/
│       │   │       └── mobile/
│       │   │           └── lab/
│       │   │               ├── model/
│       │   │               │   ├── Task.java               # Task model
│       │   │               │   └── Category.java           # Category model
│       │   │               ├── db/
│       │   │               │   ├── DatabaseHelper.java     # SQLiteOpenHelper storage
│       │   │               │   └── PreferencesManager.java # SharedPreferences manager
│       │   │               ├── receiver/
│       │   │               │   └── NetworkStatusReceiver.java # BroadcastReceiver
│       │   │               ├── service/
│       │   │               │   └── NotificationService.java # Background Service
│       │   │               ├── adapter/
│       │   │               │   └── TaskAdapter.java        # RecyclerView.Adapter
│       │   │               ├── fragment/
│       │   │               │   ├── TaskListFragment.java   # Task list fragment
│       │   │               │   ├── AnalyticsFragment.java  # Stats fragment
│       │   │               │   └── SettingsFragment.java   # Settings fragment
│       │   │               ├── ui/
│       │   │               │   ├── SplashActivity.java     # Onboarding splash
│       │   │               │   ├── MainActivity.java       # BottomNav host
│       │   │               │   ├── TaskFormActivity.java   # Form entry & validation
│       │   │               │   └── TaskDetailActivity.java # Details & Intent extras
│       │   │               └── util/
│       │   │                   └── DateUtils.java          # Date utility helper
│       │   └── res/
│       │       ├── layout/                              # Android XML layout files
│       │       └── values/                              # Colors, strings, themes
│       └── test/
│           └── java/
│               └── com/
│                   └── mobile/
│                       └── lab/
│                           └── AndroidLabTestRunner.java # 17 Automated Unit Tests
│
├── build.gradle                 # Root Gradle script
├── settings.gradle              # Gradle settings
│
├── index.html                   # Interactive Mobile Device Web Simulator
├── css/
│   └── styles.css               # Material & Mobile frame CSS
└── js/
    ├── android_engine.js        # Simulated Android engine & SQLite
    └── app.js                   # Web simulator controller
```

---

## Verification Plan

### 1. Automated Java Unit Test Verification
Compile and run the 17 automated unit test cases using `javac` and `java`:

```powershell
$javac = "C:\Users\rohit\.antigravity\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\javac.exe"
$java  = "C:\Users\rohit\.antigravity\extensions\redhat.java-1.55.0-win32-x64\jre\21.0.11-win32-x86_64\bin\java.exe"

if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
& $javac -d bin (Get-ChildItem -Recurse -Filter *.java app/src | Select-Object -ExpandProperty FullName)
& $java -cp bin com.mobile.lab.AndroidLabTestRunner
```

Expected Test Output:
```
========================================================
   ANDROID MOBILE APP DEVELOPMENT UNIT TEST SUITE       
========================================================
 [PASS] Task model title getter
 [PASS] Task model priority getter
 [PASS] Task model initial completion state == false
 [PASS] DatabaseHelper insertTask returns generated primary key > 0
 [PASS] DatabaseHelper getAllTasks size == 1
 [PASS] DatabaseHelper getTaskById
 [PASS] DatabaseHelper updateTask sets isCompleted == true
 [PASS] DatabaseHelper deleteTask removes row
 [PASS] TaskFormActivity rejects empty task title with IllegalArgumentException
 [PASS] SharedPreferences set & get user name
 [PASS] SharedPreferences dark mode preference
 [PASS] AnalyticsFragment totalTasks == 2
 [PASS] AnalyticsFragment completedTasks == 1
 [PASS] AnalyticsFragment completionPercentage == 50.0%
 [PASS] NotificationService pushes task reminder notification
 [PASS] BroadcastReceiver network connectivity state
 [PASS] TaskDetailActivity implicit share intent text generation
========================================================
 TEST RESULTS SUMMARY: 17 PASSED, 0 FAILED
========================================================
```

### 2. Native Android Studio Gradle Build Setup
To open and run the native project inside **Android Studio**:
1. Open Android Studio -> Select **Open an Existing Project**.
2. Select [`d:/R O H I T H/GitHub_Repos/android-java-lab-suite`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite).
3. Allow Gradle Sync to finish.
4. Select an Android Emulator or connected physical Android device and click **Run 'app'**.

### 3. Interactive Mobile Web Simulator Verification
1. Open [`index.html`](file:///d:/R%20O%20H%20I%20T%20H/GitHub_Repos/android-java-lab-suite/index.html) in any web browser.
2. Interact with the Phone Simulator:
   - Add new tasks via the Task Form.
   - Toggle completion checkboxes and view Analytics updates.
   - Inspect live Activity Lifecycle logs in the right panel.
   - Inspect live SQLite DB rows in the SQLite DB Inspector table.
   - Verify 17/17 test pass badges.

---

## Limitations & Future Improvements

1. **Room ORM Migration**: While `DatabaseHelper` uses standard `SQLiteOpenHelper`, production apps can migrate to Android Room ORM.
2. **WorkManager API**: `NotificationService` can be extended with Android `WorkManager` for persistent background scheduling across device reboots.
