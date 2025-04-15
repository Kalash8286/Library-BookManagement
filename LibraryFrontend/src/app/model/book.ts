import { User } from "./user";

export class Book {
    id!: number;
    title!: string;
    author!: string;
    description!: string;
    borrowed!: boolean;
    borrowedBy!: User | null;
    showMsg!: string;

}