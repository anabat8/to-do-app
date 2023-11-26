import React, { useState } from 'react';
import { TEInput, TERipple } from 'tw-elements-react';
import { useMutation } from 'react-query';
import ApiClient from '../api/ApiClient';
import Message from '../components/Message';

const UsernamePasswordForm = (props) => {

  const [errorMessage, setErrorMessage] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const mutation = useMutation(ApiClient.sendUserNamePasswordForm,
    {
    onSuccess: (data) => {
      if (props.isLogin) {
        window.location.href = '/main';
      }
      else {
        setSuccessMessage(data);
        setTimeout(() => {
          window.location.href = '/authenticate';
        }, 1000);
      }
    },
    onError: (err) => {
      setErrorMessage(err.message);
    },
  });

  const [form, setForm] = useState({
    username: '',
    password: '',
  });

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if(!form.username || !form.password) return;

    //reset messages for every button click
    setErrorMessage('');
    setSuccessMessage('');

    mutation.mutate({username: form.username, password: form.password, isLogin: props.isLogin});
  };
  
  return (
    <div className="flex flex-col items-center justify-center">
      <h4 className="lg:text-xl text-xl font-poppins text-black leading-10 md:text-center py-10 font-semibold drop-shadow">
          {props.isLogin ? "Log into your account" : "Create an account"}
      </h4>
      <TEInput
        type="text"
        name="username"
        label="Username"
        size="lg"
        className="mb-6"
        onChange={handleChange}
      >
      </TEInput>      
      <TEInput
        type="password"
        name="password"
        label="Password"
        className="mb-6"
        size="lg"
        onChange={handleChange}
      >
      </TEInput>
      <TERipple rippleColor="light">
          <button
            type="button"
            onClick={handleSubmit}
            className="inline-block rounded bg-danger-300 px-7 pb-2.5 pt-3 text-sm font-medium uppercase leading-normal text-white transition duration-150 ease-in-out hover:bg-danger-400"
          >
            {props.isLogin ? "Login" : "Sign Up"}
          </button>
      </TERipple>
      <h4 className="lg:text-xl text-lg font-poppins text-black leading-10 md:text-center py-10 font-semibold drop-shadow">
          {props.isLogin ? "Don't have an account yet?" : "Already have an account?"}
          <br/>
          <a 
            href={props.isLogin ? "/register" : "/authenticate"}
            className="text-danger transition duration-150 ease-in-out hover:text-danger-600 focus:text-danger-600 active:text-danger-700"
          >
            {props.isLogin ? "Register" : "Log in"}
          </a>
      </h4>
      { (errorMessage !== '' && <Message msg={errorMessage} isSuccess={false}/> ) ||
        (successMessage !== '' && <Message msg={successMessage} isSuccess={true}/> )
      }
    </div>
  );
};

export default UsernamePasswordForm;