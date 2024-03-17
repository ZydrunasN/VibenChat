import './App.css';
import {MainComponent} from "./components/MainComponent";
import {createTheme, ThemeProvider} from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import {Box, Container} from "@mui/material";
import {HeaderComponent} from "./components/HeaderComponent";

const darkTheme = createTheme({
    palette: {
        mode: 'dark',
    },
});

function App() {
  return (
      <div className="App">
          <ThemeProvider theme={darkTheme}>
              <Container maxWidth = "fixed">
                  <CssBaseline/>
                  <HeaderComponent/>
                  <Box sx={{ m: '1rem' }}/>
                  <MainComponent/>
              </Container>
          </ThemeProvider>
      </div>
  );
}

export default App;
