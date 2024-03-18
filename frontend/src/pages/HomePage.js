import {Grid, Paper, styled, Typography} from "@mui/material";
import {RoomSearch} from "../components/RoomSearch";

const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
}));

export const HomePage = () => {
    return <main>
        <Grid container maxWidth="fixed" spacing={2}>
            <Grid item xs={3}>
                <Item>
                    xs=6
                </Item>
            </Grid>
            <Grid item xs={6}>
                <Item>
                    <Typography sx={{fontSize:40, fontWeight:'Bold'}}>Rooms</Typography>
                    <RoomSearch/>
                </Item>
            </Grid>
            <Grid item xs={3}>
                <Item>
                    xs
                </Item>
            </Grid>
        </Grid>
    </main>
}