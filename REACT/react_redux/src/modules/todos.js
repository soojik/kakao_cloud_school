// 액션 타입 정의/생성
const INSERT = 'todos/INSERT';
const TOGGLE = 'todos/TOGGLE';
const REMOVE = 'toods/REMOVE';
const CHANGE_INPUT = 'todos/CHANGE_INPUT';

// 액션 생성 함수
export const changeInput = (input) => ({
    type: CHANGE_INPUT,
    input
});

// 샘플 데이터 2개 삽입할거라서 id의 초기값 3으로 설정
let id = 3;
export const insert = (text) => ({
    type: INSERT,
    todo: {
        id: id++,
        text,
        done: false
    }
})

export const toggle = (id) => ({
    type: TOGGLE,
    id
})

export const remove = (id) => ({
    type: REMOVE,
    id
})

// 초기값 설정
const initialState = {
    input: '',
    todos: [{
        id: 1,
        text: 'Node',
        done: true
    }, {
        id: 2,
        text: 'React',
        done: false
    }
    ]
};

// 리듀서 함수 생성
const todos = ( state = initialState, action ) => {
    switch (action.type) {
        case CHANGE_INPUT:
            return { ...state, input: action.input };
        case INSERT:
            return { ...state, todos: state.todos.concat(action.todo) };
        case TOGGLE:
            return {
                ...state, todos: state.todos.map(todo =>
                    (todo.id === action.id ? { ...todo, done: !todo.done } : todo))
            };
        case REMOVE:
            return {
                ...state, todos: state.todos.filter(todo => todo.id !== action.id)
            };
        default:
            return state;
    }
}

export default todos;