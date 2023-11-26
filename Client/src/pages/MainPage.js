import { React } from 'react';
import ApiClient from '../api/ApiClient';
import { useQuery, useMutation, useQueryClient } from 'react-query';
import Message from '../components/Message';
import { TERipple } from 'tw-elements-react';
import { RiDeleteBin6Line } from "react-icons/ri";
import { MdCheckBoxOutlineBlank } from "react-icons/md";
import { MdCheckBox } from "react-icons/md";

const MainPage = () => {
    const queryClient = useQueryClient();
    let queryResult = useQuery("currentActivities", ApiClient.seeAllActivities);

    let mutationDeleteActivity = useMutation(ApiClient.deleteActivity, 
        {
            onSuccess: (data) => {
                queryClient.invalidateQueries("currentActivities");
            },
        });
    
    let mutationCheckActivity = useMutation(ApiClient.checkActivity,
        {
            onSuccess: (data) => {
                queryClient.invalidateQueries("currentActivities");
            },
        });

    let activities = queryResult.data;
    let isLoading = queryResult.isLoading;
    let error = queryResult.error;

    if (isLoading) return <div>Fetching toDos...</div>;
    if (error) return <Message msg={error.Message} isSuccess={false}/> 

    const handleSubmit = async (e) => {
        window.location.href = '/addActivity';
    };

    const handleClickOnBin = async (id, e) => {
        mutationDeleteActivity.mutate(id); 
    };

    const handleClickOnCheckBox = async (id, e) => {
        mutationCheckActivity.mutate(id);
    }
    
    return ( 
        <div className="flex flex-col items-center justify-center">
            <div className="max-w-4xl w-full rounded-lg border-2 bg-white dark:bg-neutral-700">
                <div className="bg-secondary-50 border-b-2 border-neutral-100 px-6 py-3 dark:border-neutral-600 dark:text-neutral-50 font-bold">
                    <h5 className="text-2xl px-6 font-bold leading-tight text-neutral-800 dark:text-neutral-50">
                        TO DOs 📝
                    </h5>
                </div>
                <div className="p-8 flex flex-col items-center">
                    <ul className="w-full">
                        {activities.sort((a, b) => {
                            if(a.status === b.status)
                                return 1; 
                            else 
                                return a.status ? 1 : -1;
                        }).map((data) => (
                        <li className={`w-full md:text-lg border-b-2 px-6 py-3 break-words ${ data.status ? "line-through bg-secondary-300" : "bg-secondary-50"} `}>
                            <div className={`mb-2 font-medium`}>
                                {data.description}
                            </div>
                            <div className="flex flex-row justify-end gap-2">
                                <span className="">
                                    <RiDeleteBin6Line size={20} color={"#EF4444"} 
                                        onClick={(e) => handleClickOnBin(data.id, e)}/>
                                </span>
                                <span className="">
                                    {data.status 
                                    ? <MdCheckBox size={20} color={"#EF4444"}/>
                                    : <MdCheckBoxOutlineBlank size={20} color={"#EF4444"} onClick={(e) => handleClickOnCheckBox(data.id, e)}/>
                                    }
                                </span>
                            </div>
                        </li>
                        ))}
                    </ul>
                    <div className="px-6 py-3 dark:text-neutral-50">
                        <TERipple rippleColor="light">
                            <button
                                type="button"
                                onClick={handleSubmit}
                                className="inline-block rounded bg-danger-300 px-7 pb-2.5 pt-3 text-sm font-semibold uppercase leading-normal text-white transition duration-150 ease-in-out hover:bg-danger-400"
                            >
                                Add Activity
                            </button>
                        </TERipple>
                    </div>
                </div>
            </div>
        </div>
        
    );
};

export default MainPage;