import { combineReducers } from 'redux';

// 합치고자하는 모듈 가져오기
import counter from './counter';
import todos from './todos';

const rootReducer = combineReducers({ counter, todos });

export default rootReducer;