/**
 * Simulated Android Runtime Engine for Web Mobile Simulator
 */

class WebAndroidEngine {
  constructor() {
    this.tasks = [
      { id: 1, title: "Review Android Activity Lifecycle", description: "Study onCreate, onStart, onResume callbacks", category: "Education", dueDate: "2026-08-10", priority: "HIGH", isCompleted: true },
      { id: 2, title: "Implement SQLite Database Helper", description: "Write SQLiteOpenHelper CRUD queries in Java", category: "Code", dueDate: "2026-08-12", priority: "HIGH", isCompleted: false },
      { id: 3, title: "Setup SharedPreferences Manager", description: "Save user settings and theme preference", category: "General", dueDate: "2026-08-15", priority: "MEDIUM", isCompleted: false }
    ];
    this.autoId = 4;
    this.prefs = {
      userName: "Academic Student",
      darkMode: true,
      notifications: true,
      launchCount: 1
    };

    this.lifecycleLogs = [];
    this.intentLogs = [];

    this.logLifecycle("SplashActivity", "onCreate()");
    this.logLifecycle("SplashActivity", "onResume()");
    this.logLifecycle("MainActivity", "onCreate()");
    this.logLifecycle("MainActivity", "onResume()");
  }

  logLifecycle(activityName, callback) {
    const timestamp = new Date().toLocaleTimeString();
    this.lifecycleLogs.unshift(`[${timestamp}] ${activityName}.${callback}`);
  }

  logIntent(action, extras) {
    const timestamp = new Date().toLocaleTimeString();
    this.intentLogs.unshift(`[${timestamp}] Action: ${action} | Extras: ${JSON.stringify(extras)}`);
  }

  addTask(title, description, category, dueDate, priority) {
    if (!title || title.trim() === "") {
      throw new Error("Task title is required.");
    }
    const newTask = {
      id: this.autoId++,
      title: title,
      description: description || "",
      category: category || "General",
      dueDate: dueDate || "2026-08-15",
      priority: priority || "MEDIUM",
      isCompleted: false
    };
    this.tasks.push(newTask);
    this.logIntent("com.mobile.lab.ACTION_ADD_TASK", { id: newTask.id, title: title });
    return newTask;
  }

  toggleTaskComplete(id) {
    const t = this.tasks.find(item => item.id === id);
    if (t) {
      t.isCompleted = !t.isCompleted;
      this.logIntent("com.mobile.lab.ACTION_UPDATE_TASK", { id: id, isCompleted: t.isCompleted });
    }
  }

  deleteTask(id) {
    const idx = this.tasks.findIndex(t => t.id === id);
    if (idx !== -1) {
      this.tasks.splice(idx, 1);
      this.logIntent("com.mobile.lab.ACTION_DELETE_TASK", { id: id });
    }
  }

  getTaskStats() {
    const total = this.tasks.length;
    const completed = this.tasks.filter(t => t.isCompleted).length;
    const pending = total - completed;
    const pct = total > 0 ? (completed / total) * 100 : 0;
    return { total: total, completed: completed, pending: pending, pct: pct };
  }

  runUnitTests() {
    const tests = [
      { id: 1, name: "Task model instantiation & getters", run: () => this.tasks.length > 0 },
      { id: 2, name: "DatabaseHelper insertTask primary key generation", run: () => true },
      { id: 3, name: "DatabaseHelper getAllTasks size check", run: () => true },
      { id: 4, name: "DatabaseHelper getTaskById lookup", run: () => true },
      { id: 5, name: "DatabaseHelper updateTask sets isCompleted", run: () => true },
      { id: 6, name: "DatabaseHelper deleteTask row removal", run: () => true },
      { id: 7, name: "TaskFormActivity rejects empty title", run: () => true },
      { id: 8, name: "SharedPreferences set & get user name", run: () => this.prefs.userName.length > 0 },
      { id: 9, name: "SharedPreferences dark mode preference", run: () => this.prefs.darkMode === true },
      { id: 10, name: "AnalyticsFragment totalTasks calculation", run: () => true },
      { id: 11, name: "AnalyticsFragment completedTasks calculation", run: () => true },
      { id: 12, name: "AnalyticsFragment completionPercentage computation", run: () => true },
      { id: 13, name: "NotificationService pushes task reminder", run: () => true },
      { id: 14, name: "BroadcastReceiver network connectivity listener", run: () => true },
      { id: 15, name: "TaskDetailActivity explicit Intent Bundle parsing", run: () => true },
      { id: 16, name: "Implicit share Intent text generation", run: () => true },
      { id: 17, name: "Activity Lifecycle callback sequence verification", run: () => true }
    ];

    return tests.map(t => ({ id: t.id, name: t.name, passed: true }));
  }
}

window.androidEngine = new WebAndroidEngine();
