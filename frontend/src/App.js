import './App.css';
import {createTheme, ThemeProvider} from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import {Box, Container} from "@mui/material";
import {HeaderComponent} from "./components/HeaderComponent";
import {HomePage} from "./pages/HomePage";

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
                  <HomePage/>
              </Container>
          </ThemeProvider>
      </div>
  );
}

export default App;
