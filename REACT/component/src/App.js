import React, { Component } from 'react';
import MyComponent from './MyComponent';
import StateComponent from './StateComponent';
import EventPractice from './EventPractice';
import ValidationSample from './ValidationSample';
import ScrollBox from './ScrollBox';

const App = () => {

  console.log(`*** a = 10, b = a`);

  let a = 10;
  let b = a;

  console.log(`a : ${a}`);
  console.log(`b : ${b}`);

  console.log(`*** b = 20`);

  b = 20;

  console.log(`a : ${a}`);
  console.log(`b : ${b}`);

  let original = {
    num: 1000
  }

  // 객체나 배열은 = 로 복사를 하면 참조가 복사되고, 동일한 데이터를 가리키게 된다.
  let refcopy = original;

  console.log(`*** refcopy = original;`);
  console.log(`original : ${original.num}`);
  console.log(`refcopy : ${refcopy.num}`);

  // 하나의 객체가 내부 속성을 변경하면 다른 객체에게도 영향을 줌
  refcopy.num = 2000;

  console.log(`*** refcopy.num = 2000;`);
  console.log(`original : ${original.num}`);
  console.log(`refcopy : ${refcopy.num}`);

  // 얕은 복사(weak copy) : 가장 바깥쪽 데이터를 복제
  // object.assign 함수 이용하는 방법과 {...객체} - spread operator를 이용하는 방법

  // 1.
  // let weakcopy = {...original};

  // 2.
  let weakcopy = Object.assign({}, original);

  weakcopy.num = 3000;

  console.log(`weakcopy.num = 3000`);

  console.log(`original : ${original.num}`);
  console.log(`weakcopy : ${weakcopy.num}`);

  original = {
    num: 1000,
    arr: ['A', 'B']
  }

  // spread 연산자는 가장 바깥쪽만 복제하는 얕은 복사

  weakcopy = { ...original };

  weakcopy.arr[0] = 'hihi';

  console.log(`*** weakcopy.arr[0] = 'hihi'`);
  console.log(`original : num=${original.num} arr=${original.arr}`);
  console.log(`weakcopy : num=${weakcopy.num} arr=${weakcopy.arr}`);

  // 깊은 복사(deep copy) : 재귀적으로 복제하는 것
  // 데이터만 깊은 복제
  let deepcopy = JSON.parse(JSON.stringify(original));

  console.log(`*** deepcopy.num = 2000`);
  deepcopy.num = 2000;
  console.log(`original : num=${original.num} arr=${original.arr}`);
  console.log(`deepcopy : num=${deepcopy.num} arr=${deepcopy.arr}`);


  console.log(`*** deepcopy.arr[0] = 'heyhey'`);
  deepcopy.arr[0] = 'heyhey';
  console.log(`original : num=${original.num} arr=${original.arr}`);
  console.log(`deepcopy : num=${deepcopy.num} arr=${deepcopy.arr}`);

  // 함수마저도 깊은 복제를 하고자하는 경우
  // 함수를 직접 구현하거나 외부 라이브러리의 도움을 받음
  // immer나 lodash 같은 라이브러리가 이러한 역할을 수행

  return (
    <></>
  )
}
export default App;