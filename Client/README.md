# To Do List App

## Start Client App

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

## End-2-End tests with Postman
Register a user by passing a username and password:

![alt text](./E2E_images/RegisterUser.png)

Authenticate based on the credentials -> if auth is successful, the user receives a JWT token.

![alt text](./E2E_images/AuthUser.png)

Start adding tasks to your to do list:

![alt text](./E2E_images/AddTasks.png)

You can also delete tasks:

![alt text](./E2E_images/DeleteActivityGivenID.png)

Or check (mark activities as done):

![alt text](./E2E_images/CheckActivity1.png)

And see the resulted to do list:

![alt text](./E2E_images/SeeAllTasksAfterOperations.png)