import React from "react";

import CounterContainer from "./containers/CounterContainer";
import ToDosContainer from "./containers/ToDosContainer";

const App = () => {
  return (
    <div>
      <CounterContainer />
      <hr />
      <ToDosContainer />
    </div>
  )
}

export default App;