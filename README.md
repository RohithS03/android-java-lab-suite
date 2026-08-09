# Android Application Development — Java Laboratory Suite

An academic Android mobile application repository and interactive web device simulator titled **"Android Application Development — Java Laboratory Suite"**.

Built strictly in **Java 17 / Java 21** (no Kotlin), this repository demonstrates core Android Mobile Application Development concepts through a cohesive **Smart Task & Resource Manager** application.

---

## Android Concept Coverage Matrix

| Android Syllabus Concept | Java Implementation File | Description & Demonstration |
| :--- | :--- | :--- |
| **Activities & Lifecycle** | `SplashActivity.java`, `MainActivity.java` | Lifecycle callback tracing (`onCreate()`, `onStart()`, `onResume()`, `onPause()`, `onDestroy()`). |
| **Explicit & Implicit Intents** | `TaskFormActivity.java`, `TaskDetailActivity.java` | Explicit Intent with Bundle extras (`taskId`), Implicit Intent for action sharing. |
| **Fragments & Navigation** | `TaskListFragment.java`, `AnalyticsFragment.java`, `SettingsFragment.java` | `BottomNavigationView` managing dynamic `FragmentTransaction` switches. |
| **SQLite Local Database** | `DatabaseHelper.java` | `SQLiteOpenHelper` managing `tasks_lab.db` with CRUD operations & transactions. |
| **SharedPreferences** | `PreferencesManager.java` | Key-value persistent storage for user profile, dark theme, and notification preferences. |
| **RecyclerView & Adapters** | `TaskAdapter.java` | `RecyclerView.Adapter` with ViewHolder pattern, checkbox toggles, and item click listeners. |
| **Background Services** | `NotificationService.java` | Background `Service` building `NotificationChannel` task reminder alerts. |
| **BroadcastReceivers** | `NetworkStatusReceiver.java` | `BroadcastReceiver` monitoring system connectivity state change events. |

---

## Technical Architecture & File Structure

```
android-java-lab-suite/
├── app/
│   ├── build.gradle             # Android Gradle configuration (Compile SDK 34, Java 17)
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/mobile/lab/
│           │   ├── model/ (Task.java, Category.java)
│           │   ├── db/ (DatabaseHelper.java, PreferencesManager.java)
│           │   ├── receiver/ (NetworkStatusReceiver.java)
│           │   ├── service/ (NotificationService.java)
│           │   ├── adapter/ (TaskAdapter.java)
│           │   ├── fragment/ (TaskListFragment.java, AnalyticsFragment.java, SettingsFragment.java)
│           │   ├── ui/ (SplashActivity.java, MainActivity.java, TaskFormActivity.java, TaskDetailActivity.java)
│           │   └── util/ (DateUtils.java)
│           └── res/ (layouts, values)
├── build.gradle                 # Root Gradle script
├── settings.gradle              # Gradle settings
├── index.html                   # Interactive Mobile Device Web Simulator
├── css/styles.css               # Material & Mobile frame CSS
└── js/                          # Simulated Android engine & SQLite
```

---

## Verification Plan

### 1. Automated Java Unit Test Verification
Compile and run the 17 automated unit test cases using `javac` and `java`:

```powershell
javac -d bin (Get-ChildItem -Recurse -Filter *.java app/src | Select-Object -ExpandProperty FullName)
java -cp bin com.mobile.lab.AndroidLabTestRunner
```

---

## 📜 License
Licensed under the [MIT License](LICENSE).
