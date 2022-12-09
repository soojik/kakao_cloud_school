import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div>
            <h1>Home page - 메인 페이지</h1>
            <ul>
                <li>
                    <Link to='/about'>소개</Link> <br />
                </li>
                <li>
                    <Link to='/profile/ning'>닝닝</Link> <br />
                </li>

                <li><Link to='/profile/hanni'>하니</Link> <br /></li>
                <li><Link to='/profile/soo'>모르는 유저의 경우</Link></li>
                <li><Link to='/articles'>게시물</Link></li>
            </ul>
        </div>
    )
}

export default Home;