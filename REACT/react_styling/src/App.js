import './App.css';

import axios from 'axios';

import styles from './App.scss';
import classNames from 'classnames/bind';


const cx = classNames.bind(styles);

function App() {
  return (
    <>
      {/* <nav>
        <div className='nav-wrapper'>
          <div>react</div>
        </div>
      </nav>
      <div>material design</div> */}

      <div>
        <button onClick={(e) => {
          /* ajax로 데이터 받아오기
          let request = new XMLHttpRequest;

          request.open('GET', 'https://jsonplaceholder.typicode.com/users');

          // GET 방식은 파라미터 없어도 된다.
          // POST 방식일 때는 파라미터 대입
          request.send('');

          // 데이터 로드 성공
          request.addEventListener('load' , () => {
            let data = JSON.parse(request.responseText);

            console.log(data);
          })

          // 데이터 로드 실패
          request.addEventListener('error', (error) => {
            console.log(error);
          }) 
          
          */

          /* fetch api로 데이터 받아오기
          fetch('https://jsonplaceholder.typicode.com/users')
            .then((response) => response.json())
            .then((data) => console.log(data))
            .catch((err) => console.log(err));

          */

          /* axios로 데이터 받아오기 */
          axios.get('https://jsonplaceholder.typicode.com/users')
            .then((response) => console.log(response.data))
            .catch((error) => console.log(error));

        }}>다운로드</button>
      </div>
    </>
  );
}

export default App;
