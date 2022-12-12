// 타입 생성 - 매개변수를 받아서 증감, INCREASE, DECREASE

const INCREASE = 'counter/INCREASE';
const DECREASE = 'counter/DECREASE';

// 액션 생성 함수
export const increase = () => ({ type: INCREASE });
export const decrease = () => ({ type: DECREASE });

// 초기 상태 선언
const initialState = {
    number: 0,
    diff: 1
}

// 리듀서
const counter = (state = initialState, action) => {
    switch (action.type) {
        case INCREASE:
            return { ...state, number: state.number + 1 };
        case DECREASE:
            return { ...state, number: state.number - 1 };
        default:
            return state;
    }
}

export default counter;