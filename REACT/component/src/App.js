import React, { Component } from 'react';
import MyComponent from './MyComponent';
import StateComponent from './StateComponent';
import EventPractice from './EventPractice';
import ValidationSample from './ValidationSample';
import ScrollBox from './ScrollBox';

class App extends Component {
  render() {
    return (<>
    <MyComponent name='지수' year={1999}>태그 안의 내용</MyComponent>
    <StateComponent></StateComponent>
    <EventPractice></EventPractice>
    <ValidationSample></ValidationSample>
    <ScrollBox ref={(ref) => this.box = ref}></ScrollBox>
    <button onClick={() => this.box.scrollToBottom()}>맨 아래로</button>
    </>);
  }
}

export default App;