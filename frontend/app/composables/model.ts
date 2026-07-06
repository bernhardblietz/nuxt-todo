export type TodoItem = {
    id: number;
    text: string;
    completed: boolean;
};

export type TodoPostDTO = {
    text: string;
    completed?: boolean;
};

export type TodoPutDTO = {
    text?: string;
    completed?: boolean;
};