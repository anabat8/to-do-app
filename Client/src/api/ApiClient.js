const URL = "http://localhost:8080/api";

const sendUserNamePasswordForm = 
    async ({username, password, isLogin}) => {
    
        const response = fetch(URL + '/' + (isLogin ? 'authenticate' : 'register'), {
            mode: 'cors',
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                name: username,
                password: password,
            })
        });

        const data = await response.then(async (res) => {
            if(res.ok) {
                if (isLogin) {
                    return res.json();
                }
                else 
                    return res.text();
            }
            else {
                let err_text = await res.text();
                throw new Error(err_text);
            }
        });

        sessionStorage.setItem('token', data.token);
        sessionStorage.setItem('username', username);
        return data;
    }

const addActivity = 
    async (activity_description) => {

        const response = fetch(URL + '/toDo/add', {
            mode: 'cors',
            method: 'POST',
            //give token to server to authenticate as BEARER token
            headers: {'Authorization': 'Bearer ' + sessionStorage.getItem('token'), 'Content-Type': 'application/json'},
            body: JSON.stringify({
                description: activity_description,
            })
        });
        
        //activity obj contains id, description, and status=false
        const activity = await response.then(async (res) => {
            if(res.ok) {
                return res.json();
            }
            else {
                let err_text = await res.text();
                throw new Error(err_text);
            }
        });

        return activity;
    }

const seeAllActivities = 
    async () => {
        const response = fetch(URL + '/toDo/seeAll', {
            mode: 'cors',
            method: 'GET',
            //give token to server to authenticate as BEARER token
            headers: {'Authorization': 'Bearer ' + sessionStorage.getItem('token')},
        });
        console.log(sessionStorage.getItem('token'));
        const activities = await response.then(async (res) => {
            if(res.ok) {
                return res.json();
            }
            else {
                let err_text = await res.text();
                throw new Error(err_text);
            }
        });

        return activities;
    }

const deleteActivity = 
    async (activity_id) => {
        const response = fetch(`${URL}/toDo/deleteActivity/${activity_id}`, {
            mode: 'cors',
            method: 'DELETE',
            //give token to server to authenticate as BEARER token
            headers: {'Authorization': 'Bearer ' + sessionStorage.getItem('token'), 'Content-Type': 'application/json'},
        });

        const activity_deleted = response.then(async (res) => {
            if(res.ok) {
                return res.json();
            }
            else {
                let err_text = await res.text();
                throw new Error(err_text);
            }
        });

        return activity_deleted;
    }

const checkActivity =
    async (activity_id) => {
        const response = fetch(`${URL}/toDo/checkActivity/${activity_id}`, {
            mode: 'cors',
            method: 'POST',
            //give token to server to authenticate as BEARER token
            headers: {'Authorization': 'Bearer ' + sessionStorage.getItem('token'), 'Content-Type': 'application/json'},
        });

        const activity_checked = response.then(async (res) => {
            if(res.ok) {
                return res.json();
            }
            else {
                let err_text = await res.text();
                throw new Error(err_text);
            }
        });

        return activity_checked;
    }


const ApiClient = {
    sendUserNamePasswordForm,
    addActivity,
    seeAllActivities,
    deleteActivity,
    checkActivity,
};

export default ApiClient;