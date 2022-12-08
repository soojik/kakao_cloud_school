import { useCallback } from 'react';
import { useState } from 'react';
import { MdAdd } from 'react-icons/md';
import './ToDoInsert.scss';

const ToDoInsert = ({ onInsert }) => {
    // 입력된 데이터를 저장하기 위한 state
    const [value, setValue] = useState('');

    // 입력 내용이 변경될 때 호출될 함수
    const onChange = useCallback((e) => {
        setValue(e.target.value);
    }, []);

    // form에서 submit 이벤트가 발생하면 호출될 함수
    // form 안에서 submit 버튼을 눌러도 submit 이벤트가 발생하지만
    // form 안에서 enter를 눌러도 submit 이벤트가 발생
    // enter로 입력 받으면 아이디, 비밀번호 모두 입력했을 때만 제출이 되도록 따로 설정 필요
    // 네이버처럼 아이디만 입력 후, enter하면 비밀번호 submit 이벤트가 발생해서 매번 아이디, 비밀번호 유효성 검사 진행

    // onInsert, value가 수정될 때만 불러들이도록
    const onSubmit = useCallback((e) => {
        // 데이터 삽입
        onInsert(value);

        // input 초기화
        setValue('');

        // 제공되는 기본 이벤트 처리 코드 수행하지 않는다.
        // form의 submitdlsk ㅁ xormsms
        // 화면 전체를 갱신하기 때문에 이전 내용을 모두 잃어버린다.
        e.preventDefault();
    }, [onInsert, value]);

    return (
        <form className="ToDoInsert" onSubmit={onSubmit}>
            <input
                placeholder="할 일을 입력하세요"
                value={value}
                onChange={onChange}
            />
            <button type="submit">
                <MdAdd />
            </button>
        </form>

    );
}

export default ToDoInsert;