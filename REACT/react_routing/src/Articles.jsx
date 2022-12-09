import { Link } from 'react-router-dom';

const Articles = () => {
    return (
        <div>
            <ul>
                <li>
                    <Link to='/articles/1'>초밥 맛있겠다.</Link>
                </li>

                <li>
                    <Link to='/articles/2'>[속보] 배고프다</Link>
                </li>

                <li>
                    <Link to='/articles/3'>오리온 후레쉬베리 맛있다 .. !</Link>
                </li>
            </ul>
        </div>
    )
}

export default Articles;