import React, { useState } from "react";
import { TETextarea, TERipple } from "tw-elements-react";
import { useMutation } from "react-query";
import ApiClient from "../api/ApiClient";

const AddActivity = () => {

    const [form, setForm] = useState({
        description: '',
    });

    const mutation = useMutation(ApiClient.addActivity, {
        onSuccess: (data) => {
            window.location.href = '/main';
        },
    });

    const handleChange = (e) => {
        setForm({
          description: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(form.description === '') return;
      
        mutation.mutate(form.description);
    };

    return (
        <div className="flex flex-col items-center justify-center">
            <div className="max-w-4xl w-full rounded-lg bg-white drop-shadow-lg dark:bg-neutral-700">
                <div className="border-b-2 border-neutral-100 px-6 py-3 dark:border-neutral-600 dark:text-neutral-50 font-bold">
                    <h5 className="text-2xl px-4 font-bold leading-tight text-neutral-800 dark:text-neutral-50">
                        Add a new activity
                    </h5>
                </div>
                <div className="p-8 flex flex-col items-center">
                    <div className="w-full">
                        <TETextarea id="activityDescription" label="Description" rows={4} onChange={handleChange}/>                         
                    </div>
                    <div className="px-6 py-3 dark:text-neutral-50">
                        <TERipple rippleColor="light">
                            <button
                                type="button"
                                onClick={handleSubmit}
                                className="inline-block rounded bg-danger-300 px-7 pb-2.5 pt-3 text-sm font-semibold uppercase leading-normal text-white transition duration-150 ease-in-out hover:bg-danger-400"
                            >
                                DONE
                            </button>
                        </TERipple>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddActivity;