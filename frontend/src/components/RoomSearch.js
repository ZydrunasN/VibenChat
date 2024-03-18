import {Box, MenuItem, TextField} from "@mui/material";

const currencies = [
    {
        value: 'ALL',
        label: 'ALL',
    },
    {
        value: 'POP MUSIC',
        label: 'POP MUSIC',
    },
    {
        value: 'HIP HOP',
        label: 'HIP HOP',
    },
    {
        value: 'ROCK',
        label: 'ROCK',
    },
];

export const RoomSearch = () => {

    return <Box
        component="form"
        sx={{
            '& .MuiTextField-root': {m: 1, width: '25ch'},
        }}
        noValidate
        autoComplete="off"
    >
        <TextField
            id="filled-search"
            label="Search field"
            type="search"
            variant="filled"
        />
        <TextField
            id="outlined-select-currency"
            select
            label="Select"
            defaultValue="EUR"
            helperText="Please select a genre"
        >
            {currencies.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                    {option.label}
                </MenuItem>
            ))}
        </TextField>
    </Box>
}