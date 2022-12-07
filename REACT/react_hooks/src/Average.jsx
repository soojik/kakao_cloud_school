import React from "react";
import { useState, useMemo, useCallback } from "react";

// Component와 관련없는 메서드이기 때문에 useCallback을 적용할 수 없다.
const getAverage = (numbers) => {
    console.log('평균 계산');
    if (numbers.length === 0) return 0;

    // reduce는 배열 순회하며 연산을 수행한 후 하나의 값을 리턴
    // 매개변수는 2개인데 첫번째 매개변수는 수행할 함수
    // 두번째 매개변수는 연산 시작할 때의 초기값
    // 두번째 매개변수를 생략하면 배열의 첫번째 요소로 설정
    // 첫번째 매개변수인 함수는 매개변수를 4개까지 갖는데,
    // 첫번째는 누적값, 두번째는 배열의 요소
    // 세번째는 배열의 인덱스이고, 네번째는 배열

    // [10, 20, 130, 240]
    // 10 + 20 = 30
    // 30 + 130
    // 160 + 240
    // 400
    const sum = numbers.reduce((a, b) => a + b);

    return sum / numbers.length;
}

const Average = () => {
    const [list, setList] = useState([]);
    const [number, setNumber] = useState('');

    //
    const onChange = e => {
        setNumber(e.target.value);
    }

    const onInsert = e => {
        const nextList = list.concat(parseInt(number));
        setList(nextList);
        setNumber('');
    }

    return (
        <>
            <input type="text" value={number} onChange={onChange} />
            <button onClick={onInsert}>추가</button>
            <ul>
                {list.map((value, index) => {
                    <li key={index}>{value}</li>
                })}
            </ul>

            <div>
                <b>평균:</b>{useMemo(() => getAverage(list), [list])}

            </div>
        </>
    )
}

export default React.memo(Average);