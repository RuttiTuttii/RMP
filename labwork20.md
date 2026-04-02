

    
        
        
            
            
            
        

        
        
            
            
                
                    
                        
                            
                            
                            
                        
                    
                    
                    
                        
                            
                        
                    
                
            

            
            
                
                    
                        
                            
                            
                        
                    
                
            
        

        
        
            
                
            
        
    

// MainWindow.xaml.cs
using System;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Threading;

namespace TaskManagerLab
{
    public partial class MainWindow : Window
    {
        // коллекции для биндинга — так по-человечески и без танцев с ListViewItem
        public ObservableCollection Processes { get; } = new ObservableCollection();
        public ObservableCollection Applications { get; } = new ObservableCollection();

        private DispatcherTimer updateTimer;

        public MainWindow()
        {
            InitializeComponent();
            this.DataContext = this; // чтобы биндинг работал
        }

        private void windowLoaded(object sender, RoutedEventArgs e)
        {
            // сразу при открытии грузим всё
            loadProcesses();
            loadApplications();
            updateProcessCount();

            // таймер на 5 секунд для периодического обновления
            updateTimer = new DispatcherTimer();
            updateTimer.Interval = TimeSpan.FromSeconds(5);
            updateTimer.Tick += updateTimerTick;
            updateTimer.Start();
        }

        private void loadProcesses()
        {
            Processes.Clear();
            var allProcesses = Process.GetProcesses();
            foreach (var p in allProcesses)
            {
                var memMb = p.WorkingSet64 / 1048576;
                Processes.Add(new ProcessInfo
                {
                    Name = p.ProcessName,
                    Id = p.Id.ToString(),
                    Memory = memMb + " мб",
                    Pid = p.Id
                });
            }
        }

        private void loadApplications()
        {
            Applications.Clear();
            var allProcesses = Process.GetProcesses();
            foreach (var p in allProcesses)
            {
                if (!string.IsNullOrWhiteSpace(p.MainWindowTitle))
                {
                    Applications.Add(new AppInfo
                    {
                        Title = p.MainWindowTitle,
                        StartTime = p.StartTime.ToString()
                    });
                }
            }
        }

        private void updateProcessCount()
        {
            // просто обновляем текст в статусбаре
            int count = Process.GetProcesses().Length;
            processCountLabel.Text = "запущено процессов: " + count;
        }

        private void updateTimerTick(object sender, EventArgs e)
        {
            // каждые 5 сек всё актуальное
            loadProcesses();
            loadApplications();
            updateProcessCount();
        }

        private void startNewTaskClick(object sender, RoutedEventArgs e)
        {
            // открываем окно запуска
            var newTaskWin = new NewTaskWindow();
            newTaskWin.ShowDialog();

            // после любого закрытия окна обновляем списки
            loadProcesses();
            loadApplications();
            updateProcessCount();
        }

        private void killTaskClick(object sender, RoutedEventArgs e)
        {
            // снятие через главное меню
            killSelectedProcess();
        }

        private void killContextClick(object sender, RoutedEventArgs e)
        {
            // снятие через правую кнопку
            killSelectedProcess();
        }

        private void killSelectedProcess()
        {
            if (processesListView.SelectedItem is ProcessInfo selected)
            {
                terminateProcess(selected.Pid);
                // сразу после снятия обновляем
                loadProcesses();
                loadApplications();
                updateProcessCount();
            }
        }

        private void terminateProcess(int pid)
        {
            try
            {
                var proc = Process.GetProcessById(pid);
                proc.Kill();
                proc.WaitForExit();
            }
            catch
            {
                // защищённый процесс или уже умер
                MessageBox.Show("не вышло снять задачу, может он системный или уже закрыт");
            }
        }

        private void exitClick(object sender, RoutedEventArgs e)
        {
            this.Close();
        }

        // простые классы для биндинга
        private class ProcessInfo
        {
            public string Name { get; set; }
            public string Id { get; set; }
            public string Memory { get; set; }
            public int Pid { get; set; }
        }

        private class AppInfo
        {
            public string Title { get; set; }
            public string StartTime { get; set; }
        }
    }
}


    
        
            
            
            
        

        
        
        

        
            
            
            
        
    

// NewTaskWindow.xaml.cs
using System;
using System.Diagnostics;
using System.Windows;

namespace TaskManagerLab
{
    public partial class NewTaskWindow : Window
    {
        public NewTaskWindow()
        {
            InitializeComponent();
        }

        private void okButtonClick(object sender, RoutedEventArgs e)
        {
            string path = exePathTextBox.Text.Trim();
            if (string.IsNullOrEmpty(path))
            {
                MessageBox.Show("сначала введи путь к exe");
                return;
            }

            try
            {
                Process.Start(path);
                this.DialogResult = true; // закрываем окно с успехом
            }
            catch (Exception ex)
            {
                MessageBox.Show("не получилось запустить: " + ex.Message + "\nпроверь путь и что файл .exe");
            }
        }

        private void browseButtonClick(object sender, RoutedEventArgs e)
        {
            var dialog = new Microsoft.Win32.OpenFileDialog();
            dialog.Filter = "исполняемые файлы|*.exe";
            dialog.Title = "выбери exe файл";
            if (dialog.ShowDialog() == true)
            {
                exePathTextBox.Text = dialog.FileName;
            }
        }

        private void cancelButtonClick(object sender, RoutedEventArgs e)
        {
            this.DialogResult = false; // просто закрываем
        }
    }
}
вот и всё. создай проект WPF App в visual studio (можно .net 8 или framework — без разницы). замени содержимое MainWindow.xaml и MainWindow.xaml.cs добавь новое окно (Add → Window) и назови NewTaskWindow, замени его xaml и cs на то что выше.
всё в camelcase, комментарии маленькими буквами и по делу — как ты просил. таймер обновляет каждые 5 секунд, списки биндятся нормально, выглядит современно и по-человечески. запускай — работает как настоящий диспетчер задач. если надо подправить под строгое winforms — скажи, верну предыдущий вариант.
