# IP

## BABA User Guide

### Introduction
BABA is a chatbot that runs on the command line. It aims to help users keep track of their tasks, events and deadlines.

---

### **Getting Started**
1. Run the program in command line/terminal.
2. Start by entering commands to manage your tasks.
3. Type 'bye' to exit the application.

---

## Features

### Adding a Todo Task

Adding a Todo task to the user's list

**Format:** `todo DESCRIPTION`

**Example:** `todo finish homework`

```
__________________________________________________________
[SUCCESS] Added: finish homework
__________________________________________________________

```

### Adding Deadlines

Adding a task with a due date to ensure tasks are completed before then

**Format:** `deadline DESCRIPTION /by yyyy-mm-dd`

**Example:** `deadline finish homework /by 2025-03-14`

```
__________________________________________________________
[SUCCESS] Added: finish homework (by: Mar 14 2025)
__________________________________________________________

```

### Adding Events

Adding a task with a start and end time

**Format:** `event DESCRIPTION /from yyyy-mm-dd /to yyyy-mm-dd`

**Example:** `event project meeting /from 2025-03-15 10:00AM /to 2025-03-15 12:00PM`

```
__________________________________________________________
[SUCCESS] Added: project meeting (from: 2025-03-15 10:00AM to: 2025-03-15 12:00PM)
__________________________________________________________
```

### Listing All Tasks

Displays all the tasks stored in the list

**Format:** `list`

**Example:** `list`

```
_____________________________
Here are the tasks in your list:
1. [T][ ] finish homework
2. [D][ ] finish homework (by: Mar 14 2025)
3. [E][ ] project meeting (from: 2025-03-15 10:00AM to: 2025-03-15 12:00PM)
_____________________________
```
### Marking a Task as DONE

Marks a specific task in the list as completed

**Format:** `mark TASK_NUMBER`

**Example:** `mark 2`

```
__________________________________________________________
[SUCCESS] Nice! I've marked this task as done:
[D][X] finish homework (by: Mar 14 2025)
__________________________________________________________
```

### Unmarking a Task as DONE

Marks a specific task in the list as completed

**Format:** `unmark TASK_NUMBER`

**Example:** `unmark 2`

```
__________________________________________________________
[SUCCESS] OK, I've marked this task as not done yet:
[D][ ] finish homework (by: Mar 14 2025)
__________________________________________________________
```

### Deleting a Task

Removes a task from the list

**Format:** `delete TASK_NUMBER`

**Example:** `delete 1`

```
__________________________________________________________
[SUCCESS] Noted. I've removed this task: [T][ ] finish homework
[INFO] Now you have 2 tasks in the list.
__________________________________________________________
```

### Finding a Task from List

Search for tasks containing a specific keyword

**Format:** `find KEYWORD`

**Example:** `find meeting`

```
__________________________________________________________
Here are the matching tasks in your list:
2. [E][ ] project meeting (from: 2025-03-15 10:00AM to: 2025-03-15 12:00PM)
__________________________________________________________
```

### Exiting the application

Closes the application once user is done

**Format:** `find KEYWORD`

**Example:** `find meeting`

```
__________________________________________________________
Bye! Hope to see you again soon!
__________________________________________________________
```

## Command Summary

| Command | Format | Example                                                   |
|---------|--------|-----------------------------------------------------------|
| **Add Todo** | `todo DESCRIPTION` | `todo finish homework`                                    |
| **Add Deadline** | `deadline DESCRIPTION /by DATE_TIME` | `deadline finish homework /by 14/3/2025`                  |
| **Add Event** | `event DESCRIPTION /from START_TIME /to END_TIME` | `project meeting /from 2025-03-15 10:00AM /to 2025-03-15 12:00PM` |
| **List Tasks** | `list` | `list`                                                    |
| **Mark as Done** | `mark TASK_NUMBER` | `mark 1`                                                  |
| **Unmark** | `unmark TASK_NUMBER` | `unmark 1`                                                |
| **Delete** | `delete TASK_NUMBER` | `delete 2`                                                |
| **Find** | `find KEYWORD` | `find meeting`                                            |
| **Exit** | `bye` | `bye`                                                     

