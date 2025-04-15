import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Book } from '../model/book';
import { BorrowBook } from '../model/borrowBook';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class LibraryApiService {

  private apiURL = 'http://localhost:8080/'
  constructor(private http: HttpClient) { }

  addBook(formValues: Book) : Observable<Book>{
    return this.http.post<Book>(this.apiURL+'book/save', formValues);
  }

  getAllBooks(): Observable<Book[]>{
    return this.http.get<Book[]>(this.apiURL+'book/get/all');
  }

  getBookByID(bookId: number) : Observable<Book>{
    return this.http.get<Book>(this.apiURL+'book/get/'+ bookId);
  }

  // bookId , userEmail
  borrowBook(bookId: number, userEmail: BorrowBook) : Observable<void>{    
   return this.http.patch<void>(this.apiURL+'book/borrow/'+bookId, userEmail);
  }

  returnBook(bookId: number) : Observable<void>{    
    return this.http.patch<void>(this.apiURL+'book/return/'+bookId, null);
   }

  deleteBook(bookId: number) : Observable<void>{    
    return this.http.delete<void>(this.apiURL+'book/delete/'+bookId);
  }


  addUser(user : User): Observable<User>{
    return this.http.post<User>(this.apiURL + 'user/save', user);
  }

  getAllUser(): Observable<User[]>{
    return this.http.get<User[]>(this.apiURL+'user/get/all');
  }

}
