import { ToastContainer, toast } from 'react-toastify';
import {Routes, Route,BrowserRouter as Router} from 'react-router-dom'
import { publicRoutes } from './router/index';
import DefaultLayout from './layout/defaultLayout/defaultLayout'

function App() {
  return (
    <Router>
      <div className="App">
          <Routes>
            {publicRoutes.map((route, index) => {
                const Layout = route.layout || DefaultLayout
                const Page = route.component
                return <Route key={index} path={route.path} element={
                  <Layout>
                    <Page/>
                  </Layout>
                }/>
            })}
          </Routes>
      </div>
      <ToastContainer/>
    </Router>

);

}

export default App;
