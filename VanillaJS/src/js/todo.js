const todoForm = document.querySelector("#todo form");
const todoInput = document.querySelector("#todo form input");
const todoList = document.getElementById("todo-list");
const TODOS_KEY = "todos"
let todos = [];

function saveTodos(){
localStorage.setItem(TODOS_KEY,JSON.stringify(todos));
}

function deleteTodo(event) {
  const li = event.target.parentElement;
  todos = todos.filter((todo) => todo.id !== parseInt(li.id));
  saveTodos();
  li.remove();
}

function paintTodo(newTodo) {
  const li = document.createElement("li");
  li.id = newTodo.id;
  const span = document.createElement("span");
  const btn = document.createElement("button");
  btn.addEventListener("click", deleteTodo);
  span.innerHTML = newTodo.text;
  btn.innerHTML = "❌";
  li.appendChild(span);
  li.appendChild(btn);
  todoList.appendChild(li);
}

function handleTodoSubmit(event) {
  event.preventDefault();
  const newTodo = todoInput.value;
  todoInput.value = "";
  const newTodoObj={
      text: newTodo,
      id: Date.now(),
  };
  todos.push(newTodoObj);
  paintTodo(newTodoObj);
  saveTodos();
}


todoForm.addEventListener("submit", handleTodoSubmit);

const savedTodos = localStorage.getItem(TODOS_KEY);

if(savedTodos){
    const parsedTodos = JSON.parse(savedTodos);
    todos = parsedTodos;
    parsedTodos.forEach(paintTodo);
}