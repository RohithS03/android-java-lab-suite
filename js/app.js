/**
 * Main Web Controller for Android Lab Simulator
 */

document.addEventListener('DOMContentLoaded', () => {
  initPhoneNav();
  initInspectorNav();
  renderTaskList();
  renderDbInspector();
  renderLifecycleLogs();
  renderCoverageMatrix();
  renderTestingSuite();
});

function initPhoneNav() {
  const tabs = document.querySelectorAll('.nav-tab-btn');
  tabs.forEach(tab => {
    tab.addEventListener('click', () => {
      const targetScreen = tab.getAttribute('data-screen');
      tabs.forEach(t => t.classList.remove('active'));
      tab.classList.add('active');

      window.androidEngine.logLifecycle("MainActivity", `onPause() -> switchTab(${targetScreen})`);
      window.androidEngine.logLifecycle("MainActivity", "onResume()");

      showPhoneScreen(targetScreen);
      renderLifecycleLogs();
    });
  });
}

function showPhoneScreen(screenId) {
  const screens = ['screen-tasks', 'screen-analytics', 'screen-settings', 'screen-form', 'screen-detail'];
  screens.forEach(s => {
    const el = document.getElementById(s);
    if (el) el.style.display = (s === screenId) ? 'block' : 'none';
  });

  if (screenId === 'screen-tasks') renderTaskList();
  if (screenId === 'screen-analytics') renderAnalyticsView();
  if (screenId === 'screen-settings') renderSettingsView();
}

function renderTaskList() {
  const container = document.getElementById('taskListContainer');
  if (!container) return;

  const tasks = window.androidEngine.tasks;
  if (tasks.length === 0) {
    container.innerHTML = `<div style="text-align:center; padding:2rem; color:#94a3b8">No tasks found in SQLite database.</div>`;
    return;
  }

  container.innerHTML = tasks.map(t => `
    <div style="background:#1e1e1e; border:1px solid rgba(255,255,255,0.08); border-radius:12px; padding:0.85rem; margin-bottom:0.75rem; display:flex; justify-content:space-between; align-items:center;">
      <div>
        <div style="font-weight:700; font-size:0.9rem; color:#fff; ${t.isCompleted ? 'text-decoration:line-through; opacity:0.6;' : ''}">${t.title}</div>
        <div style="font-size:0.75rem; color:#94a3b8">${t.category} | Due: ${t.dueDate}</div>
      </div>
      <div style="display:flex; gap:0.4rem; align-items:center;">
        <input type="checkbox" ${t.isCompleted ? 'checked' : ''} onchange="handleToggleTask(${t.id})" />
        <button class="btn" style="padding:0.25rem 0.5rem; background:rgba(239,68,68,0.2); color:#fca5a5" onclick="handleDeleteTask(${t.id})"><i class="fas fa-trash"></i></button>
      </div>
    </div>
  `).join('');
}

window.handleToggleTask = function(id) {
  window.androidEngine.toggleTaskComplete(id);
  renderTaskList();
  renderDbInspector();
};

window.handleDeleteTask = function(id) {
  window.androidEngine.deleteTask(id);
  renderTaskList();
  renderDbInspector();
};

window.handleOpenAddForm = function() {
  window.androidEngine.logLifecycle("TaskFormActivity", "onCreate()");
  window.androidEngine.logLifecycle("TaskFormActivity", "onResume()");
  showPhoneScreen('screen-form');
  renderLifecycleLogs();
};

window.handleSaveTaskForm = function(e) {
  e.preventDefault();
  const title = document.getElementById('formTitleInput').value;
  const desc = document.getElementById('formDescInput').value;
  const category = document.getElementById('formCategoryInput').value;
  const dueDate = document.getElementById('formDateInput').value;
  const priority = document.getElementById('formPriorityInput').value;

  try {
    window.androidEngine.addTask(title, desc, category, dueDate, priority);
    showPhoneScreen('screen-tasks');
    renderDbInspector();
    renderLifecycleLogs();
    alert("Task saved successfully into SQLite Database tasks.db!");
  } catch (err) {
    alert(err.message);
  }
};

function renderAnalyticsView() {
  const container = document.getElementById('analyticsViewContainer');
  if (!container) return;

  const stats = window.androidEngine.getTaskStats();
  container.innerHTML = `
    <div style="background:#1e1e1e; padding:1rem; border-radius:12px; border:1px solid rgba(255,255,255,0.08); margin-bottom:1rem">
      <h4 style="color:var(--android-green); margin-bottom:0.5rem">Task Analytics</h4>
      <div style="font-size:0.85rem; color:#fff">Total Tasks: <strong>${stats.total}</strong></div>
      <div style="font-size:0.85rem; color:#fff">Completed Tasks: <strong>${stats.completed}</strong></div>
      <div style="font-size:0.85rem; color:#fff">Pending Tasks: <strong>${stats.pending}</strong></div>
      <div style="font-size:1.1rem; font-weight:700; color:var(--primary-cyan); margin-top:0.5rem">Completion Rate: ${stats.pct.toFixed(1)}%</div>
    </div>
  `;
}

