"use client";

import { useEffect, useState } from "react";
import api from "@/services/api";
import { useRouter } from "next/navigation";

interface Task {
    id: number;
    title: string;
    description: string;
    status: string;
    priority: string;
    dueDate: string;
}

export default function TasksPage() {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const router = useRouter();

    const fetchTasks = async () => {
        try {
            const res = await api.get("/api/tasks");
            setTasks(res.data.content);
        } catch (error) {
            alert("Unauthorized. Please login again.");
            router.push("/login");
        }
    };

    const createTask = async () => {
        try {
            await api.post("/api/tasks", {
                title,
                description,
                status: "TODO",
                priority: "HIGH",
                dueDate: "2026-03-10"
            });

            setTitle("");
            setDescription("");
            fetchTasks();
        } catch (error) {
            alert("Failed to create task");
        }
    };

    useEffect(() => {
        fetchTasks();
    }, []);

    return (

        <div style={{ padding: 40 }}>
            <button
                onClick={() => {
                    localStorage.removeItem("token");
                    router.push("/login");
                }}
            >
                Logout
            </button>

            <br /><br />
            <h1>My Tasks</h1>

            <div>
                <input
                    placeholder="Task Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />
                <br /><br />
                <input
                    placeholder="Description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                />
                <br /><br />
                <button onClick={createTask}>Create Task</button>
            </div>

            <hr /><br />

            {tasks.map((task) => (
                <div key={task.id} style={{ marginBottom: 20 }}>
                    <h3>{task.title}</h3>
                    <p>{task.description}</p>
                    <p>Status: {task.status}</p>
                    <p>Priority: {task.priority}</p>

                    <button
                        onClick={async () => {
                            await api.put(`/api/tasks/${task.id}`, {
                                title: task.title + " (Updated)",
                                description: task.description,
                                status: "DONE",
                                priority: task.priority,
                                dueDate: task.dueDate,
                            });
                            fetchTasks();
                        }}
                    >
                        Mark as Done
                    </button>
                </div>
            ))}
        </div>
    );
}