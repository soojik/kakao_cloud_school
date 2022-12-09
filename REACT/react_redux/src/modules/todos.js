const ADD_TODO = 'todos/ADD_TODO';
const TOGGLE_TODO = 'todos/TOGGLE_TODO';

// 액션 생성 함수
let nextId = 1;

export const addToDo = (text) => ({
    type: ADD_TODO,
    todo: {
        id: nextId++,
        text
    }
})

export const toggleToDO = (id) => ({
    type: TOGGLE_TODO,
    id: id
})

// 빈 배열로 state 초기화
const initialState = [];

const todos = (state = initialState, action) => {
    switch (action.type) {
        case ADD_TODO:
            return state.concat(action.todo);
        case TOGGLE_TODO:
            return state.map(todo => todo.id === action.id ? { ...todo, done: !todo.done } : todo);
        default:
            return state;
    }
}

export default todos;