import { Outlet, useNavigate } from 'react-router-dom';

const Layout = () => {
    const navigate = useNavigate();

    // 뒤로 이동하는 함수
    const goBack = () => {
        navigate(-1);
    }


    // 게시글(articles) 이동하는 함수
    const goArticles = () => {
        navigate('/articles');
    }

    return (
        <div>
            <header style={{ background: 'darkgreen', padding: 16, fontSize: 24, color: 'ghostwhite' }}>
                <button onClick={goBack}>뒤로</button>
                <button onClick={goArticles}>게시글</button>
            </header>
            <main>
                <Outlet />
            </main>
        </div>
    )
}

export default Layout;