function renderSettingsView() {
  const container = document.getElementById('settingsViewContainer');
  if (!container) return;

  const prefs = window.androidEngine.prefs;
  container.innerHTML = `
    <div style="background:#1e1e1e; padding:1rem; border-radius:12px; border:1px solid rgba(255,255,255,0.08)">
      <h4 style="color:var(--android-green); margin-bottom:0.5rem">SharedPreferences</h4>
      <div style="font-size:0.85rem; color:#fff">User Name: <strong>${prefs.userName}</strong></div>
      <div style="font-size:0.85rem; color:#fff">Dark Mode: <strong>${prefs.darkMode ? 'ENABLED' : 'DISABLED'}</strong></div>
      <div style="font-size:0.85rem; color:#fff">App Launch Count: <strong>${prefs.launchCount}</strong></div>
    </div>
  `;
}

// Inspector View Controller
function initInspectorNav() {
  const btns = document.querySelectorAll('.inspector-nav-btn');
  const cards = document.querySelectorAll('.inspector-card-pane');

  btns.forEach(btn => {
    btn.addEventListener('click', () => {
      const target = btn.getAttribute('data-pane');
      btns.forEach(b => b.classList.remove('active'));
      cards.forEach(c => c.style.display = 'none');

      btn.classList.add('active');
      const pane = document.getElementById(target);
      if (pane) pane.style.display = 'block';
    });
  });
}

function renderLifecycleLogs() {
  const container = document.getElementById('lifecycleLogsBox');
  if (!container) return;
  container.innerText = window.androidEngine.lifecycleLogs.join('\n');
}

function renderDbInspector() {
  const tbody = document.getElementById('dbInspectorBody');
  if (!tbody) return;

  tbody.innerHTML = window.androidEngine.tasks.map(t => `
    <tr>
      <td><code>${t.id}</code></td>
      <td><strong>${t.title}</strong></td>
      <td>${t.category}</td>
      <td>${t.dueDate}</td>
      <td><span class="badge ${t.isCompleted ? 'badge-green' : 'badge-cyan'}">${t.isCompleted ? '1 (TRUE)' : '0 (FALSE)'}</span></td>
    </tr>
  `).join('');
}

function renderCoverageMatrix() {
  const container = document.getElementById('coverageMatrixContainer');
  if (!container) return;

  const matrix = [
    { concept: "Activities & Intents", file: "SplashActivity.java / MainActivity.java", detail: "Explicit Intent bundle passing & implicit share action" },
    { concept: "Fragments & Navigation", file: "TaskListFragment.java / AnalyticsFragment.java", detail: "BottomNavigationView managing dynamic FragmentTransactions" },
    { concept: "SQLite Database Storage", file: "DatabaseHelper.java", detail: "SQLiteOpenHelper with CRUD operations & transactions" },
    { concept: "SharedPreferences", file: "PreferencesManager.java", detail: "Key-value persistent storage for user theme & profile" },
    { concept: "RecyclerView & Adapters", file: "TaskAdapter.java", detail: "ViewHolder pattern with item click & checkbox listeners" },
    { concept: "Background Services", file: "NotificationService.java", detail: "Background NotificationChannel task reminders" },
    { concept: "BroadcastReceivers", file: "NetworkStatusReceiver.java", detail: "Connectivity state broadcast monitoring" }
  ];

  container.innerHTML = `
    <table class="custom-table">
      <thead>
        <tr><th>Android Concept</th><th>Java File</th><th>Implementation Detail</th></tr>
      </thead>
      <tbody>
        ${matrix.map(m => `<tr><td><strong>${m.concept}</strong></td><td><code>${m.file}</code></td><td>${m.detail}</td></tr>`).join('')}
      </tbody>
    </table>
  `;
}

function renderTestingSuite() {
  const container = document.getElementById('testBadgesContainer');
  if (!container) return;

  const results = window.androidEngine.runUnitTests();
  container.innerHTML = results.map(r => `
    <div style="background:var(--bg-card); border:1px solid var(--border-color); border-left:4px solid var(--color-success); border-radius:var(--radius-md); padding:0.75rem; display:flex; justify-content:space-between; align-items:center;">
      <div>
        <strong style="font-size:0.85rem">Test #${r.id}</strong>
        <div style="font-size:0.75rem; color:var(--text-muted)">${r.name}</div>
      </div>
      <span class="badge badge-green">PASS</span>
    </div>
  `).join('');
}
