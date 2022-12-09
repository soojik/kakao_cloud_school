import { createStore } from 'redux';

// 사용할 상태 정의
const initialState = {
    counter: 0,
    text: '',
    list: []
}

const INCREASE = 'INCREASE';
const DECREASE = 'DECREASE';
const CHANGE_TEXT = 'CHANGE_TEXT';
const ADD_TO_LIST = 'ADD_TO_LIST';

const increase = () => {
    return {
        type: INCREASE
    }
}

const decrease = () => {
    return {
        type: DECREASE
    }
}

const changeText = text => {
    return {
        type: CHANGE_TEXT,
        text
    }
}

const addToList = item => {
    return {
        type: ADD_TO_LIST,
        item
    }
}

// 위 액션 생성함수들을 통해 만들어진 객체들을 참조하여
// 새로운 상태를 만드는 함수를 만들어봅시다.
// 주의: 리듀서에서는 불변성을 꼭 지켜줘야 합니다!
const reducer = (state = initialState, action) => {
    // state의 초깃값을 initialState로 지정
    switch (action.type) {
        case INCREASE:
            return {
                ...state,
                counter: state.counter + 1
            };
        case DECREASE:
            return {
                ...state,
                counter: state.counter - 1
            };
        case CHANGE_TEXT:
            return {
                ...state,
                text: action.text
            };
        case ADD_TO_LIST:
            return {
                ...state,
                list: state.list.concat(action.item)
            };
        default:
            return state;
    }
}

// store 만들기
const store = createStore(reducer);

// 현재 store 상태
console.log(store.getState());

// listener 설정 - store의 상태가 변겨욀 때마다 호출되도록
const listener = () => {
    const state = store.getState();
    console.log(state);
}

// 구독 설정
const unsubscribe = store.subscribe(listener);


// 액션 호출
store.dispatch(increase());
store.dispatch(decrease());
store.dispatch(changeText('안녕하세요'));
store.dispatch(addToList({ id: 1, text: '와우' }));
